#!/bin/bash

# IMPORTANT: Must set this variable
#export BE_EXP_SRC=


if [ -z $BE_EXP_MAXHEAP ]; then
    BE_EXP_MAXHEAP="8g"
fi

file_to_string() {
    strres=$(cat $1 | grep -v "#" | paste -s -d "$2" \-)
}

measure_mutation() {
    basedir=$1
    casestudy=$2
    muttmpfile="mutationresults.tmp"

    python $BE_EXP_SRC/scripts/parse-results/measure-mutation.py $basedir $casestudy > $muttmpfile

    warn=$(grep "Warning" $muttmpfile)
    [[ $warn != "" ]] && echo $warn

    mkill=$(grep "Killed" $muttmpfile | cut -d' ' -f2) 
    mto=$(grep "Timeout" $muttmpfile | cut -d' ' -f2) 
    msurv=$(grep "Survived" $muttmpfile | cut -d' ' -f2) 
    mnocov=$(grep "No coverage" $muttmpfile | cut -d' ' -f3) 
    mutantskilled=$((mkill+mto))
    totalmutants=$((mkill+mto+msurv+mnocov))
    rm $muttmpfile
}

clean_and_compile() {
    cmd="ant clean clean-auto-tests compile > /dev/null"
    echo ""
    echo "> Cleaning up and compiling project: $cmd"
    bash -c "$cmd"
}

#file_to_string $1 ","
#echo $strres
