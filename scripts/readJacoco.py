import pandas as pd
import sys
from os import listdir
from os.path import join
from os.path import join,isfile
import re   # regex module
import os


# def file():
   # return "build/reports/pitest/mutations.csv"
# def parser(seed, package, clazz, budget, tool, numberTests, suffix =".csv"):
def parser( file, package, suffix =".csv"):
	branch=0
	line=0
	branchTotal=0
	lineTotal=0
	fname = file
	df = pd.read_csv(fname, error_bad_lines=False)
	df=df[df['PACKAGE'].str.contains(package)]
	# print(df)
	#No tengo los metodos en JACOCO csv. Ver la forma de omitir, repoks, isLinkedList, etc.
	discard = ['HashMap.KeyIterator','HashMap.Values','HashMap.KeySet','repOK', 'putAll']
	for index, row in df.iterrows():
			clazz=row[2]
			if clazz not in discard:
				# print(clazz)
				branch=row['BRANCH_COVERED']+branch
				branchTotal=row['BRANCH_COVERED']+row['BRANCH_MISSED']+branchTotal
				line=row['LINE_COVERED']+line
				lineTotal=row['LINE_MISSED']+row['LINE_COVERED']+lineTotal

	#   				
			

	print(line)
	print(lineTotal)
	print(branch)
	print(branchTotal)

if __name__ == '__main__':
	file = sys.argv[1]
	package = sys.argv[2]
	parser(file, package)


