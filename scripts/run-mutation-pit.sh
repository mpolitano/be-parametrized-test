	#!/bin/bash
project=$1
class=$2
tool=$3
budget=$4

packagename=${class%\.*}
dirPackage=${packagename//.//}

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
#move to common.sh
if [[ $tool == "randoop" ]]; then 
	# cmd="cp -r $projectdir/$tool-tests $resultsdir"
	mkdir -p $testSource/${dirPackage}/
	cmd="cp -r $projectdir/$tool-tests/${dirPackage}/Regression* $testSource/${dirPackage}/"
	echo ""
	echo "> Copy tests: $cmd"
	bash -c "$cmd" 
	test=${packagename}.RegressionTest*

else #need parameterized tests. Depends with builders or no builders
	sed -i'' -e "s/scope=.*/scope=$budget/g" config.properties
	sed -i'' -e "s/tool=.*/tool=$tool/g" config.properties
	sed -i'' -e "s/clazz=.*/clazz=$class/g" config.properties
	test=${packagename}.${class//*.}Test
fi

SECONDS=0
cmd="timeout 3600 mvn clean test-compile org.pitest:pitest-maven:mutationCoverage  -Dtest=${test} -Dpackage=${packagename} >> $explog"
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

echo "$SECONDS"
echo "PitTime: $SECONDS" >> $explog

# rm -r $projectdir/target/* 
