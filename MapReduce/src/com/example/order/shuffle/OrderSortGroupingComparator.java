package com.example.order.shuffle;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

import com.example.order.bean.OrderBean;

/**
 * writableComparator�����ָ����Щbean��һ����ͬ��key��
 * 	��������ͬ��bean��Ҳ���Ա�reduce��Ϊ��ͬһ��key
 * @author tou
 *
 */
public class OrderSortGroupingComparator extends WritableComparator {

	protected OrderSortGroupingComparator() {
		super(OrderBean.class, true);
	}
	
	/**
	 * ��reduce������groupingcomparator������id��ͬ��kv�ۺϳ��飬Ȼ��ȡ��һ
	 */
	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		
		OrderBean abean = (OrderBean) a;
		OrderBean bbean = (OrderBean) b;
		
		// ��orderId��ͬ��bean����Ϊһ��
		int compare = abean.getOrderId().compareTo(bbean.getOrderId());
		return compare;
	}
}
