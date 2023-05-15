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

mkdir -p $resultsdir
explog=$resultsdir/log.txt

pushd $projectdir > /dev/null

echo ""
echo "> Starting experiment: Clean $@"

packagename=${class%\.*}
dirPackage=${packagename//.//}
testSource=$projectdir/src/test/java/

rm $testSource/${dirPackage}/RegressionTest*
