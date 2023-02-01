#!/bin/bash
project=$1
class=$2
tool=$3
budget=$4

packagename=${class%\.*}

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh

projectdir=$projectsdir/$project
resultsdir=$scriptsdir/results/$project/$class/$tool/$budget

# if [[ -d $resultsdir ]]; then
#     rm -rf $resultsdir
# fi
mkdir -p $resultsdir
explog=$resultsdir/log.txt

pushd $projectdir > /dev/null

cmd="timeout 3600 mvn -B clean test-compile org.pitest:pitest-maven:mutationCoverage -Dpackage=${packagename} >> $explog"
echo ""
echo "> Running pit $cmd"
bash -c "$cmd"
CODE=$?
    if [ $CODE -eq 124 ] || [ $CODE -eq 133 ] 
    then
        echo "Pitest take more than 3600 to run" >> $explog
    fi    


cmd="cp -r $projectdir/target/pit-reports $resultsdir"
echo ""
echo "> Saving pit: $cmd"
bash -c "$cmd" 

rm -r $projectdir/target/* 
