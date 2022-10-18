#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh
function run_many() {
    for project in $projects
    do
        for scope in $scopes
        do
            echo "Scope"
            echo $scope
            pushd $projectsdir/$project > /dev/null
            sed -i'' -e "s/scope=.*/scope=$scope/g" config.properties
            ant
            popd > /dev/null
            # for technique in $techniques 
            # do
                methodsfile=$scriptsdir/config/$project/methods/$casestudy
                for casestudy in $cases 
                do
                    # methodnum=1
                    # for method in $methods
                    # do

                        packagename=${casestudy%\.*}
                        classname=${casestudy##*\.}

                        jacocoDirReport=$BE_EXP_SRC/$project/build/reports/jacoco/test


                        SECONDS=0
                        cmd="./gen-beapi-serialize-inputs.sh $project $casestudy $scope graph builders"
                        echo "$cmd"
                        bash -c "$cmd"
                        pushd $projectsdir/$project > /dev/null
                        cmd="./gradlew test --tests ${packganme}${classname}Test jacocoTestReport -Dorg.gradle.jvmargs="-Xss4m""
                        echo "$cmd"
                        bash -c "$cmd"
                        # ./gradlew clean test "$project" "$casestudy" "$budget"
                        popd > /dev/null
                        dirOutput="reportBEAPI/$casestudy/$scope"
                        touch $dirOutput/time.txt
                        echo $SECONDS > $dirOutput/time.txt
                        [ -d $dirOutput ] || mkdir -p $dirOutput
                        cp -r $jacocoDirReport/* $dirOutput
                    # done
                done
            # done
        done
    done
}


projects="1_java-util"
# cases="java2.util2.linkedlist.LinkedList java2.util2.treeset.TreeMap java2.util2.treemap.TreeMap java2.util2.hashmap.HashMap"
cases="java2.util2.treemap.TreeMap"
scopes="3 4 5 6 7 8"
run_many
projects="2_apache"
cases="org.apache.commons.collections4.list.NodeCachingLinkedList"
scopes="3 4"
# run_many
projects="3_builders"
cases="builders.Schedule"
# cases="java2.util2.treeset.TreeSet java2.util2.treeset.TreeMap java2.util2.hashmap.HashMap java2.util2.linkedlist.LinkedList"
# techniques="randoop evosuite"
# budgets="60 120 180 300 600"
scopes="3 4"
#run_many
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


