package com.example.friends.first;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class OneShareFriendsMapper extends Mapper<LongWritable, Text, Text, Text>{
	
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, Text>.Context context)
			throws IOException, InterruptedException {
		// 1 ��ȡһ�� A:B,C,D,F,E,O
		String line = value.toString();
		
		// 2 �и�
		String[] fileds = line.split(":");
		
		// 3 ��ȡperson�ͺ���
		String person = fileds[0];
		String[] friends = fileds[1].split(",");
		
		// 4д��ȥ
		for(String friend: friends){
			// ��� <���ѣ���>
			context.write(new Text(friend), new Text(person));
		}
	}
}
