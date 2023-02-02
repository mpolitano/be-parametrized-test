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

-First, you must create the objects that will feed the parameterized tests.
For this purpose you can execute:

'./run-generate-obj.sh <project> <case> <tool> <budget>'

For example:
'./run-generate-obj.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-serialize-builders 5'

-To measure branch and line coverage, you can run the following script:

'./run-coverage-jacoco.sh <project> <case> <tool> <budget>'

For example:
'./run-coveraje-jacoco.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-serialize-builders 5'

The results of the script are saved in a folder:

$BE_EXP_SRC/scripts/results/<project>/<case>/<tool>/<budget>



-To measure mutation coverage, you can run the following script:

'./run-mutation-pit.sh <project> <case> <tool> <budget>'

For example:
'./run-mutation-pit.sh 1_java-util java2.util2.linkedlist.LinkedList randoop-serialize-builders 5'

The results of the script are saved in a folder:

$BE_EXP_SRC/scripts/results/<project>/<case>/<tool>/<budget>/pit-report/*/

