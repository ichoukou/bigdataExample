package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.example.conf.HdfsEnum;

public class HdfsClientDemo1 {
	public static void main(String[] args) throws Exception {
		// 1 ��ȡ�ļ�ϵͳ
		Configuration configuration = new Configuration();
		// �����ڼ�Ⱥ������
		configuration.set("fs.defaultFS", HdfsEnum.HDFS102.getValue());// "192.168.29.102"
		FileSystem fileSystem = FileSystem.get(configuration);

		// ֱ�����÷��ʼ�Ⱥ��·���ͷ��ʼ�Ⱥ���û�����
//		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration, "atguigu");

		// 2 �ѱ����ļ��ϴ����ļ�ϵͳ��
		fileSystem.copyFromLocalFile(new Path(HdfsEnum.FILE.getValue()+"hellos.txt"), new Path("/hellos1.copy.txt"));

		// 3 �ر���Դ
		fileSystem.close();
		System.out.println("over"); 
	}

}
