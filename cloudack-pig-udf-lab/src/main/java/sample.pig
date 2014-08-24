REGISTER cloudack-mr-lab.jar;
DEFINE MyUDF com.cloudack.pig.udf.MyUDF();
A = LOAD 'data/input/wc/pg42102.txt' AS (words:chararray);
B = FOREACH A GENERATE MyUDF(words);
DUMP B;
