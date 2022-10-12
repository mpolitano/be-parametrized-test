Setup
-----

Must set environment variable BE_EXP_SRC to the current directory before running the experiments.

For example, by executing:

export BE_EXP_SRC=$(pwd)

It is also needed to set environment variable BE_EXP_MAXHEAP

export BE_EXP_MAXHEAP="15g"


To run the experiments
----------------------

-You can find one folder for each of the four benchmark used in this analysis named 0_korat, 9_fajita, 8_roops and 7_kiasan.
-In folder scripts we place the scripts to reproduce all experiments to assess BEAPI.

-To reproduce the experiments to compare BEAPI (with enabled optimizations) with Korat, you can run the script beapi-vs-	korat.sh. Log files after runs for each case study, technique and scope are located in folder scripts/results-begen.  To process these results and obtain the data in a format similar to that presented in the article, you can run the  script  process-results-beapi-vs-korat.sh

-To replicate the experiments to compare different configuration (State Matching enabled and disabled, builders identification enabled and disabled) of BEAPI for all data structures you can run the script beapi-optimizations.sh. Log files after runs for each case study, technique and scope are located in folder scripts/results-begen.  To process these results and obtain the data in a format similar to that presented in the article, you can run the  script process-results-beapi-optimizations.

-To replicate the experiments to analyze repOKs provided for each class, you can run the script run-all-begen-serialize-exp.sh. To process the obtained results, we  provide a script  process-results-inclusion.sh. Log files containing the estructures reported as not included are found in folder scripts/results-begen-serialize.sh.