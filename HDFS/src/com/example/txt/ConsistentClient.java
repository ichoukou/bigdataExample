package com.example.txt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 	д������ʱ�����ϣ�����ݱ�����client�����ɼ����������·���
	FsDataOutputStream. hflush ();		//����ͻ��˻��������ݣ�������client�����ɼ�
 * @author tou
 *
 */
public class ConsistentClient {
	public void writeFile() throws Exception{
		// 1 ����������Ϣ����
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		
		// 2 �����ļ������
		Path path = new Path("hdfs://hadoop102:9000/user/atguigu/hello.txt");
		FSDataOutputStream fos = fs.create(path);
		
		// 3 д����
		fos.write("hello".getBytes());
		
        // 4 һ����ˢ��
		fos.hflush();//����ͻ��˻��������ݣ�������client�����ɼ�
		
		fos.close();
	}

}
