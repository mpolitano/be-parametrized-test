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


cmd="timeout 3600 mvn -B test jacoco:report -Dpackage=${packagename} >> $explog"
echo ""
echo "> Running jacoco $cmd"
bash -c "$cmd"
CODE=$?
    if [ $CODE -eq 124 ] || [ $CODE -eq 133 ] 
    then
        echo "Jacoco take more than 3600 to run" >> $explog
    fi    


echo ""
echo "> Saving Jacoco: $cmd"
cmd="cp -r $projectdir/target/site/*/* $resultsdir"
bash -c "$cmd" 
