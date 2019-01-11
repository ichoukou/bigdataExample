package com.example.table.d;

import java.net.URI;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.example.conf.FileLocationEnum;

/**
 * ֱ�Ӵ�map�˺ϲ�������������
 * @author tou
 *
 */
public class DistributedCacheDriver {

	public static void main(String[] args) throws Exception {
		// 1 ��ȡjob��Ϣ
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		// 2 ���ü���jar��·��
		job.setJarByClass(DistributedCacheDriver.class);

		// 3 ����map
		job.setMapperClass(DistributedCacheMapper.class);
		
		// 4 �������������������
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(NullWritable.class);

		// 5 �����������·��
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.ORDERTXT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.TABLEOUTPUT.getValue()+"ex"));

		// 6 ���ػ�������
		job.addCacheFile(new URI("file:/"+FileLocationEnum.PDTXT.getValue()));
		
		// 7 map��join���߼�����Ҫreduce�׶Σ�����reducetask����Ϊ0
		job.setNumReduceTasks(0);

		// 8 �ύ
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
