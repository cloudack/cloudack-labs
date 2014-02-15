Streaming job:

Prepare input dataset:
-bash-3.2$ hadoop fs -mkdir inputconf
-bash-3.2$ hadoop fs -copyFromLocal /etc/hadoop/conf/*.xml inputconf
-bash-3.2$ hadoop fs -ls inputconf

location of streaming jar on the node:  find /usr/lib/ -name 'hadoop-streaming*'

/usr/lib/hadoop-0.20-mapreduce/contrib/streaming/hadoop-streaming-2.0.0-mr1-cdh4.4.0.jar
Command to execute streaming job:

 hadoop jar /usr/lib/hadoop-0.20-mapreduce/contrib/streaming/hadoop-streaming-2.0.0-mr1-cdh4.4.0.jar -file ~/mapper.py -mapper "python  mapper.py" -file ~/reducer.py  -reducer "python reducer.py" -input inputconf -output outputconf

 hadoop jar /usr/lib/hadoop-0.20-mapreduce/contrib/streaming/hadoop-streaming-2.0.0-mr1-cdh4.4.0.jar -file ~/mapper.py -mapper "python  mapper.py" -file ~/reducer.py  -reducer "python reducer.py" -input inputDir-output outputDir
