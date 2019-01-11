package com.example.order;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.example.order.bean.OrderBean;

public class OrderSortReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable>{
	@Override
	protected void reduce(OrderBean bean, Iterable<NullWritable> values,
			Context context) throws IOException, InterruptedException {
		// 直接写出
		context.write(bean, NullWritable.get());//问题：为什么分组后，排第二第三的都不见了
	}
	
	//https://www.cnblogs.com/yaboya/p/9254640.html
    //到达reduce时，相同id的所有bean已经被看成一组，且金额最大的那个排在第一位
    //在设置了groupingcomparator以后，这里收到的kv数据就是：  <1001 87.6>,null  <1001 76.5>,null  .... 
    //此时，reduce方法中的参数key就是上述kv组中的第一个kv的key：<1001 87.6>
    //要输出同一个item的所有订单中最大金额的那一个，就只要输出这个key	
	
	//自己理解
	//partition，就是reduce之前，标记这些数据将要写在什么地方
	//group，就是将n份同组数据给reduce处理(假如流量那个，只输出第一个手机号(不进行迭代的话)，那后面的数据都不会被访问)
}

