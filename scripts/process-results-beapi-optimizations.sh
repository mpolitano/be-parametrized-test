#!/bin/bash 
clean_and_compile() {
    cmd="ant clean clean-auto-tests compile > /dev/null"
    echo ""
    echo "> Cleaning up and compiling project: $cmd"
    bash -c "$cmd"
}

source $BE_EXP_SRC/scripts/common.sh

resultsdir=./results-begen/
tmpfile="processresults.csv.tmp"
tmpfilebuilders="builders.txt"
[[ -f $tmpfile ]] && rm $tmpfile
[[ -f $tmpfilebuilders ]] && rm $tmpfilebuilders

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

                if [[ $technique == "beapi/graph/discover" ]]; then
                    echo "$casestudy" >> $tmpfilebuilders
                    cat "$currdir/builders.txt" >> $tmpfilebuilders
                fi

                [[ $budgets == "" ]] && echo "No budgets found in $currdir" && continue;
                for budget in $budgets
                do
                    currdir=$resultsdir/$project/$casestudy/$technique/$budget/
                    [[ ! -d $currdir ]] && continue;
                    logfile=$currdir/log.txt

                    gentime=""
                    structures=""
                    explored=""

                    if [[ $technique == "korat" ]]; then
                        gentime=$(grep "Overall time" $logfile | cut -d' ' -f3)
                        structures=$(grep "New found" $logfile | cut -d':' -f2)
                        explored=$(grep "Total explored" $logfile | cut -d':' -f2)
                    else
                        gentime=$(grep "Bounded exhaustive generation time" $logfile | cut -d' ' -f5)
                        gentime=$(echo "result = (${gentime%??}/1000); scale=2; result / 1" | bc -l)
                        structures=$(grep "Number of builder sequences" $logfile | cut -d' ' -f5)
                        explored=$(grep "Number of sequences explored" $logfile | cut -d' ' -f5)
                    fi
                    echo $gentime >> $tmpfilebuilders
                    echo "$project,$casestudy,$technique,$budget,$gentime,$structures,$explored" >> $tmpfile

                done
            done
        done
    done
}


echo "Project,Class,Technique,Budget,Time,Structures,Explored"

techniques="beapi/graph/builders beapi/graph/discover beapi/graph/no-builders beapi/no-matching/builders beapi/no-matching/discover beapi/no-matching/no-builders"
process_results

cat $tmpfile | sort -V
rm $tmpfile

