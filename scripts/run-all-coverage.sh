#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh

function run_many() {
    for project in $projects
    do
        for casestudy in $cases 
        do
            # for technique in $techniques 
            # do
                methodsfile=$scriptsdir/config/$project/methods/$casestudy
                for budget in $budgets
                do
                    # methodnum=1
                    # for method in $methods
                    # do
                        ./run-coverage-randoop.sh "$project" "$casestudy" "$budget"
                    # done
                done
            # done
        done
    done
}


projects="1_java-util"
cases="java2.util2.linkedlist.LinkedList"

# cases="java2.util2.treeset.TreeSet java2.util2.treeset.TreeMap java2.util2.hashmap.HashMap java2.util2.linkedlist.LinkedList"
# techniques="randoop evosuite"
# budgets="60 120 180 300 600"
budgets="10"
run_many
# methods="all"
#run_many

# techniques="be"
# budgets="3 4 5"
# #run_many

# techniques="be-all"
# budgets="3 4"
# #run_many


# projects="3_java-util-HashMap"
# cases="java2.util2.HashMap"
# techniques="randoop evosuite"
# budgets="180 600"
# methods="all"
# # run_many

# techniques="be"
# budgets="3 4"
# # run_many

# techniques="be-all"
# budgets="3 4"
# # run_many


# projects="4_java-util-TreeMap"
# cases="java2.util2.TreeMap"
# techniques="randoop evosuite"
# budgets="180 600"
# methods="all"
# # run_many

# techniques="be"
# budgets="3 4"
# # run_many


# projects="6_paper-builders"
# cases="builders.Lits"
# techniques="randoop"
# budgets="600"
# methods="all"
# run_many

# techniques="be-all"
# budgets="3 4"
# run_many


