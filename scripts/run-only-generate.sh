#!/bin/bash

project=$1
class=$2
tool=$3
budget=$4

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh

projectdir=$projectsdir/$project
resultsdir=$scriptsdir/results/$project/$class/$tool/$budget

if [[ -d $resultsdir ]]; then
    rm -rf $resultsdir
fi
mkdir -p $resultsdir
explog=$resultsdir/log.txt

pushd $projectdir > /dev/null

echo ""
echo "> Starting experiment: $@"

packagename=${class%\.*}
dirPackage=${packagename//.//}
# testSource=$projectdir/src/test/java/
# rm -r $testSource
# mkdir $testSource

popd > /dev/null

#Need generate serialize
./run-generate-obj.sh $project $class $tool $budget


echo ""
echo "> Experiment finished! Results in: $resultsdir"