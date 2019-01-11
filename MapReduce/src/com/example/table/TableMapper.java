package com.example.table;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean>{
	TableBean bean = new TableBean();
	Text k = new Text();
	
	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {
		
		// 1 ��ȡ�����ļ�����
		FileSplit split = (FileSplit) context.getInputSplit();
		String name = split.getPath().getName();
		
		// 2 ��ȡ��������
		String line = value.toString();
		

		// 3.1 �и�
		String[] fields = line.split("\t");

		
		// 3 ��ͬ�ļ��ֱ���
		if (name.startsWith("order")) {// ��������
			
			if(fields.length<3) {
				return;
			}
			
			// 3.2 ��װbean����
			bean.setOrder_id(fields[0]);
			bean.setP_id(fields[1]);
//			bean.setAmount(Integer.parseInt(fields[2]));
			bean.setAmount(1);//�����ص���ȥ����ͳ����ʲô�����أ�
			bean.setPname("");
			bean.setFlag("0");
			
			k.set(fields[1]);
		}else {// ��Ʒ����
			
			if(fields.length<2) {
				return;
			}
			
			// 3.4 ��װbean����
			bean.setP_id(fields[0]);
			bean.setPname(fields[1]);
			bean.setFlag("1");
			bean.setAmount(0);
			bean.setOrder_id("");
			
			k.set(fields[0]);
		}
		// 4 д��
		context.write(k, bean);
	}
}

