# Job Map-Reduce Java App: Sales Analysis

## Overview
This Java application is designed to perform sales analysis on data stored in a text file (`sales.txt`). The app utilizes Hadoop Map-Reduce framework to perform sales analytics made by the company in different cities. The input file (`sales.txt`) contains records in the format: `date city product price`.

## Prerequisites
- Java Development Kit (JDK) installed
- Apache Hadoop installed and configured on your system

## How to Use
1. **Input File Format**: Ensure the input file (`sales.txt`) follows the specified format: `date city product price`.
2. **Compile the Code**.
3. **Create a JAR File**: Create a JAR file from the compiled classes.
4. **Run the Hadoop Job**: Execute the Hadoop Map-Reduce job using the created JAR file and input file:
   ```
   hadoop jar SalesAnalysis.jar SalesAnalysis /sales.txt /output
   ```
   
## Exercise 1

### The app utilizes Hadoop Map-Reduce framework to calculate and determine the total sales made by the company in different `cities`.

**SalesMapper**:

```java
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
```
**SalesReducer**:

```java
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
```

**View Output**:

![img.png](src%2Fmain%2Fresources%2Fassets%2Fimg.png)

### 2 - The app utilizes Hadoop Map-Reduce framework to calculate and determine the total sales made by `cities` and `year`.

**SalesMapper**:

```java
public class SalesMapper2 extends Mapper<LongWritable,Text,Text, DoubleWritable> {

    @Override
    protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {

        String row[]= value.toString().split(";");
        if(row.length == 4){

            String date[] = row[0].split("-");
            String year = date[0];
            String city = row[1];
            double price = Double.parseDouble(row[3]);

            for (String text:row) {
                context.write(new Text(city+"_"+year),new DoubleWritable(price));
            }
        }
    }
}
```

**SalesReducer**:

```java
public class SalesReducer2 extends Reducer<Text, DoubleWritable,Text,DoubleWritable> {

    @Override
    protected void reduce(Text key, Iterable<DoubleWritable> values, Reducer<Text, DoubleWritable, Text, DoubleWritable>.Context context) throws IOException, InterruptedException {
        double totalPrice=0.0;
        for(DoubleWritable val: values){
            totalPrice+=val.get();
        }
        context.write(key,new DoubleWritable(totalPrice));
    }
}
```

5. **View Output**:

![img_1.png](src%2Fmain%2Fresources%2Fassets%2Fimg_1.png)

## Author
Abderrahim Ait Bouna

## Acknowledgments
This project was developed for the purpose of practicing and working with Hadoop Map-Reduce framework. Special thanks to the open-source community and Hadoop developers for providing such a powerful tool for big data processing.
