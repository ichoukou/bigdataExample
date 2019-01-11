package com.example.order.shuffle;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

import com.example.order.bean.OrderBean;

public class OrderSortPartitioner extends Partitioner<OrderBean, NullWritable>{

	@Override
	public int getPartition(OrderBean key, NullWritable value, int numReduceTasks) {
		
		return (key.getOrderId().hashCode() & Integer.MAX_VALUE) % numReduceTasks;
		//hashCode()是Object类的一个方法，hashCode() Returns a hash code value for the object.
		//Integer.MAX_VALUE表示int类型能够表示的最大值，
		//&是按位与运算符比如a&b表示把a和b进行二进制的按位与运算比如8&10，其中8的二进制是0000 1000，而10的二进制是0000 1010，因此      0000 1000(十进制8)   & 0000 1010(10进制10）结果为0000 1000(就是10进制的8）因此8&10的结果为8与的计算规则是，如果两个数都都为真(或为1)，其结果为真，如果两位数中有一位为假(或为0）者结果为假
		//上面的程序就是2 和2147483647按位与然后再模3取余
	}
}
