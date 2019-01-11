package com.example.outputformat;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.conf.FileLocationEnum;

public class FilterDriver {
	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();

		Job job = Job.getInstance(conf);

		job.setJarByClass(FilterDriver.class);
		job.setMapperClass(FilterMapper.class);
		job.setReducerClass(FilterReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(NullWritable.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// Ҫ���Զ���������ʽ������õ�job��
		job.setOutputFormatClass(FilterOutputFormat.class);

		//ָ���������Ŀ¼
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.OUTPUTFORMATINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.OUTPUTFORMATOUTPUT.getValue()+"ex"));
		// ��Ȼ�����Զ�����outputformat��������Ϊ���ǵ�outputformat�̳���fileoutputformat
		// ��fileoutputformatҪ���һ��_SUCCESS�ļ������ԣ����⻹��ָ��һ�����Ŀ¼

		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
