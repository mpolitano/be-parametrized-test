#!/bin/bash


project=$1
class=$2
tool="randoop"
budget=$3
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

[[ $tool == "be-all" ]] && tool="be"

echo ""
echo "> Starting experiment: $@"

clean_and_compile

cmd="$scriptsdir/gen-$tool.sh $project $class $budget > $explog"
echo ""
echo "> Generating tests: $cmd"
bash -c "$cmd" 

cmd="cp -r $projectdir/$tool-tests $resultsdir"
echo ""
echo "> Saving tests: $cmd"
bash -c "$cmd" 

cmd="ant $tool-test >> $explog"
echo ""
echo "> Running tests: $cmd"
bash -c "$cmd"

cmd="ant $tool-coverage >> $explog"
echo ""
echo "> Measuring coverage: $cmd"
bash -c "$cmd"

# Save results
cp -r build/test-report $resultsdir
cp -r build/jacoco $resultsdir

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

# cmd="ant pit-$tool >> $explog"
# echo ""
# echo "> Measuring mutation: $cmd"
# bash -c "$cmd"

# Save results
# cp -r build/pitReports $resultsdir

popd > /dev/null

echo ""
echo "> Experiment finished! Results in: $resultsdir"

