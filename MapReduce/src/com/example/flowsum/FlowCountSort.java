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
			
			// 1 �õ�������һ��ͳ�Ƴ�������Ľ�����Ѿ��Ǹ��ֻ��ŵ���������Ϣ
			String line = value.toString();
			
			// 2 ��ȡ�ַ�������ȡ�绰�š�������������������
			String[] fields = line.split("\t");
			String phoneNbr = fields[0];
			
			long upFlow = Long.parseLong(fields[fields.length - 3]);
			long downFlow = Long.parseLong(fields[fields.length - 2]);
			
			// 3 ��װ����
			bean.set(upFlow, downFlow);
			v.set(phoneNbr);
			
			// 4 ���
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
		// 1 ��ȡ������Ϣ������job����ʵ��
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		// 6 ָ���������jar�����ڵı���·��
		job.setJarByClass(FlowCountSort.class);

		// 2 ָ����ҵ��jobҪʹ�õ�mapper/Reducerҵ����
		job.setMapperClass(FlowCountSortMapper.class);
		job.setReducerClass(FlowCountSortReducer.class);

		// 3 ָ��mapper������ݵ�kv����
		job.setMapOutputKeyClass(FlowBeanCompare.class);
		job.setMapOutputValueClass(Text.class);

		// 4 ָ��������������ݵ�kv����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBeanCompare.class);

		// 5 ָ��job������ԭʼ�ļ�����Ŀ¼
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.FLOWSUMINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.FLOWSUMOUTPUT.getValue()+"exsort"));

		
//		FileSystem fs = FileSystem.get(configuration);
//		if (fs.exists(outPath)) {
//			fs.delete(outPath, true);
//		}

		// 7 ��job�����õ���ز������Լ�job���õ�java�����ڵ�jar���� �ύ��yarnȥ����
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
