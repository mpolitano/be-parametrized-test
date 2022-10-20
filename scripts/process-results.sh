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
                    testreport=$(ls $currdir/test-report/*.txt 2> /dev/null)
                    if [[ $testreport != "" ]]; then
                        testline=$(cat $testreport | grep "Tests run:") 
                        testsnum=$(echo $testline | cut -d',' -f1 | cut -d' ' -f3)
                        runtime=$(echo $testline | cut -d',' -f5 | cut -d' ' -f4)
                    fi

                    # Process coverage
                    linesmiss=""                   
                    linescov=""
                    branchesmiss=""
                    branchescov=""
                    covreport=$currdir/jacoco/site/jacoco/report.csv
                    if [[ -f $covreport ]]; then
                        covline=$(cat $covreport | grep ",$classname,")
                        linesmiss=$(echo $covline | cut -d',' -f9)
                        linescov=$(echo $covline | cut -d',' -f5)
                        branchesmiss=$(echo $covline | cut -d',' -f6)
                        branchescov=$(echo $covline | cut -d',' -f7)
                    fi

                    # Process mutation
                    #pitinstrcov=""
                    #pittotalinstr=""
                    #mutantskilled=""
                    #totalmutants=""
                    #pitreport=$(ls $currdir/pitReports/*/*/index.html 2> /dev/null)
                    #if [[ $pitreport != "" ]]; then
                    #    caselinenum=$(grep -n ">$classname.java<" $pitreport | cut -d':' -f1)
                    #    instrline=$(sed -n $(($caselinenum+1))p $pitreport)
                    #    mutationline=$(sed -n $(($caselinenum+2))p $pitreport)
                    #    pitinstrcov=$(echo $instrline | cut -d'<' -f8 | cut -d'>' -f2 | cut -d'/' -f1) 
                    #    pittotalinstr=$(echo $instrline | cut -d'<' -f8 | cut -d'>' -f2 | cut -d'/' -f2) 
                    #    mutantskilled=$(echo $mutationline | cut -d'<' -f8 | cut -d'>' -f2 | cut -d'/' -f1) 
                    #    totalmutants=$(echo $mutationline | cut -d'<' -f8 | cut -d'>' -f2 | cut -d'/' -f2) 
                    #fi

                    measure_mutation $basedir $casestudy

                    #echo "Project,Class,Technique,Budget,Tests,Testing time,Lines cov,Lines miss,Branches cov,Branches miss,Lines cov,Lines,Mutants killed,Mutants"
                    #echo "$project,$casestudy,$technique,$budget,$testsnum,$runtime,$linescov,$linesmiss,$branchescov,$branchesmiss,$pitinstrcov,$pittotalinstr,$mutantskilled,$totalmutants" >> $tmpfile
                    echo "$project,$casestudy,$technique,$budget,$testsnum,$runtime,$linescov,$branchescov,$mutantskilled,$totalmutants" >> $tmpfile

                done
            done
        done
    done
}


echo "Project,Class,Technique,Budget,Tests,Testing time,Instr cov,Branches cov,Mutants killed,Mutants total"

techniques="randoop"
process_results

cat $tmpfile | sort -V
rm $tmpfile

