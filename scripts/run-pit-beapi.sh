#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts

source $scriptsdir/common.sh

TO=60m

maxheap=12g
project=$1
class=$2
packagename=${class%\.*}
classname=${class##*\.}
budget=$3

function run_many() {
	for budget in $budgets
    do
	    for project in $projects
	    do
            pushd $projectsdir/$project > /dev/null
            sed -i'' -e "s/scope=.*/scope=$budget/g" config.properties
	        for casestudy in $cases 
        	do
        		pathPitResult="pit/$casesstudy/$budget/"
        		mkdir -p pit-result/
        		mkdir -p $pathPitResult
        		packagename=${casestudy%\.*}
        		sed -i'' -e "s/targetClasses = .*/targetClasses = ['$packagename*']/g" build.gradle
        		# sed -i'' -e "s/reportDir = .*/targetClasses = [$packagename*]/g" build.gradle
                cmd="./gradlew clean"
                echo "************"
                echo ">> Executing: $cmd"
                bash -c "$cmd"
                cmd="./gradlew pitest"
                echo "************"
                echo ">> Executing: $cmd"
                bash -c "$cmd"
                cp pit-results/* $pathPitResult
                rm -r pit-results/*
                # if [ $? -eq 124 ]; then 
                #     echo ">> Execution timed out"
                #     # break;
                #     break 3;
                # fi
                done
            popd > /dev/null
        done
    done

}


#to run a single case
budgets="3"
projects="1_java-util"
cases="java2.util2.treemap.TreeMap"
run_many