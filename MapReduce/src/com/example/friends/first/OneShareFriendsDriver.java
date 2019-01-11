package com.example.friends.first;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class OneShareFriendsDriver {

	public static void main(String[] args) throws Exception {
		// 1 获取job对象
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);
		
		// 2 指定jar包运行的路径
		job.setJarByClass(OneShareFriendsDriver.class);

		// 3 指定map/reduce使用的类
		job.setMapperClass(OneShareFriendsMapper.class);
		job.setReducerClass(OneShareFriendsReducer.class);
		
		// 4 指定map输出的数据类型
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(Text.class);
		
		// 5 指定最终输出的数据类型
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		
		// 6 指定job的输入原始所在目录
		FileInputFormat.setInputPaths(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		// 7 提交
		boolean result = job.waitForCompletion(true);
		
		System.exit(result?1:0);
	}
}

//第一次结果	我觉得已经够了，加入找bc共同好友，第二位数据，哪个包含bc，然后就输出就可以了
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

