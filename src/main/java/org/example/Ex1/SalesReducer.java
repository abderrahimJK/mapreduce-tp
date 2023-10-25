package org.example;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class SalesReducer extends Reducer<Text, DoubleWritable,Text,DoubleWritable> {

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        double totalPrice=0.0;
        for(DoubleWritable val: values){
            totalPrice+=val.get();
        }
        context.write(key,new DoubleWritable(totalPrice));
    }
}
