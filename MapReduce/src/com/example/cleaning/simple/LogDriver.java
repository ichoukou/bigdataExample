package com.example.cleaning.simple;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.conf.FileLocationEnum;

public class LogDriver {

	public static void main(String[] args) throws Exception {
		// 1 ��ȡjob��Ϣ
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		// 2 ����jar��
		job.setJarByClass(LogDriver.class);

		// 3 ����map
		job.setMapperClass(LogMapper.class);

		// 4 ���������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// 5 ������������·��
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.CLEANINGINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.CLEANINGOUTPUT.getValue()+"ex"));

		// 6 �ύ
		job.waitForCompletion(true);
	}
}
