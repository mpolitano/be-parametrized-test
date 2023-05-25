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
def parser( file, suffix =".csv"):
	killed=0
	noKilled=0
	output = "result.txt"
	fname = file
	df = pd.read_csv(fname, header=None,error_bad_lines=False)

	out = open(output, "a+")
	discard = ['readObject','writeObject','repOK', 'resize' ,'transfer'  ,'newKeyIterator', 'newValueIterator', 'putAll', 'isLinkedList', 'repOK', 'putAll']
	
	for index, row in df.iterrows():
			method=row[3]
			if method not in discard:
				if row[5] == 'KILLED':
	  				killed=killed+1
	  			

	print(killed)
	print(len(df))
	# out.write('{},{}\n'.format(killed,df.count()))

	out.close()

if __name__ == '__main__':
	file = sys.argv[1]
	parser(file)


