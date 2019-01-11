package com.example.inputformat;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import com.example.conf.FileLocationEnum;
public class InputFormatDriver {

	static class SequenceFileMapper extends Mapper<NullWritable, BytesWritable, Text, BytesWritable> {
		private Text filenameKey;

		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			// ��ȡ��Ƭ��Ϣ
			InputSplit split = context.getInputSplit();
			// ��ȡ��Ƭ·��
			Path path = ((FileSplit) split).getPath();
			// ������Ƭ·����ȡ�ļ�����
			filenameKey = new Text(path.toString());
		}

		@Override
		protected void map(NullWritable key, BytesWritable value, Context context)
				throws IOException, InterruptedException {
			// �ļ�����Ϊkey
			context.write(filenameKey, value);
		}
	}

	public static void main(String[] args) throws Exception {

		Configuration conf = new Configuration();
		
		Job job = Job.getInstance(conf);
		job.setJarByClass(InputFormatDriver.class);

		job.setInputFormatClass(WholeFileInputFormat.class);
		job.setOutputFormatClass(SequenceFileOutputFormat.class);

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(BytesWritable.class);

		job.setMapperClass(SequenceFileMapper.class);

		FileInputFormat.setInputPaths(job, new Path(FileLocationEnum.INPUTFORMATINPUT.getValue()));
		FileOutputFormat.setOutputPath(job, new Path(FileLocationEnum.INPUTFORMATOUTPUT.getValue()+"ex"));

		boolean result = job.waitForCompletion(true);

		System.exit(result ? 0 : 1);
	}
}
