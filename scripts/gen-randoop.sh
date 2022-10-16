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
cp=./build/classes:./lib/*.jar
outdir=randoop-tests

pushd $projectsdir/$1 > /dev/null
echo "aca toy"
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
--disable-contracts $omitmethods \
--junit-package-name=$packagename \
--only-test-public-members \
--omitmethods=\".*All\(\.*|toString\(\)|fin$classname|repOK\" \
--forbid-null=true \
--null-ratio=0
"

echo "$cmd"
bash -c "$cmd"

echo ""
echo "> Randoop finished!"

popd > /dev/null
