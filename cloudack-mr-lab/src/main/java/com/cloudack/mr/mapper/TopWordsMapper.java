/**
 * 
 */
package com.cloudack.mr.mapper;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer.Context;

/**
 * @author pudi
 * 
 */

public class TopWordsMapper extends
		Mapper<LongWritable, Text, IntWritable, Text> {

	private Text word = new Text();

	public void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		StringTokenizer tokenizer = new StringTokenizer(line);
		word.set(tokenizer.nextToken());
		IntWritable keyInt = new IntWritable(Integer.parseInt(tokenizer
				.nextToken()));

		context.write(keyInt, word);

	}
}