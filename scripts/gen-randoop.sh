#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts

source $scriptsdir/common.sh
maxheap=8g
project=$1
class=$2
packagename=${class%\.*}
classname=${class##*\.}
budget=$3
cp=./target/classes:./lib/*.jar
outdir=randoop-tests

pushd $projectsdir/$1 > /dev/null
rm -r randoop-tests/*

echo $BE_EXP_SRC
echo $projectsdir/$1

projectdir=$(pwd)
omitfile=$scriptsdir/config/$1/omitmethods/$class
if [[ -f "$omitfile" ]]; then
    file_to_string $omitfile "|"
    omitmethods="--omit-methods=\"$strres\""
fi

echo ""
echo "> Executing Randoop"

randoop_jar=../lib/randoop-serialize.jar

cmd="java -Xmx$maxheap -ea -cp $randoop_jar:$cp \
randoop.main.Main gentests \
--testclass=$class \
--junit-output-dir=$outdir \
--time-limit=$budget \
--junit-package-name=$packagename \
--only-test-public-members=false \
--forbid-null=true \
--null-ratio=0 \
--testsperfile=300 \
--omit-classes=\"java2.util2.linkedlist.LinkedList.ListItr\" \
$omitmethods

"

echo "$cmd"
bash -c "$cmd"

echo ""
echo "> Randoop finished!"
# rm -r src/test/java/java2/util2/treemap/*
# mv randoop-tests/java2/util2/treemap/*.java src/test/java/java2/util2/treemap/
# ./$scriptsdir/run-pit-beapi.sh 1_java-util java2.util2.treemap.TreeMap $budget
# python3 readMutation.py
popd > /dev/null
