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


                        testreport=$(ls $currdir/log.txt 2> /dev/null)
                        if [[ $testreport != "" ]]; then
                            testline=$(cat $testreport | grep -A 3 "Results"|grep "Tests run:"|head -1) 
                            testsnum=$(echo $testline | cut -d' ' -f4|cut -d',' -f1)
                            objects=0
                            if [[ $technique != "beapi" ]]; then
                                objects=$(cat $currdir/log.txt | grep "Number of builder sequences" | cut -d' ' -f5)
                            else
                                objects=$(cat $currdir/log.txt | grep "ObjectsSerialize=" | cut -d' ' -f3)                            
                            objectsInvalids=0
                            objectsInvalids=$(cat $currdir/invalidsLock.txt) 

                            runtime=$(cat $testreport | grep "RunTime"|cut -d" " -f2)
                            timeJacoco=$(cat $testreport | grep "JacocoTime"|cut -d" " -f2) 
                            
                            timePit=$(cat $testreport | grep "PitTime"|cut -d" " -f2) 

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

                    echo "$project,$casestudy,$technique,$budget,$objects,$objectsInvalids,$testsnum,$linescov,$linesTotal,$branchescov,$branchesTotal,$mutantsKilled,$mutationTotal,$mutationTime,$runtime,$timeJacoco,$timePit" >> $tmpfile

                done
            done
        done
    done
}


echo "Project,Class,Technique,Budget,Objects,ObjectsInvalid,Tests,Line cov, Line Total, Branches cov, Branches Total, Mutants Killed,Mutants Total, Time Mutation,Testing time, Jacoco Time, PitTime"

# techniques="randoop randoop-serialize-builders randoop-serialize"
techniques="randoop-serialize randoop-builders beapi randoop"
techniques="beapi"

process_results

cat $tmpfile | sort -V
rm $tmpfile

