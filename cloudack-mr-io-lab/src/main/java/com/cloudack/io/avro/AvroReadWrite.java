/**
 * 
 */
package com.cloudack.io.avro;

/**
 * @author pudi
 *
 */

import java.io.*;
import java.util.*;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Type;
import org.apache.avro.Schema.Field;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericData.Record;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericDatumReader;

public class AvroReadWrite {
	
	
  public static Schema makeSchema() {
    List<Field> fields = new ArrayList<Field>();
    fields.add(new Field("name", Schema.create(Type.STRING), null, null));
    fields.add(new Field("age", Schema.create(Type.INT), null, null));
    fields.add(new Field("salary", Schema.create(Type.DOUBLE), null, null));
    

 
    Schema schema = Schema.createRecord("Person", null, "avro.test", false);
    
    schema.setFields(fields);

    return(schema);
  }

  public static GenericData.Record makeObject (Schema schema, String name, int age) {
    GenericData.Record record = new GenericData.Record(schema);
    record.put("name", name);
    record.put("age", age);
    record.put("salary", new Random().nextDouble() *1000);
    return(record);
  }

  public static void testWrite (File file, Schema schema) throws IOException {
    GenericDatumWriter<GenericData.Record> datum = new GenericDatumWriter<GenericData.Record>(schema);
    DataFileWriter<GenericData.Record> writer = new DataFileWriter<GenericData.Record>(datum);

    writer.create(schema, file);
    
    for(int i=0;i<5000;i++) {
    	 writer.append(makeObject(schema, "Person "+i, i));
    }
   
    writer.close();
  }

  public static void testRead (File file) throws IOException {
    GenericDatumReader<GenericData.Record> datum = new GenericDatumReader<GenericData.Record>();
    DataFileReader<GenericData.Record> reader = new DataFileReader<GenericData.Record>(file, datum);

    GenericData.Record record = new GenericData.Record(reader.getSchema());
    while (reader.hasNext()) {
      reader.next(record);
      System.out.println("Name " + record.get("name") + " Age " + record.get("age") +" salary "+record.get("salary"));
     
    }

    reader.close();
  }

  public static void main (String[] args) throws IOException {
    Schema schema = makeSchema();
    File file = new File("data/person-file.avro");
    //testWrite(file, schema);
    testRead(file);
  }
}
