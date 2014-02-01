/**
 * 
 */
package com.cloudack.mr.reducer;

import java.io.IOException;
import java.util.Iterator;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapred.Reporter;

/**
 * @author pudi
 * 
 */

public class GeoLocReducer extends MapReduceBase implements
		Reducer<Text, Text, Text, Text> {

	private Text outputKey = new Text();
	private Text outputValue = new Text();

	public void reduce(Text geoLocationKey, Iterator<Text> geoLocationValues,
			OutputCollector<Text, Text> results, Reporter reporter)
			throws IOException {
		// in this case the reducer just creates a list so that the data can
		// used later
		String outputText = "";
		while (geoLocationValues.hasNext()) {
			Text locationName = geoLocationValues.next();
			outputText = outputText + locationName.toString() + " ,";
		}
		outputKey.set(geoLocationKey.toString());
		outputValue.set(outputText);
		results.collect(outputKey, outputValue);
	}

}