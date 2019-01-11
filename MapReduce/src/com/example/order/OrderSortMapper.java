package com.example.order;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import com.example.order.bean.OrderBean;

public class OrderSortMapper extends Mapper<LongWritable, Text, OrderBean, NullWritable>{
	OrderBean bean = new OrderBean();
	
	@Override
	protected void map(LongWritable key, Text value,
			Context context)throws IOException, InterruptedException {
		// 1 ��ȡһ������
		String line = value.toString();
		
		// 2 ��ȡ�ֶ�
		String[] fields = line.split("\t");
		
		// 3 ��װbean
		if(fields.length<2) {
			return;	//��ֹ��ָ��
		}
		bean.setOrderId(fields[0]);
		Double a = Double.parseDouble(fields[2]);
		bean.setPrice(a);
		System.out.print(a);
		
		// 4 д��
		context.write(bean, NullWritable.get());
	}
}

