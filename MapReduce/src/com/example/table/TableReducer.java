package com.example.table;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class TableReducer extends Reducer<Text, TableBean, TableBean, NullWritable> {

	@Override
	protected void reduce(Text key, Iterable<TableBean> values, Context context)
			throws IOException, InterruptedException {

		// 1׼���洢�����ļ���
		ArrayList<TableBean> orderBeans = new ArrayList<>();
		// 2 ׼��bean����
		TableBean pdBean = new TableBean();

		for (TableBean bean : values) {

			if ("0".equals(bean.getFlag())) {// ������
				// �������ݹ�����ÿ���������ݵ�������
				TableBean orderBean = new TableBean();
				try {
					BeanUtils.copyProperties(orderBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}

				orderBeans.add(orderBean);
			} else {// ��Ʒ��
				try {
					// �������ݹ����Ĳ�Ʒ���ڴ���
					BeanUtils.copyProperties(pdBean, bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

		// 3 ���ƴ��
		for(TableBean bean:orderBeans){
			bean.setP_id(pdBean.getPname());//��ʵ����д��order������ݣ�ֻ�����������Ϣ���ò�Ʒ�����Ϣ������
			
			// 4 ����д��ȥ
			context.write(bean, NullWritable.get());
		}
	}
}
