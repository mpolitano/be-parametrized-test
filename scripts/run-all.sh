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
cases="java2.util2.treemap.TreeMap java2.util2.hashmap.HashMap java2.util2.linkedlist.LinkedList"
techniques="randoop-serialize randoop"
scopes="20 60 120 300"
run_many

projects="1_java-util"
cases="java2.util2.treemap.TreeMap java2.util2.hashmap.HashMap java2.util2.linkedlist.LinkedList"
techniques="randoop-builders"
scopes="5 10 20"
run_many

projects="1_java-util"
cases="java2.util2.treemap.TreeMap java2.util2.treemap.TreeSet java2.util2.hashmap.HashMap java2.util2.hashmap.HashSet java2.util2.linkedlist.LinkedList"
techniques="beapi"
scopes="3 4 5 6"
run_many



projects="2_apache"
cases="org.apache.commons.collections4.list.NodeCachingLinkedList"
techniques="randoop-serialize randoop-builders randoop"
# techniques="beapi"
scopes="10"
# scopes="4"
# run_many

projects="2_apache"
cases="org.apache.commons.collections4.list.NodeCachingLinkedList"
techniques="randoop-serialize randoop-builders randoop"
techniques="beapi"
# scopes="10"
scopes="3"
# run_many

