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
import com.example.flowsum.bean.FlowBean;
import com.example.flowsum.partitioner.ProvincePartitioner;

public class FlowCount {

	static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			// 1 ��һ������ת��string
			String ling = value.toString();

			// 2 �з��ֶ�
			String[] fields = ling.split("\t");

			// 3 ȡ���ֻ�����
			String phoneNum = fields[1];

			// 4 ȡ��������������������
			long upFlow = Long.parseLong(fields[fields.length - 3]);
			long downFlow = Long.parseLong(fields[fields.length - 2]);

			// 5 д������
			context.write(new Text(phoneNum), new FlowBean(upFlow, downFlow));
		}
	}

	static class FlowCountReducer extends Reducer<Text, FlowBean, Text, FlowBean> {
		@Override
		protected void reduce(Text key, Iterable<FlowBean> values, Context context)
				throws IOException, InterruptedException {
			long sum_upFlow = 0;
			long sum_downFlow = 0;

			// 1 ��������bean�������е��������������������ֱ��ۼ�
			for (FlowBean bean : values) {
				sum_upFlow += bean.getUpFlow();
				sum_downFlow += bean.getDownFlow();
			}

			// 2 ��װ����
			FlowBean resultBean = new FlowBean(sum_upFlow, sum_downFlow);
			context.write(key, resultBean);
		}
	}

	public static void main(String[] args) throws Exception {
		// 1 ��ȡ������Ϣ������job����ʵ��
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);

		// 6 ָ���������jar�����ڵı���·��
		job.setJarByClass(FlowCount.class);

		// 2 ָ����ҵ��jobҪʹ�õ�mapper/Reducerҵ����
		job.setMapperClass(FlowCountMapper.class);
		job.setReducerClass(FlowCountReducer.class);

		// 3 ָ��mapper������ݵ�kv����
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(FlowBean.class);
		
		////////////////shuffle/////////////////
		// 8 ָ���Զ������ݷ���
		job.setPartitionerClass(ProvincePartitioner.class);		
		// 9 ͬʱָ����Ӧ������reduce task
		job.setNumReduceTasks(5); 
		////////////////shuffle/////////////////



		// 4 ָ��������������ݵ�kv����
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(FlowBean.class);

		// 5 ָ��job������ԭʼ�ļ�����Ŀ¼
		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.FLOWSUMINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.FLOWSUMOUTPUT.getValue()+"ex1"));

		// 7 ��job�����õ���ز������Լ�job���õ�java�����ڵ�jar���� �ύ��yarnȥ����
		boolean result = job.waitForCompletion(true);
		System.exit(result ? 0 : 1);
	}
}
