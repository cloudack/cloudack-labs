/**
 * 
 */
package com.cloudack.pig.udf;

import java.io.IOException;

import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;

/**
 * @author pudi
 *
 */
public class MyUDF extends EvalFunc<Integer> {

	@Override
	public Integer exec(Tuple arg0) throws IOException {
		String str = arg0.toString();
		return str.length();
	}

}
