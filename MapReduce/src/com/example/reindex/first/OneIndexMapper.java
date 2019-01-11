package com.example.reindex.first;

import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class OneIndexMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

	Text k = new Text();

	@Override
	protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
		// 1 ��ȡ��Ƭ����
		FileSplit inputSplit = (FileSplit) context.getInputSplit();
		String name = inputSplit.getPath().getName();

		// 2 ��ȡ1��
		String line = value.toString();

		// 3 ��ȡ
		String[] words = line.split(" ");

		// 4 ��ÿ�����ʺ���Ƭ���ƹ�������
		for (String word : words) {
			k.set(word + "--" + name);
			
			context.write(k, new IntWritable(1));
		}
	}
}
