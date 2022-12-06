#!/bin/bash

project=$1
class=$2
budget=$3
tool=$4

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh

projectdir=$projectsdir/$project
resultsdir=$scriptsdir/results/$project/$class/$tool/$budget/

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
rm -r $testSource
mkdir $testSource


#Need generate serialize
cmd="$scriptsdir/gen-$tool.sh $project $class $budget graph builders > $explog"
echo ""
echo "> Generating/serialize tests: $cmd"
bash -c "$cmd" 


if [[ $tool == "randoop" ]]; then 
	# cmd="cp -r $projectdir/$tool-tests $resultsdir"
	cmd="cp -r $projectdir/$tool-tests/* $testSource"
else #need parameterized tests. Depends with builders or no builders
	sed -i'' -e "s/scope=.*/scope=$budget/g" config.properties
	sed -i'' -e "s/tool=.*/tool=$tool/g" config.properties
	sed -i'' -e "s/clazz=.*/clazz=$clazz/g" config.properties
	
	mkdir -p $testSource/${dirPackage}/
	cmd="cp -r $projectdir/testParametrized/${dirPackage}/* $testSource/${dirPackage}/"
	echo ""
	echo "> Copy tests parameterized: $cmd"
	bash -c "$cmd" 

	cmd="cp -r $projectdir/testParametrized/utils $testSource/"
	echo ""
	echo "> Copy utils for tests: $cmd"
	bash -c "$cmd" 
fi


# cmd="mvn clean 	test >> $explog"
# echo ""
# echo "> Running tests, Coverage and Mutation: $cmd"
# bash -c "$cmd"

cmd="mvn clean test-compile test  >> $explog"
echo ""
echo "> Running test $cmd"
bash -c "$cmd"

cmd="cp -r $resultsdir/tests.txt $resultsdir/testsCount.txt"
echo ""
echo "> Save Test count $cmd"
bash -c "$cmd"

cmd="cp -r $projectdir/target/surefire-reports $resultsdir"
echo ""
echo "> Save Test runner $cmd"
bash -c "$cmd"


cmd="mvn test jacoco:report -Dpackage=${packagename} >> $explog"
echo ""
echo "> Running jacoco $cmd"
bash -c "$cmd"


echo ""
echo "> Saving Jacoco: $cmd"
cmd="cp -r $projectdir/target/site/*/* $resultsdir"
bash -c "$cmd" 


cmd="timeout 3600 mvn clean test-compile org.pitest:pitest-maven:mutationCoverage -Dpackage=${packagename} >> $explog"
echo ""
echo "> Running pit $cmd"
bash -c "$cmd"


cmd="cp -r $projectdir/target/pit-reports $resultsdir"
echo ""
echo "> Saving pit: $cmd"
bash -c "$cmd" 

rm -r $projectdir/target/*
popd > /dev/null

echo ""
echo "> Experiment finished! Results in: $resultsdir"