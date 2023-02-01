#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh

function run_many() {
    for project in $projects
    do
        for casestudy in $cases 
        do
            for technique in $techniques 
            do
                methodsfile=$scriptsdir/config/$project/methods/$casestudy
                for budget in $scopes
                do
                        ./run-tests-coverage.sh "$project" "$casestudy" "$budget" "$technique"
                done
            done
        done
    done
}
scopes="5"


projects="1_java-util"
# cases="java2.util2.hashmap.HashMap"
cases="java2.util2.linkedlist.LinkedList"
techniques="randoop-serialize-builders"
run_many

projects="3_builders"
cases="builders.Schedule"
# run_many

projects="2_apache"
cases="org.apache.commons.collections4.list.NodeCachingLinkedList"
scopes="3"
# run_many
