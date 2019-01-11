package com.example.reindex.second;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.conf.FileLocationEnum;

public class TwoIndexDriver {

	public static void main(String[] args) throws Exception {
		Configuration config = new Configuration();
		Job job = Job.getInstance(config);

		job.setMapperClass(TwoIndexMapper.class);
		job.setReducerClass(TwoIndexReducer.class);

		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);

		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.REINDEXFIRSTOUTPUT.getValue()+"/part-r-00000"));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.REINDEXSECONDOUTPUT.getValue()));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
