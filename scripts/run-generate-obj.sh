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
# rm -r $testSource
# mkdir $testSource

#Need generate serialize
cmd="$scriptsdir/gen-$tool.sh $project $class $budget graph builders > $explog"
echo ""
echo "> Generating/serialize tests: $cmd"
bash -c "$cmd" 


# mvn -B clean compile 


#move to common.sh
if [[ $tool == "randoop" ]]; then 
	# cmd="cp -r $projectdir/$tool-tests $resultsdir"
	mkdir -p $testSource/${dirPackage}/
	cmd="cp -r $projectdir/$tool-tests/${dirPackage}/Regression* $testSource/${dirPackage}/"
	echo ""
	echo "> Copy tests: $cmd"
	bash -c "$cmd" 
	test=RegressionTest

else #need parameterized tests. Depends with builders or no builders
	sed -i'' -e "s/scope=.*/scope=$budget/g" config.properties
	sed -i'' -e "s/tool=.*/tool=$tool/g" config.properties
	sed -i'' -e "s/clazz=.*/clazz=$class/g" config.properties
	test=${class//*.}Test
	
# 	mkdir -p $testSource/${dirPackage}/
# 	cmd="cp -r $projectdir/testParametrized/${dirPackage}/* $testSource/${dirPackage}/"
# 	echo ""
# 	echo "> Copy tests parameterized: $cmd"
# 	bash -c "$cmd" 

# 	cmd="cp -r $projectdir/testParametrized/utils $testSource/"
# 	echo ""
# 	echo "> Copy utils for tests: $cmd"
# 	bash -c "$cmd" 
fi


cmd="mvn -B test-compile test  -Dtest="${test}" >> $explog"
echo ""
echo "> Running test $cmd"
bash -c "$cmd"
cmd="cp -r $projectdir/target/surefire-reports $resultsdir"
	echo "> Save Test runner $cmd"
	bash -c "$cmd"
	
if [[ $tool == "randoop" ]]; then 
	#change is only parametrized
	cmd="cp -r $projectdir/target/surefire-reports $resultsdir"
	echo ""
	echo "> Save Test runner $cmd"
	bash -c "$cmd"
	
else

	cmd="mv $resultsdir/tests.txt $resultsdir/testsCount.txt"
	echo ""
	echo "> Save Test count $cmd"
	bash -c "$cmd"
fi