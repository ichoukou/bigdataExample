package com.example.flowsum;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.conf.FileLocationEnum;
import com.example.flowsum.bean.FlowBeanCompare;

public class FlowCountSort {
	static class FlowCountSortMapper extends Mapper<LongWritable, Text, FlowBeanCompare, Text>{
		FlowBeanCompare bean = new FlowBeanCompare();
		Text v = new Text();
		
		@Override
		protected void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {
			
			// 1 拿到的是上一个统计程序输出的结果，已经是各手机号的总流量信息
			String line = value.toString();
			
			// 2 截取字符串并获取电话号、上行流量、下行流量
			String[] fields = line.split("\t");
			String phoneNbr = fields[0];
			
			long upFlow = Long.parseLong(fields[fields.length - 3]);
			long downFlow = Long.parseLong(fields[fields.length - 2]);
			
			// 3 封装对象
			bean.set(upFlow, downFlow);
			v.set(phoneNbr);
			
			// 4 输出
			context.write(bean, v);
		}
	}
	
	static class FlowCountSortReducer extends Reducer<FlowBeanCompare, Text, Text, FlowBeanCompare>{
		
		@Override
		protected void reduce(FlowBeanCompare bean, Iterable<Text> values, Context context)
				throws IOException, InterruptedException {
			context.write(values.iterator().next(), bean);
		}
	}
	
	public static void main(String[] args) throws Exception {
		// 1 获取配置信息，或者job对象实例
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		// 6 指定本程序的jar包所在的本地路径
		job.setJarByClass(FlowCountSort.class);

		// 2 指定本业务job要使用的mapper/Reducer业务类
		job.setMapperClass(FlowCountSortMapper.class);
		job.setReducerClass(FlowCountSortReducer.class);

		// 3 指定mapper输出数据的kv类型
		job.setMapOutputKeyClass(FlowBeanCompare.class);
		job.setMapOutputValueClass(Text.class);

		// 4 指定最终输出的数据的kv类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBeanCompare.class);

		// 5 指定job的输入原始文件所在目录
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.FLOWSUMINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.FLOWSUMOUTPUT.getValue()+"exsort"));

		
//		FileSystem fs = FileSystem.get(configuration);
//		if (fs.exists(outPath)) {
//			fs.delete(outPath, true);
//		}

		// 7 将job中配置的相关参数，以及job所用的java类所在的jar包， 提交给yarn去运行
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
