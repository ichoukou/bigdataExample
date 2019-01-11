package com.example.reindex.first;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.conf.FileLocationEnum;

public class OneIndexDriver {

	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf);
		job.setJarByClass(OneIndexDriver.class);

		job.setMapperClass(OneIndexMapper.class);
		job.setReducerClass(OneIndexReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);

		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.REINDEXINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.REINDEXFIRSTOUTPUT.getValue()));

		job.waitForCompletion(true);
	}
}



//½á¹û
//atguigu--a.txt	3
//atguigu--b.txt	2
//atguigu--c.txt	2
//pingping--a.txt	1
//pingping--b.txt	3
//pingping--c.txt	1
//ss--a.txt	2
//ss--b.txt	1
//ss--c.txt	1
