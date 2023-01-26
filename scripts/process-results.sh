#!/bin/bash 

source $BE_EXP_SRC/scripts/common.sh

resultsdir=$1
tmpfile="processresults.csv.tmp"
[[ -f $tmpfile ]] && rm $tmpfile

function process_results() {
    projects=$(ls $resultsdir)
    [[ $projects == "" ]] && echo "No proyects found in $currdir" && exit -1;
    for project in $projects
    do
        currdir=$resultsdir/$project
        cases=$(ls $currdir)
        [[ $cases == "" ]] && echo "No case studies found in $currdir" && continue;
        for casestudy in $cases 
        do
            currdir=$resultsdir/$project/$casestudy
            for technique in $techniques 
            do
                currdir=$resultsdir/$project/$casestudy/$technique
                [[ ! -d $currdir ]] && continue;
                budgets=$(ls $currdir)
                [[ $budgets == "" ]] && echo "No budgets found in $currdir" && continue;
                for budget in $budgets
                do
                    classname=${casestudy##*\.}
                    basedir=$resultsdir/$project/$casestudy/$technique/$budget/
                    [[ ! -d $basedir ]] && continue;
                    currdir=$resultsdir/$project/$casestudy/$technique/$budget/

                    # Process tests number and tests runtime
                    testsnum=""
                    runtime=""
                    objects=0
                    testsnum=0
                    runtime=0
                    linescov=0
                    linesTotal=0
                    branchescov=0
                    branchesTotal=0
                    mutantsKilled=0
                    mutationTotal=0
                    mutationTime=0


                    if [[ $technique == "randoop" ]]; then

                        testreport=$(ls $currdir/surefire-reports/*.txt 2> /dev/null)
                        if [[ $testreport != "" ]]; then
                            testline=$(cat $testreport | grep "Tests run:") 
                            testsnum=$(echo $testline | cut -d',' -f1 | cut -d' ' -f3)
                            runtime=$(echo $testline | cut -d',' -f5 | cut -d' ' -f4)
                        fi
                    else
                        testreport=$(ls $currdir/surefire-reports/*.txt 2> /dev/null)
                        if [[ $testreport != "" ]]; then
                            objects=0
                            objects=$(cat $currdir/log.txt | grep "CountObjects=" | cut -d' ' -f3)
                            testline=$(cat $testreport | grep "Tests run:") 
                            testsnum=$(cat $currdir/testsCount.txt) 
                            runtime=$(echo $testline | cut -d',' -f5 | cut -d' ' -f4)
                        fi

                    fi
                    # Process coverage
                    linesmiss=""                   
                    linescov=""
                    branchesmiss=""
                    branchescov=""
                    covreport=$currdir/jacoco.csv
                    if [[ -f $covreport ]]; then
                        packagename=${casestudy%\.*}
                        linesmiss=$(cat $covreport | grep ",$packagename,"|cut -d',' -f8|awk '{ SUM += $1} END { print SUM }') 
                        linescov=$(cat $covreport | grep ",$packagename,"|cut -d',' -f9|awk '{ SUM += $1} END { print SUM }') 
                        linesTotal=$(($linescov + $linesmiss)) 

                        branchesmiss=$(cat $covreport | grep ",$packagename,"|cut -d',' -f6|awk '{ SUM += $1} END { print SUM }') 
                        branchescov=$(cat $covreport | grep ",$packagename,"|cut -d',' -f7|awk '{ SUM += $1} END { print SUM }') 
                        branchesTotal=$(($branchesmiss + $branchescov))
                    fi

                    # Process mutation
                    pitinstrcov=""
                    pittotalinstr=""
                    pitreport=$(ls $currdir/pit-reports/*/mutations.csv 2> /dev/null)

                    if [[ $pitreport != "" ]]; then
                        # python3 readMutation.py $pitreport $casestudy > aux.txt
                        file="readMutation.py"
                        filesize=$(wc -c "$pitreport" | awk '{print $1}')
                        if (( filesize > 1 )); then #problem with timeout
                            mutants=$(python3 $file $pitreport $casestudy)
                            arrMutants=(${mutants//,/ })
                            mutantsKilled=${arrMutants[0]}
                            mutantsNoKilled=${arrMutants[1]}
                            mutationTotal=$(($mutantsKilled + $mutantsNoKilled))
                            
                            mutationMinutes=$(grep "Total  :" $currdir/log.txt|  cut -d ' ' -f5)
                            mutationSecond=$(grep "Total  :" $currdir/log.txt|  cut -d ' ' -f8)
                            mutationTime=$(($mutationSecond + $mutationMinutes*60))
                        fi 
                    fi
#
                    #echo "Project,Class,Technique,Budget,Tests,Testing time,Lines cov,Lines miss,Branches cov,Branches miss,Lines cov,Lines,Mutants killed,Mutants"
                    #echo "$project,$casestudy,$technique,$budget,$testsnum,$runtime,$linescov,$linesmiss,$branchescov,$branchesmiss,$pitinstrcov,$pittotalinstr,$mutantskilled,$totalmutants" >> $tmpfile
                    echo "$project,$casestudy,$technique,$budget,$objects,$testsnum,$runtime,$linescov,$linesTotal,$branchescov,$branchesTotal,$mutantsKilled,$mutationTotal,$mutationTime" >> $tmpfile

                done
            done
        done
    done
}


echo "Project,Class,Technique,Budget,Objects,Tests,Testing time,Line cov, Line Total, Branches cov, Branches Total, Mutants Killed,Mutants No Killed, Time Mutation"

techniques="randoop randoop-serialize-builders randoop-serialize"
process_results

cat $tmpfile | sort -V
rm $tmpfile

