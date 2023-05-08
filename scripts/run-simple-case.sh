#!/bin/bash

projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts
source $scriptsdir/common.sh

project=$1
casestudy=$2
technique=$3
budget=$4

./run-tests-coverage.sh "$project" "$casestudy" "$technique" "$budget"

