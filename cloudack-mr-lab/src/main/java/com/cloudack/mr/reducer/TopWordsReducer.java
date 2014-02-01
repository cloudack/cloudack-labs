/**
 * 
 */
package com.cloudack.mr.reducer;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * @author pudi
 */

public class TopWordsReducer extends
		Reducer<IntWritable, Text, IntWritable, Text> {

	public void reduce(IntWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		StringBuffer wordAgg = new StringBuffer("");
		for (Text val : values) {
			wordAgg.append(val + "#");
		}
		context.write(key, new Text(wordAgg.toString()));
	}
}
