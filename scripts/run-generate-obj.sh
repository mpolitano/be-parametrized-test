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
testSource=$projectdir/src/test/java/

if [[ $tool == "randoop" ]]; then 
	#Need generate randoop test
	cmd="$scriptsdir/gen-$tool.sh $project $class $budget graph builders > $explog"
	echo ""
	echo "> Generating/serialize tests: $cmd"
	bash -c "$cmd" 
	# cmd="cp -r $projectdir/$tool-tests $resultsdir"
	mkdir -p $testSource/${dirPackage}/
	cmd="cp -r $projectdir/$tool-tests/${dirPackage}/Regression* $testSource/${dirPackage}/"
	echo ""
	echo "> Copy tests: $cmd"
	bash -c "$cmd" 
	test=RegressionTest

else

	cmd="$scriptsdir/gen-$tool.sh $project $class $budget graph builders > $explog"
	echo ""
	echo "> Generating/serialize tests: $cmd"
	bash -c "$cmd" 
	 #need parameterized tests. Depends with builders or no builders
	sed -i'' -e "s/scope=.*/scope=$budget/g" config.properties
	sed -i'' -e "s/tool=.*/tool=$tool/g" config.properties
	sed -i'' -e "s/clazz=.*/clazz=$class/g" config.properties
	test=${class//*.}Test
	
fi