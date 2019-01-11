package com.example.friends.first;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class OneShareFriendsDriver {

	public static void main(String[] args) throws Exception {
		// 1 ��ȡjob����
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);
		
		// 2 ָ��jar�����е�·��
		job.setJarByClass(OneShareFriendsDriver.class);

		// 3 ָ��map/reduceʹ�õ���
		job.setMapperClass(OneShareFriendsMapper.class);
		job.setReducerClass(OneShareFriendsReducer.class);
		
		// 4 ָ��map�������������
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		// 5 ָ�������������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// 6 ָ��job������ԭʼ����Ŀ¼
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 7 �ύ
		boolean result = job.waitForCompletion(true);
		
		System.exit(result?1:0);
	}
}

//��һ�ν��	�Ҿ����Ѿ����ˣ�������bc��ͬ���ѣ��ڶ�λ���ݣ��ĸ�����bc��Ȼ�������Ϳ�����
//A	I,K,C,B,G,F,H,O,D,
//B	A,F,J,E,
//C	A,E,B,H,F,G,K,
//D	G,C,K,A,L,F,E,H,
//E	G,M,L,H,A,F,B,D,
//F	L,M,D,C,G,A,
//G	M,
//H	O,
//I	O,C,
//J	O,
//K	B,
//L	D,E,
//M	E,F,
//O	A,H,I,J,F,

