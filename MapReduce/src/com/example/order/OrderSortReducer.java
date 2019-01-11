package com.example.order;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;

import com.example.order.bean.OrderBean;

public class OrderSortReducer extends Reducer<OrderBean, NullWritable, OrderBean, NullWritable>{
	@Override
	protected void reduce(OrderBean bean, Iterable<NullWritable> values,
			Context context) throws IOException, InterruptedException {
		// ֱ��д��
		context.write(bean, NullWritable.get());//���⣺Ϊʲô������ŵڶ������Ķ�������
	}
	
	//https://www.cnblogs.com/yaboya/p/9254640.html
    //����reduceʱ����ͬid������bean�Ѿ�������һ�飬�ҽ�������Ǹ����ڵ�һλ
    //��������groupingcomparator�Ժ������յ���kv���ݾ��ǣ�  <1001 87.6>,null  <1001 76.5>,null  .... 
    //��ʱ��reduce�����еĲ���key��������kv���еĵ�һ��kv��key��<1001 87.6>
    //Ҫ���ͬһ��item�����ж�������������һ������ֻҪ������key	
	
	//�Լ����
	//partition������reduce֮ǰ�������Щ���ݽ�Ҫд��ʲô�ط�
	//group�����ǽ�n��ͬ�����ݸ�reduce����(���������Ǹ���ֻ�����һ���ֻ���(�����е����Ļ�)���Ǻ�������ݶ����ᱻ����)
}

