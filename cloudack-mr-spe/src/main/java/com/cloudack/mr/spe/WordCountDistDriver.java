/**
 * 
 */
package com.cloudack.mr.spe;

import java.net.URI;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;



/**
 * @author pudi
 * 
 */
public class WordCountDistDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		Logger log = Logger.getLogger(WordCountDistDriver.class);
		log.setLevel(Level.INFO);
		Configuration conf = new Configuration();
		DistributedCache.addCacheFile(new URI("cachefile1"),conf);
		
		Job job = new Job(conf, "wordcount");

		job.setJarByClass(WordCountMapper.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		job.setMapperClass(WordCounterCacheMapper.class);
		job.setReducerClass(WordCountReducer.class);

		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		job.setNumReduceTasks(4);
		job.setPartitionerClass(CustomPartitioner.class);
		
	 
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));

		job.waitForCompletion(true);
	}

}
