#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh


TO=60m

function run_many1() {
    for project in $projects
    do
        for casestudy in $cases 
        do
            for technique in $techniques 
            do
                for budget in $budgets
                do
                    cmd="timeout $TO ./run-begen-experiment.sh $project $casestudy $technique $budget graph builders"
                    echo "************"
                    echo ">> Executing: $cmd"
                    bash -c "$cmd"
                    if [ $? -eq 124 ]; then 
                        echo ">> Execution timed out"
                        # break;
                        break 3;
                    fi
                done
            done
        done
    done
}

function run_many2() {
    for project in $projects
    do
        for casestudy in $cases 
        do
            for technique in $techniques 
            do
                for budget in $budgets
                do
                    cmd="timeout $TO ./run-begen-experiment.sh $project $casestudy $technique $budget graph discover"
                    echo "************"
                    echo ">> Executing: $cmd"
                    bash -c "$cmd"
                    if [ $? -eq 124 ]; then 
                        echo ">> Execution timed out"
                        # break;
                        break 3;
                    fi
                done
            done
        done
    done
}

function run_many3() {
    for project in $projects
    do
        for casestudy in $cases 
        do
            for technique in $techniques 
            do
                for budget in $budgets
                do
                    cmd="timeout $TO ./run-begen-experiment.sh $project $casestudy $technique $budget graph no-builders"
                    echo "************"
                    echo ">> Executing: $cmd"
                    bash -c "$cmd"
                    if [ $? -eq 124 ]; then 
                        echo ">> Execution timed out"
                        # break;
                        break 3;

                    fi
                done
            done
        done
    done
}

function run_many4() {
    for project in $projects
    do
        for casestudy in $cases 
        do
            for technique in $techniques 
            do
                for budget in $budgets
                do
                    cmd="timeout $TO ./run-begen-experiment.sh $project $casestudy $technique $budget no-matching builders"
                    echo "************"
                    echo ">> Executing: $cmd"
                    bash -c "$cmd"
                    if [ $? -eq 124 ]; then 
                        echo ">> Execution timed out"
                        # break;
                        break 3;
                    fi
                done
            done
        done
    done
}


function run_many5() {
    for project in $projects
    do
        for casestudy in $cases 
        do
            for technique in $techniques 
            do
                for budget in $budgets
                do
                    cmd="timeout $TO ./run-begen-experiment.sh $project $casestudy $technique $budget no-matching no-builders"
                    echo "************"
                    echo ">> Executing: $cmd"
                    bash -c "$cmd"
                    if [ $? -eq 124 ]; then 
                        echo ">> Execution timed out"
                        # break;
                        break 3;
                    fi
                done
            done
        done
    done
}


budgets="3 4 5 6 7 8"




#Korat
projects="1_java-util"
cases="java2.util2.linkedlist.LinkedList java2.util2.treeset.TreeMap java2.util2.treemap.TreeMap java2.util2.hashmap.HashMap"
#faltan:
#korat.examples.disjset.DisjSet korat.examples.heaparray.HeapArray korat.examples.dag.DAG"
techniques="beapi"
# run_many1 ; 
run_many3 ; run_many4 ; run_many5 ;
