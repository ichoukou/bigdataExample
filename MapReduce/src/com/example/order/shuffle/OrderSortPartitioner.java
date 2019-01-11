package com.example.order.shuffle;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import com.example.order.bean.OrderBean;

public class OrderSortPartitioner extends Partitioner<OrderBean, NullWritable>{

	@Override
	public int getPartition(OrderBean key, NullWritable value, int numReduceTasks) {
		
		return (key.getOrderId().hashCode() & Integer.MAX_VALUE) % numReduceTasks;
		//hashCode()��Object���һ��������hashCode() Returns a hash code value for the object.
		//Integer.MAX_VALUE��ʾint�����ܹ���ʾ�����ֵ��
		//&�ǰ�λ�����������a&b��ʾ��a��b���ж����Ƶİ�λ���������8&10������8�Ķ�������0000 1000����10�Ķ�������0000 1010�����      0000 1000(ʮ����8)   & 0000 1010(10����10�����Ϊ0000 1000(����10���Ƶ�8�����8&10�Ľ��Ϊ8��ļ�������ǣ��������������Ϊ��(��Ϊ1)������Ϊ�棬�����λ������һλΪ��(��Ϊ0���߽��Ϊ��
		//����ĳ������2 ��2147483647��λ��Ȼ����ģ3ȡ��
	}
}
