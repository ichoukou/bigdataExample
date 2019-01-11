package com.example.order.shuffle;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.example.order.bean.OrderBean;

/**
 * writableComparator则可以指定哪些bean是一个相同的key。
 * 	这样，不同的bean，也可以被reduce认为是同一个key
 * @author tou
 *
 */
public class OrderSortGroupingComparator extends WritableComparator {

	protected OrderSortGroupingComparator() {
		super(OrderBean.class, true);
	}
	
	/**
	 * 在reduce端利用groupingcomparator将订单id相同的kv聚合成组，然后取第一
	 */
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		OrderBean abean = (OrderBean) a;
		OrderBean bbean = (OrderBean) b;
		
		// 将orderId相同的bean都视为一组
		int compare = abean.getOrderId().compareTo(bbean.getOrderId());
		return compare;
	}
}
