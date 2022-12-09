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
                        covline=$(cat $covreport | grep ",$classname,")
                        linesmiss=$(echo $covline | cut -d',' -f8)
                        linescov=$(echo $covline | cut -d',' -f9)
                        branchesmiss=$(echo $covline | cut -d',' -f6)
                        branchescov=$(echo $covline | cut -d',' -f7)
                    fi

                    # Process mutation
                    pitinstrcov=""
                    pittotalinstr=""
                    pitreport=$(ls $currdir/pit-reports/*/mutations.csv 2> /dev/null)
                    if [[ $pitreport != "" ]]; then
                        # python3 readMutation.py $pitreport $casestudy > aux.txt
                        file="readMutation.py"
                        mutantskilled=$(python3 $file $pitreport $casestudy)
                    fi
#
                    #echo "Project,Class,Technique,Budget,Tests,Testing time,Lines cov,Lines miss,Branches cov,Branches miss,Lines cov,Lines,Mutants killed,Mutants"
                    #echo "$project,$casestudy,$technique,$budget,$testsnum,$runtime,$linescov,$linesmiss,$branchescov,$branchesmiss,$pitinstrcov,$pittotalinstr,$mutantskilled,$totalmutants" >> $tmpfile
                    echo "$project,$casestudy,$technique,$budget,$testsnum,$runtime,$linescov,$branchescov,$mutantskilled" >> $tmpfile

                done
            done
        done
    done
}


echo "Project,Class,Technique,Budget,Tests,Testing time,Line cov,Branches cov,Mutants killed,Mutants total"

techniques="randoop randoop-serialize-builders randoop-serialize"
process_results

cat $tmpfile | sort -V
rm $tmpfile

