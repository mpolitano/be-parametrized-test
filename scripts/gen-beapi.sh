#!/bin/bash

set_config_options() {
#     if [[ $config == "DEFAULT" ]]
# then
#     options_builders="builders"
#     options_state_matching="matching"
# elif [[ $config == "SM" ]]
# then
#     options_builders="no-builders"
#     options_state_matching="matching"
# elif [[ $config == "BLD" ]]
# then
#     options_builders="builders"
#     options_state_matching="no-matching"
# elif [[ $config == "NoOpt" ]]
# then
#     options_builders="no-builders"
#     options_state_matching="no-matching" 
#  else
#     echo "Unknown optimization option: $config"
#     exit 1
# fi
    beopts=""
    if [[ $matching == "graph" ]]
    then
        beopts=" --filtering=BEALL --canonicalizer-cfg=$scriptsdir/properties/scope${scope}.all.canonicalizer.properties"

    elif [[ $matching == "abstraction" ]]
    then
        abstractionfile=$scriptsdir/config/$project/abstraction/$class
        file_to_string $abstractionfile ","
        abstractionstr=$strres
        beopts=" --filtering=BEABS --canonicalizer-cfg=$scriptsdir/properties/scope${scope}.canonicalizer.properties --abstraction=\"$abstractionstr\""
    elif [[ $matching == "no-matching" ]]
    then
        beopts=" --filtering=NO"
    else
        echo "Unknown state matching option: $matching"
        exit 1
    fi

    if [[ $builders == "builders" ]]
    then
        buildersfile=$scriptsdir/config/$project/builders-beapi/$class
        file_to_string $buildersfile "|"
        buildersstr=$strres
        beopts="$beopts --builder-methods=\"$buildersstr\""
    elif [[ $builders == "discover" ]]
    then
        beopts="$beopts --builders-at-length=5 --output-computed-builders=builders.txt"
    elif [[ $builders == "no-builders" ]]
    then
        beopts="$beopts"
    else
        echo "Unknown state matching option: $matching"
        exit 1
    fi
}

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts

source $scriptsdir/common.sh

maxheap=$BE_EXP_MAXHEAP

project=$1
class=$2
packagename=${class%\.*}
classname=${class##*\.}
scope=$3
matching=$4
builders=$5
#method=$4
timelimit=36000
#testlimit=1000000
omitmethods=""

maxBEit=$((scope + scope + scope)) # Up to XX iterations in the first stage of BE
maxsize=$((maxBEit + maxBEit + maxBEit)) # Up to XX methods in a JUnit test

literals="$scriptsdir/literals/literals${scope}.txt"
bejar="../lib/be.jar"
#regexmethod=".*"
regexmethod=$method

if [ $project == "7_kiasan" ] && [ $class == "stack.array.StackAr" ]; then
	forbidnull=false
else
	forbidnull=true
fi

pushd $projectsdir/$1 > /dev/null

set_config_options
cp=./target/classes:../lib/korat/korat.jar
outdir=./beapi-tests
# serializeDir=serialize/$class/$scope/
serializeDir=serialize/$class/$scope/beapi

mkdir -p $serializeDir
omitfile=$scriptsdir/config/$project/omitmethods/$class
if [[ -f "$omitfile" ]]; then
   file_to_string $omitfile "|"
   omitmethods="--omitmethods=\"$strres\""
fi

echo ""
echo "> Executing BE"

cmd="java -Xmx$maxheap -ea -cp $bejar:$cp randoop.main.Main gentests \
--testclass=$class \
--junit-output-dir=$outdir \
--usethreads=false \
--instance-generics-integer \
--disable-contracts \
--forbid-null=$forbidnull \
--timelimit=$timelimit \
--literals-level=ALL \
--literals-file=$literals \
--max-BE-iterations=$maxBEit \
--all-sequences=false \
--BEmaxsize=$maxsize \
--junit-package-name=$packagename \
--instance-object-integer \
--keep-only-builder-seqs \
--efficient-extensions \
--only-test-public-members $beopts \
--discard-generation-seqs \
--dont-output-tests=true \
--serialize-class-xml=$class \
--inputs_xml_file=$serializeDir/randoop.xml \
$omitmethods

" 
#--id-triples-extensions \
#--output_computed_extensions=computedExt.txt \
#--output_gen_seqs_objs=seqObj.txt
#--assert-methods=\"$regexmethod\" \
#--second-phase-save-all \
# --serialize-class-xml=$serializeDir/randoop.xml \


echo "$cmd"
bash -c "$cmd"

echo ""
echo "> BE finished!"


popd > /dev/null
