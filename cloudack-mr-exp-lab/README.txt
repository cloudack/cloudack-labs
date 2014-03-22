Running mapreduce examples that ship with Hadoop

These examples are in 'hadoop-examples-*.jar'
On  Cloudack cluster , this jar file is in /home/vagrant/hadoop-0.20.2-cdh3u6//hadoop-examples-0.20.2-cdh3u6.jar

== STEP 1)
    Find what mapreduce examples are available

    $ hadoop jar /home/vagrant/hadoop-0.20.2-cdh3u6/hadoop-examples-0.20.2-cdh3u6.jar

    This will print out sample programs available


== STEP 2) grep mapreduce
    find usage:
    $ hadoop jar /home/vagrant/hadoop-0.20.2-cdh3u6//hadoop-examples-0.20.2-cdh3u6.jar grep


== STEP 3)
    lets copy some files into hdfs, we will use Hadoop config files

    prepare a directory in hdfs
        $ hadoop dfs -mkdir in

    copy hadoop config files from /etc/hadoop/conf  into HDFS
        $ hhadoop fs -copyFromLocal /var/log/*.log in


== STEP 4) running grep
    to find grep usage
        $ hadoop jar /home/vagrant/hadoop-0.20.2-cdh3u6//hadoop-examples-0.20.2-cdh3u6.jar grep 

    it will print out the usage
        first arg : input dir
        second arg : output dir
        third arg : pattern to look for

    now lets run the command, look for string 'dfs'

        $ hadoop jar /home/vagrant/hadoop-0.20.2-cdh3u6//hadoop-examples-0.20.2-cdh3u6.jar grep  in  out  'dfs'

    This commnad will kick off mapreduce jobs


== STEP 5) verifying run output
    grep output will be in the output dir (<your_name>/grep/out)
    see files in the output dir

        $ hdfs dfs -ls in

    use 'cat' command to see the file contents

        $ hadoop dfs -cat <your_name>/grep/out/part-00000


== BONUS LAB )
    lets do a unix grep on the files
        $   grep -c 'dfs'   /etc/hadoop/conf/*
        $   hadoop jar /home/vagrant/hadoop-0.20.2-cdh3u6//hadoop-examples-0.20.2-cdh3u6.jar wordcount data1 wcoutput

    note the count
    what is the count from Hadoop grep
    do they agree?  if not why not?
