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
cases="java2.util2.linkedlist.LinkedList java2.util2.treeset.TreeMap java2.util2.treemap.TreeMap java2.util2.hashmap.HashMap"
# cases="java2.util2.hashmap.HashMap"
scopes="10 30 60 120 180 300 600"
run_many

projects="3_builders"
cases="builders.Schedule"
scopes="10 30 60 120 180 300 600"
run_many

cases="org.apache.commons.collections4.list.NodeCachingLinkedList"
projects="2_apache"
scopes="10 30 60 120 180 300 600"
run_many