#!/bin/bash


project=$1
class=$2
budget=$3
tool=$4

# methodnum=$5
# method=$6

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
# echo "$method" > $resultsdir/method.txt

pushd $projectdir > /dev/null

echo ""
echo "> Starting experiment: $@"

clean_and_compile
packagename=${class%\.*}



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
fi

if [[ $tool == "randoop-serialize-builders" ]]; then 
	sed -i'' -e "s/scope=.*/scope=$budget/g" config.properties
	sed -i'' -e "s/tool=.*/tool=$tool/g" config.properties
	cmd="cp -r $projectdir/testParametrized/* $testSource"
fi
if [[ $tool == "randoop-serialize" ]]; then 
	echo "Serialize"
	sed -i'' -e "s/scope=.*/scope=$budget/g" config.properties
	sed -i'' -e "s/tool=.*/tool=$tool/g" config.properties
	cmd="cp -r $projectdir/testParametrized/* $testSource"
fi
	echo ""
	echo "> Saving tests: $cmd"
	bash -c "$cmd" 

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



# if [[ $tool == "be-all" ]]; then 
# 	md="cp -r $projectdir/$tool-tests $resultsdir"
# 	echo ""
# 	echo "> Saving tests: $cmd"
# 	bash -c "$cmd" 
# fi


# cmd="cp -r $projectdir/$tool-tests $resultsdir"
# echo ""
# echo "> Saving tests: $cmd"
	# bash -c "$cmd" 

# cmd="mvn jacoco:report -Dpackage=$packagename >> $explog"
# echo ""
# echo "> Running tests: $cmd"
# bash -c "$cmd"

# cmd="mvn org.pitest:pitest-maven:mutationCoverage -Dpackage=$packagename >> $explog"
# echo ""
# echo "> Running tests: $cmd"
# bash -c "$cmd"

# cmd="ant $tool-coverage >> $explog"
# echo ""
# echo "> Measuring coverage: $cmd"
# bash -c "$cmd"

# Save results
# cp -r build/test-report $resultsdir
# cp -r build/jacoco $resultsdir

# For evosuite to work with pit we have to disable automated mocks. So we have to regenerate tests
# if [[ $tool == "evosuite" ]]; then
#     clean_and_compile
#     param2="nomocks"
#     cmd="$scriptsdir/gen-$tool.sh $project $class $budget $param2 >> $explog"
#     echo ""
#     echo "> Generating tests: $cmd"
#     bash -c "$cmd"

#     cmd="cp -r $projectdir/$tool-tests $resultsdir/$tool-tests-$param2"
#     echo ""
#     echo "> Saving tests: $cmd"
#     bash -c "$cmd" 
# fi

# cmd="ant pit-beapi -Dpackage=$packagename >> $explog"
# echo ""
# echo "> Measuring mutation: $cmd"
# bash -c "$cmd"

# Save results
# cp -r build/pitReports $resultsdir

rm -r $projectdir/target/*
popd > /dev/null

echo ""
echo "> Experiment finished! Results in: $resultsdir"