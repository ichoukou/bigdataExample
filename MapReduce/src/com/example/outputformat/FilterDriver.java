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

		// 要将自定义的输出格式组件设置到job中
		job.setOutputFormatClass(FilterOutputFormat.class);

		//指定输入输出目录
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.OUTPUTFORMATINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.OUTPUTFORMATOUTPUT.getValue()+"ex"));
		// 虽然我们自定义了outputformat，但是因为我们的outputformat继承自fileoutputformat
		// 而fileoutputformat要输出一个_SUCCESS文件，所以，在这还得指定一个输出目录

		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
