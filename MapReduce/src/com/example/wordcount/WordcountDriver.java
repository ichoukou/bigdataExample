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
 * �൱��һ��yarn��Ⱥ�Ŀͻ��ˣ�
 * ��Ҫ�ڴ˷�װ���ǵ�mr����������в�����ָ��jar��
 * ����ύ��yarn
 * @author Administrator
*/
public class WordcountDriver {
	
	public static void main(String[] args) throws Exception {
		// 1 ��ȡ������Ϣ������job����ʵ��
		Configuration configuration = new Configuration();
		// 8 �����ύ��yarn������,windows��Linux������һ��
//		configuration.set("mapreduce.framework.name", "yarn");
//		configuration.set("yarn.resourcemanager.hostname", "hadoop103");
		Job job = Job.getInstance(configuration);
		
		// 6 ָ���������jar�����ڵı���·��
//		job.setJar("/home/atguigu/wc.jar");
		job.setJarByClass(WordcountDriver.class);
		
		// 9 ���������InputFormat,��Ĭ���õ���TextInputFormat.class
//		job.setInputFormatClass(CombineTextInputFormat.class);
//		CombineTextInputFormat.setMaxInputSplitSize(job, 4194304);// 4m
//		CombineTextInputFormat.setMinInputSplitSize(job, 2097152);// 2m

		
		// 2 ָ����ҵ��jobҪʹ�õ�mapper/Reducerҵ����
		job.setMapperClass(WordcountMapper.class);
		job.setReducerClass(WordcountReducer.class);
		
		//////////////////shuffle area /////////////
		//8�����������ü��ط���������reducetask����
//		job.setPartitionerClass(WordCountPartitioner.class);
//		job.setNumReduceTasks(2);
//
//		// 9 ָ����Ҫʹ��combiner���Լ����ĸ�����Ϊcombiner���߼�
//		job.setCombinerClass(WordcountCombiner.class);
		/////////////////////////////////////////////
		
		// 3 ָ��mapper������ݵ�kv����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(IntWritable.class);
		
		


		
		// 4 ָ��������������ݵ�kv����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		
		// 5 ָ��job������ԭʼ�ļ�����Ŀ¼
//		FileInputFormat.setInputPaths(job, new Path(args[0]));
//		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.WORDCOUNTINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.WORDCOUNTOUTPUT.getValue()));
		
		
		// 7 ��job�����õ���ز������Լ�job���õ�java�����ڵ�jar���� �ύ��yarnȥ����
//		job.submit();
		boolean result = job.waitForCompletion(true);
		
		System.out.println("finish");
		
		System.exit(result?0:1);
		
		
	}
}
