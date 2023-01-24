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
    # row[1] = package
    # row[2] = class
    # row[3] = Instruction_miss
    # row[4] = Instruction_coverage
    # row[5] = branch_miss
    # row[6] = branch_coverage
    # row[7] = line_miss
    # row[8] = line_coverage

	df = pd.read_csv(fname, header=None)
    #df = df.loc[df[1] == casestudy] 
    # out = open("result.txt", "w")
	out = open(output, "a+")
	# out.write("-----------------")
	# out.write(seed)
	# out.write("-----------------")
	# out.write("\n")
	discard = ["readObject" "writeObject" "repOK"]
	for index, row in df.iterrows():	
		if not "$" in row[1]:
			if not row[3] in discard:
  				# out.write(row[3])
  				# out.write("\n")
				# print(", ".join(discard))
				# out.write()
				if row[5] == 'KILLED':
	  				killed=killed+1
				else:
	  				noKilled=noKilled+1

	print(killed)
	print(noKilled)
	out.write('{},{}\n'.format(killed,noKilled))

	out.close()


	# out.close()
	# for f in listdir(directory):
	# fname = join(directory, "report.csv")
	# # if isfile(fname) and fname.endswith( suffix ):
	# print('Processing: {}'.format(fname))
	# df = pd.read_csv(fname)
	# out = open("result.txt", "a")
	# out.write('{}: {}\n'.format(fname, df.iloc[0,1]))
	# 	# else:
	# 	# 	print('Warning: file {} does not exist or no is .csv'.format(f))
	# out.close()


# def grepForTest(dir):
# 	n = 0
# 	print(dir)	
# 	count = 0
# 	for filename in os.listdir(dir):
# 		file1 = open(filename, 'r')
# 		Lines = file1.readlines()
# 		# Strips the newline character
# 		for line in Lines:
# 			if re.match("@Test", line):
# 				n+=1
# 	return n
            
if __name__ == '__main__':
	# if len(sys.argv) != 3:
 #   		print("Run the command with the DIRECTORY where the files are located and the NAME of the output file\n")
 #   		print("Example python script files/ result.txt\n")
	# output = "result.txt"
	file = sys.argv[1]
	# package = sys.argv[2]
	# clazz = sys.argv[3]
	# seed = sys.argv[4]
	# tool = sys.argv[5]
	# numberTests = sys.argv[6]


	parser(file)


