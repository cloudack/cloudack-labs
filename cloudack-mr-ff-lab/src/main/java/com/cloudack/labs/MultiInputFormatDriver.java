/**
 * 
 */
package com.cloudack.labs;

/**
 * @author rdoppala
 *
 */
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class MultiInputFormatDriver {
	public static class TColCSVMapper extends
	Mapper<LongWritable, Text, Text, IntWritable> {
		public void map(LongWritable k, Text v, Context con)
				throws IOException, InterruptedException {
			String line = v.toString();
			String[] words = line.split(",");
			String sex = words[1];
			int sal = Integer.parseInt(words[2]);
			con.write(new Text(sex), new IntWritable(sal));
		}
	}

	public static class FColCSVMapper extends
	Mapper<LongWritable, Text, Text, IntWritable> {
		public void map(LongWritable k, Text v, Context con)
				throws IOException, InterruptedException {
			String line = v.toString();
			String[] words = line.split(",");
			String sex = words[2];
			int sal = Integer.parseInt(words[3]);
			con.write(new Text(sex), new IntWritable(sal));
		}
	}

	public static class Red extends
	Reducer<Text, IntWritable, Text, IntWritable> {
		public void reduce(Text sex, Iterable<IntWritable> salaries, Context con)
				throws IOException, InterruptedException {
			int tot = 0;
			for (IntWritable sal : salaries) {
				tot += sal.get();
			}
			con.write(sex, new IntWritable(tot));

		}
	}

	public static void main(String[] args) throws Exception {
		Configuration c = new Configuration();
		String[] files = new GenericOptionsParser(c, args).getRemainingArgs();
		Path p1 = new Path(files[0]);
		Path p2 = new Path(files[1]);
		Path p3 = new Path(files[2]);
		Job job = new Job(c, "multiple");
		job.setJarByClass(MultiInputFormatDriver.class);
		job.setMapperClass(TColCSVMapper.class);
		job.setMapperClass(FColCSVMapper.class);
		job.setReducerClass(Red.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		MultipleInputs.addInputPath(job, p1, TextInputFormat.class,
				TColCSVMapper.class);
		MultipleInputs.addInputPath(job, p2, TextInputFormat.class,
				FColCSVMapper.class);
		FileOutputFormat.setOutputPath(job, p3);
		System.exit(job.waitForCompletion(true) ? 0 : 1);

	}

}