import pandas as pd
import sys
from os import listdir
from os.path import join
from os.path import join,isfile
import re   # regex module
import os

def parser( file, clazz, suffix =".csv"):
	mutations=0
	output = "result.txt"
	fname = file

	df = pd.read_csv(fname, header=None)
	out = open(output, "a+")
	discard = ["readObject" "writeObject" "repOK"]
	for index, row in df.iterrows():
		if row[1] == clazz:
			if row[5] == 'KILLED':
				if not "$" in row[1]:
					if not row[3] in discard:
		  				# out.write(row[3])
		  				# out.write("\n")
						# print(", ".join(discard))
						# out.write()
			  			mutations=mutations+1
	
	# out.write('mutatans: {}\n'.format(mutations))
	return mutations

if __name__ == '__main__':
	
	file = sys.argv[1]
	clazz = sys.argv[2]

	print(parser(file,clazz))


