/**
 * 
 */
package com.cloudack.mr.spe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import org.apache.hadoop.filecache.DistributedCache;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Mapper.Context;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.log4j.Logger;

/**
 * @author pudi
 * 
 */

public  class WordCounterCacheMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    
	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	private Logger log = Logger.getLogger(WordCounterCacheMapper.class);
	 private Path[] localArchives;
     private Path[] localFiles;
     
    
     
     @Override
    protected void setup(org.apache.hadoop.mapreduce.Mapper.Context context)
    		throws IOException, InterruptedException {
    	// TODO Auto-generated method stub
    	super.setup(context);
    	log.info("initializing the mapper");
    	System.out.println("this is sysout output");
   
    	try {
    		localFiles = DistributedCache.getLocalCacheFiles(context.getConfiguration());
    		FileSystem fs = FileSystem.get(context.getConfiguration());
    		  BufferedReader br  = new BufferedReader(new FileReader(localFiles[0].toString()));
    		  String ln;
    		  while(( ln=br.readLine())!=null){
	                //System.out.println(ln);
	                //log.info(ln);
    		  }
    	} catch (IOException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    }

	

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		String line = value.toString();
		
		FileReader fileStream = new FileReader(localFiles[0].toString());
	        BufferedReader reader=new BufferedReader(fileStream);
	        String ln=null;
	            while((ln=reader.readLine())!=null){
	                //System.out.println(ln);
	                log.debug(ln);
	        		
	            }
	    
		StringTokenizer tokenizer = new StringTokenizer(line);
		while (tokenizer.hasMoreTokens()) {
			word.set(tokenizer.nextToken());
			context.write(word, one);
		}
		FileOutputFormat.getWorkOutputPath(context);
	     
	}
}
