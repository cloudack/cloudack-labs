/**
 * 
 */
package com.cloudack.mr.driver;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileInputFormat;
import org.apache.hadoop.mapred.FileOutputFormat;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapred.TextOutputFormat;

import com.cloudack.mr.mapper.AnagramMapper;
import com.cloudack.mr.reducer.AnagramReducer;

/**
 * @author pudi
 * 
 */
public class AnagramDriver {

	public static void main(String[] args) throws Exception {
		JobConf conf = new JobConf(AnagramDriver.class);
		conf.setJobName("anagramcount");

		conf.setOutputKeyClass(Text.class);
		conf.setOutputValueClass(Text.class);

		conf.setMapperClass(AnagramMapper.class);
		// conf.setCombinerClass(AnagramReducer.class);
		conf.setReducerClass(AnagramReducer.class);

		conf.setInputFormat(TextInputFormat.class);
		conf.setOutputFormat(TextOutputFormat.class);

		FileInputFormat.setInputPaths(conf, new Path(args[0]));
		FileOutputFormat.setOutputPath(conf, new Path(args[1]));

		JobClient.runJob(conf);

	}
}
