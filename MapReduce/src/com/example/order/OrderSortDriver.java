package com.example.order;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.conf.FileLocationEnum;
import com.example.order.bean.OrderBean;
import com.example.order.shuffle.OrderSortGroupingComparator;
import com.example.order.shuffle.OrderSortPartitioner;

public class OrderSortDriver {

	public static void main(String[] args) throws Exception {
		// 1 ��ȡ������Ϣ
		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf);

		// 2 ����jar������·��
		job.setJarByClass(OrderSortDriver.class);

		// 3 ����map/reduce��
		job.setMapperClass(OrderSortMapper.class);
		job.setReducerClass(OrderSortReducer.class);

		// 4 ����map�������key��value����
		job.setMapOutputKeyClass(OrderBean.class);
		job.setMapOutputValueClass(NullWritable.class);

		// 5 ��������������ݵ�key��value����
		job.setOutputKeyClass(String.class);
		job.setOutputValueClass(Double.class);

		// 6 �����������ݺ��������·��
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.ORDERINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.ORDEROUTPUT.getValue()+"exsort"));


		// 10 ����reduce�˵ķ���
		job.setGroupingComparatorClass(OrderSortGroupingComparator.class);
		
		// 7 ���÷���
		job.setPartitionerClass(OrderSortPartitioner.class);
		
		// 8 ����reduce����
		job.setNumReduceTasks(3);

		// 9 �ύ
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}

