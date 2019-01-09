package com.example.wordcount;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.CombineTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.wordcount.conf.FileLocationEnum;
import com.example.wordcount.shuffle.WordCountPartitioner;
import com.example.wordcount.shuffle.WordcountCombiner;

/**
 * 相当于一个yarn集群的客户端，
 * 需要在此封装我们的mr程序相关运行参数，指定jar包
 * 最后提交给yarn
 * @author Administrator
*/
public class WordcountDriver {
	
	public static void main(String[] args) throws Exception {
		// 1 获取配置信息，或者job对象实例
		Configuration configuration = new Configuration();
		// 8 配置提交到yarn上运行,windows和Linux变量不一致
//		configuration.set("mapreduce.framework.name", "yarn");
//		configuration.set("yarn.resourcemanager.hostname", "hadoop103");
		Job job = Job.getInstance(configuration);
		
		// 6 指定本程序的jar包所在的本地路径
//		job.setJar("/home/atguigu/wc.jar");
		job.setJarByClass(WordcountDriver.class);
		
		// 9 如果不设置InputFormat,它默认用的是TextInputFormat.class
//		job.setInputFormatClass(CombineTextInputFormat.class);
//		CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);// 4m
//		CombineTextInputFormat.setMinInputSplitSize(job, 2097152);// 2m

		
		// 2 指定本业务job要使用的mapper/Reducer业务类
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);
		
		//////////////////shuffle area /////////////
		//8在驱动中配置加载分区，设置reducetask个数
//		job.setPartitionerClass(WordCountPartitioner.class);
//		job.setNumReduceTasks(2);
//
//		// 9 指定需要使用combiner，以及用哪个类作为combiner的逻辑
//		job.setCombinerClass(WordcountCombiner.class);
		/////////////////////////////////////////////
		
		// 3 指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		


		
		// 4 指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 5 指定job的输入原始文件所在目录
//		FileInputFormat.setInputPaths(job, new Path(args[0]));
//		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.WORDCOUNTINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.WORDCOUNTOUTPUT.getValue()));
		
		
		// 7 将job中配置的相关参数，以及job所用的java类所在的jar包， 提交给yarn去运行
//		job.submit();
		boolean result = job.waitForCompletion(true);
		
		System.out.println("finish");
		
		System.exit(result?0:1);
		
		
	}
}
