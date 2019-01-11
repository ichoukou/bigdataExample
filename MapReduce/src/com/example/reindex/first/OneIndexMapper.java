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
		// 1 获取切片名称
		FileSplit inputSplit = (FileSplit) context.getInputSplit();
		String name = inputSplit.getPath().getName();

		// 2 获取1行
		String line = value.toString();

		// 3 截取
		String[] words = line.split(" ");

		// 4 把每个单词和切片名称关联起来
		for (String word : words) {
			k.set(word + "--" + name);
			
			context.write(k, new IntWritable(1));
		}
	}
}
