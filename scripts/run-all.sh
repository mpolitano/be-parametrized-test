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
                for budget in $scopes
                do
                        ./run-tests-coverage.sh "$project" "$casestudy" "$technique" "$budget"
                done
            done
        done
    done
}
scopes="4"


projects="1_java-util"
cases="java2.util2.treemap.TreeMap java2.util2.treemap.TreeSet java2.util2.hashmap.HashMap java2.util2.hashmap.HashSet java2.util2.linkedlist.LinkedList"
techniques="randoop"
scopes="5 10 20 40 150 300"
run_many

techniques="randoop-builders"
scopes="5 10 20 40 150"
run_many

techniques="randoop-serialize"
scopes="5 10 20 40 150"
# run_many

techniques="beapi"
scopes="3 4 5 6 7 8"
# run_many


projects="2_apache"
cases="org.apache.commons.collections4.list.NodeCachingLinkedList"
techniques="randoop"
scopes="5 10 20 40 150 300"
run_many

techniques="randoop-builders"
scopes="5 10 20 40 150 300"
run_many

techniques="randoop-serialize"
scopes="5 10 20 40 150"
# run_many

techniques="beapi"
scopes="3 4 5 6 7 8"
# run_many


