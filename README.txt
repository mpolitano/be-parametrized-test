Setup
-----

Must set environment variable BE_EXP_SRC to the current directory before running the experiments.

For example, by executing:

export BE_EXP_SRC=$(pwd)


Folders structures:
####

###




To run the experiments:
-----------------------	 

For execute one case and generate the serialize objects and measure coverage with these objects, you can run:

'./run-simple-case.sh <project> <case> <tool> <budget>'

For example:

'./run-simple-case.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-builders 180'

The possibilities for execute cases are:
'./run-simple-case.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-builders 180'
'./run-simple-case.sh 1_java-util java2.util2.linkedlist.LinkedList beapi 5'
'./run-simple-case.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-serialize 180'
'./run-simple-case.sh 1_java-util java2.util2.linkedlist.LinkedList randoop 180'




-First, you must create the objects that will feed the parameterized tests.
For this purpose you can execute:

'./run-generate-obj.sh <project> <case> <tool> <budget>'

For example:
'./run-generate-obj.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-builders 180'

For generate objects for all cases:

'./run-generate-all.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-builders 180'

-To measure branch and line coverage, you can run the following script:

'./run-coverage-jacoco.sh <project> <case> <tool> <budget>'

For example:
'./run-coveraje-jacoco.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-builders 180'

The results of the script are saved in a folder:

$BE_EXP_SRC/scripts/results/<project>/<case>/<tool>/<budget>



-To measure mutation coverage, you can run the following script:

'./run-mutation-pit.sh <project> <case> <tool> <budget>'

For example:
'./run-mutation-pit.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-serialize-builders 180'

The results of the script are saved in a folder:

$BE_EXP_SRC/scripts/results/<project>/<case>/<tool>/<budget>/pit-report/*/

