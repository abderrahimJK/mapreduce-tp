package org.example.Ex1;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SalesMapper extends Mapper<LongWritable,Text,Text, DoubleWritable> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        String row[]= value.toString().split(";");
        String city = row[1];
        System.out.println("[ City Val ] "+city);
        double price = Double.parseDouble(row[3]);
        System.out.println("[ Price Val ] "+price);

        for (String text:row) {
            context.write(new Text(city),new DoubleWritable(price));
        }
    }
}
