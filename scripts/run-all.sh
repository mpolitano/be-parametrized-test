#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh

# function run_randoop() {
#     for project in $projects
#     do
#         for casestudy in $cases 
#         do
#             # for technique in $techniques 
#             # do
#                 methodsfile=$scriptsdir/config/$project/methods/$casestudy
#                 for budget in $scopes
#                 do
#                     # methodnum=1
#                     # for method in $methods
#                     # do
#                         ./run-coverage-randoop.sh "$project" "$casestudy" "$budget" "randoop"
#                     # done
#                 done
#             # done
#         done
#     done
# }

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

                        ./run-coverage-randoop.sh "$project" "$casestudy" "$budget" "$technique"
                done
            done
        done
    done
}

projects="1_java-util"

# projects="2_apache"
# cases="java2.util2.linkedlist.LinkedList java2.util2.treeset.TreeMap java2.util2.treemap.TreeMap java2.util2.hashmap.HashMap"
cases="java2.util2.linkedlist.LinkedList java2.util2.treemap.TreeMap"
# cases="org.apache.commons.collections4.list.NodeCachingLinkedList"

#scopes="60 300 600"
# techniques="randoop randoop-serialize-builders randoop-serialize"
# techniques="randoop-serialize-builders "
# techniques="randoop"

techniques="randoop-serialize-builders randoop-serialize"
# scopes="5"
scopes="10 30 60 120 180"
run_many

projects="3_builders"
cases="builders.Schedule"
scopes="10 30 60 120 180 300 600"
# run_many

cases="org.apache.commons.collections4.list.NodeCachingLinkedList"
projects="2_apache"
scopes="10 30 60 120 180 300 600"
# run_many
