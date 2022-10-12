#!/bin/bash
projectsdir=$BE_EXP_SRC
scriptsdir=$projectsdir/scripts

project=$1
class=$2
scope=$3

pushd $projectsdir/$project > /dev/null

KORAT_CP=./build/classes/:../lib/korat/*:
java -cp $KORAT_CP korat.Korat --class $class --args $scope
--serialize korat/$class/$scope.objects.ser
#--visualize \
#--printCandVects \
#--cvFile korat.csv \

popd > /dev/null

