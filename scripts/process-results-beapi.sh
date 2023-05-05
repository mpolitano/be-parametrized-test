#!/bin/bash 

source $BE_EXP_SRC/scripts/common.sh

resultsdir=$1
tmpfile="processresults.csv.tmp"
[[ -f $tmpfile ]] && rm $tmpfile

function process_results() {
    projects=$(ls $resultsdir)
    [[ $projects == "" ]] && echo "No proyects found in $currdir" && exit -1;
    for casestudy in $projects
    do
        currdir=$resultsdir/$casestudy
                budgets=$(ls $currdir)
                [[ $budgets == "" ]] && echo "No budgets found in $currdir" && continue;
                for budget in $budgets
                do
                    classname=${casestudy##*\.}
                    basedir=$resultsdir/$casestudy/$budget/
                    [[ ! -d $basedir ]] && continue;
                    currdir=$resultsdir/$casestudy/$budget/
                    echo "$currdir"
                    # Process tests number and tests runtime
                    testsnum=""
                    runtime=""
                    testreport="$currdir/tests.txt"

                    if [[ $testreport != "" ]]; then
                        testsnum=$(cat $testreport) 
                    fi
                    testline="$currdir/time.txt"
                    if [[ $testreport != "" ]]; then
                        runtime=$(cat $testline)

                    fi

                    # Process coverage
                    linesmiss=""                   
                    linescov=""
                    branchesmiss=""
                    branchescov=""
                    covreport=$currdir/jacocoTestReport.csv
                    if [[ -f $covreport ]]; then
                        covline=$(cat $covreport | grep ",$classname,")
                        linesmiss=$(echo $covline | cut -d',' -f9)
                        linescov=$(echo $covline | cut -d',' -f9)
                        branchesmiss=$(echo $covline | cut -d',' -f6)
                        branchescov=$(echo $covline | cut -d',' -f7)
                    fi
                    echo "$project,$casestudy,$technique,$budget,$testsnum,$runtime,$linescov,$branchescov,$mutantskilled,$totalmutants" >> $tmpfile
        done
    done
}


echo "Project,Class,Technique,Budget,Tests,Testing time,Instr cov,Branches cov,Mutants killed,Mutants total"

techniques="randoop-serialize"
process_results

cat $tmpfile | sort -V
rm $tmpfile

