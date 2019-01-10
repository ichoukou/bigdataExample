
package com.example.wordcount.map;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * KEYIN:Ĭ������£���mr�����������һ���ı�����ʼƫ������Long;
 * ��hadoop�����Լ��ĸ���������л��ӿڣ����Բ�ֱ����Long��������LongWritable
 * VALUEIN:Ĭ������£���mr�����������һ���ı����ݣ�String;�˴���Text
 * 
 * KEYOUT:���û��Զ����߼��������֮����������е�key,�ڴ˴��ǵ��ʣ�String���˴���Text
 * VALUEOUT�����û��Զ����߼��������֮����������е�value���ڴ˴��ǵ��ʴ�����Integer���˴���IntWritable
 * @author Administrator
 */
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
	/**
	 * map�׶ε�ҵ���߼���д���Զ����map()������
	 * maptask���ÿһ���������ݵ���һ�������Զ����map��������
	 */
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		// 1 ��maptask�������ǵ��ı�������ת����String
		String line = value.toString();
		
		// 2 ���ݿո���һ���зֳɵ���
		String[] words = line.split(" ");
		
		// 3 ���������Ϊ<���ʣ�1>
		for(String word:words){
			// ��������Ϊkey��������1��Ϊvalue,�Ա��ں��������ݷַ������Ը��ݵ��ʷַ����Ա�����ͬ���ʻᵽ��ͬ��reducetask��
			context.write(new Text(word), new IntWritable(1));
		}
	}
}
