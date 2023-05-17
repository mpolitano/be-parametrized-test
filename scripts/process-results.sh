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
                    genTime=0
                    objectsInvalids=0

                        testreport=$(ls $currdir/log.txt 2> /dev/null)
                        if [[ $testreport != "" ]]; then
                            testline=$(cat $testreport | grep -A 3 "Results"|grep "Tests run:"|head -1) 
                            objects=0
                            if [[ $technique == "beapi" ]]; then
                                objects=$(cat $currdir/log.txt | grep "Number of builder sequences" | cut -d' ' -f5)
                            else
                                objects=$(cat $currdir/log.txt | grep "ObjectsSerialize=" | cut -d' ' -f3)                            
                            fi
                            objectsInvalids=0
                            objectsInvalids=$(cat $currdir/invalidsLock.txt) 

                            if [[ $technique == "randoop" ]]; then

                                testsnum=$(echo $testline | cut -d' ' -f4|cut -d',' -f1)
                            else

                                testsnum=0
                                testsnum=$(cat $currdir/testsLock.txt) 
                            fi
                            objectsInvalids=$(cat $currdir/invalidsLock.txt)
                            if [[ $technique == "beapi" ]]; then
                                genTimeMs=$(cat $currdir/log.txt | grep "Bounded exhaustive generation time" | cut -d' ' -f5)
                                genTimeMs=${genTimeMs%?};
                                genTime=${genTimeMs%?};
                                genTime=$(echo ${genTime}/1000 | bc)

                            fi
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
                        file="readJacoco.py"
                        filesize=$(wc -c "$covreport" | awk '{print $1}')
                        if (( filesize > 1 )); then #problem with timeout
                            coverage=$(python3 $file $covreport $packagename)
                            coveragearray=($coverage)
                            linescov=${coveragearray[0]}
                            linesTotal=${coveragearray[1]}
                            branchescov=${coveragearray[2]}
                            branchesTotal=${coveragearray[3]}
                        fi 

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
                            #     mutationMinutes=$(grep "Total  :" $currdir/log.txt|  cut -d ' ' -f5)
                            #     mutationSecond=$(grep "Total  :" $currdir/log.txt|  cut -d ' ' -f8)
                            # if [[ -z "$mutationMinutes" ]]; then
                            #     mutationTime=$(($mutationSecond + $mutationMinutes*60))
                            # fi
                        fi 
                    fi
#

                    echo "$project,$casestudy,$technique,$budget,$objects,$objectsInvalids,$testsnum,$linescov,$branchescov,$mutantsKilled,$runtime,$genTime" >> $tmpfile

                done
            done
        done
    done
}


echo "Project,Class,Technique,Budget,Objects,ObjectsInvalid,Tests,Line cov,Branches cov, Mutants Killed,Testing time,GenTime"

# techniques="randoop randoop-serialize-builders randoop-serialize"
techniques="randoop-serialize randoop-builders beapi randoop"
# techniques="beapi randoop-serialize"

process_results

cat $tmpfile | sort -V
rm $tmpfile

