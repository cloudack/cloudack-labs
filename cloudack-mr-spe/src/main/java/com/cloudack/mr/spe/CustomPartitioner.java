package com.cloudack.mr.spe;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class CustomPartitioner extends Partitioner<Text, IntWritable> {

	@Override
	public int getPartition(Text key, IntWritable value, int numPartitions) {
		 return (key.toString().charAt(0)) % numPartitions;
	}

}
