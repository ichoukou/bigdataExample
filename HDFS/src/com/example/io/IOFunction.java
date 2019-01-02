package com.example.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

/**
 * ������java����Ϊ��
 * @author tou
 *
 */
public class IOFunction {
	
	public void putFileToHDFS() throws Exception{
		// 1 ����������Ϣ����
		Configuration configuration = new Configuration();
		
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration, "atguigu");
		
		// 2 ����������
		FileInputStream inStream = new FileInputStream(new File("e:/hello.txt"));
		
		// 3 ��ȡ���·��
		String putFileName = "hdfs://hadoop102:9000/user/atguigu/hello1.txt";
		Path writePath = new Path(putFileName);

		// 4 ���������
		FSDataOutputStream outStream = fs.create(writePath);

		// 5 ���Խ�
		try{
			IOUtils.copyBytes(inStream, outStream, 4096, false);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			IOUtils.closeStream(inStream);
			IOUtils.closeStream(outStream);
		}
	}

	
	
	public void getFileToHDFS() throws Exception{
		// 1 ����������Ϣ����
		Configuration configuration = new Configuration();
		
		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration, "atguigu");
		
		// 2 ��ȡ��ȡ�ļ�·��
		String filename = "hdfs://hadoop102:9000/user/atguigu/hello1.txt";
		
		// 3 ������ȡpath
		Path readPath = new Path(filename);
		
		// 4 ����������
		FSDataInputStream inStream = fs.open(readPath);
		
		// 5 ���Խ����������̨
		try{
			IOUtils.copyBytes(inStream, System.out, 4096, false);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			IOUtils.closeStream(inStream);
		}
	}


	// ��λ���ص�һ������
	public void readFileSeek1() throws Exception {

		// 1 ����������Ϣ����
		Configuration configuration = new Configuration();

		FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "atguigu");

		// 2 ��ȡ������·��
		Path path = new Path("hdfs://hadoop102:9000/user/atguigu/tmp/hadoop-2.7.2.tar.gz");

		// 3 ��������
		FSDataInputStream fis = fs.open(path);

		// 4 ���������
		FileOutputStream fos = new FileOutputStream("e:/hadoop-2.7.2.tar.gz.part1");

		// 5 ���Խ�
		byte[] buf = new byte[1024];
		for (int i = 0; i < 128 * 1024; i++) {
			fis.read(buf);
			fos.write(buf);
		}

		// 6 �ر���
		IOUtils.closeStream(fis);
		IOUtils.closeStream(fos);
		}
	
	// ��λ���صڶ�������
		public void readFileSeek2() throws Exception{
			
			// 1 ����������Ϣ����
			Configuration configuration = new Configuration();

			FileSystem fs = FileSystem.get(new URI("hdfs://hadoop102:9000"), configuration, "atguigu");
			
			// 2 ��ȡ������·��
			Path path = new Path("hdfs://hadoop102:9000/user/atguigu/tmp/hadoop-2.7.2.tar.gz");
			
			// 3 ��������
			FSDataInputStream fis = fs.open(path);
			
			// 4 ���������
			FileOutputStream fos = new FileOutputStream("e:/hadoop-2.7.2.tar.gz.part2");
			
			// 5 ��λƫ�������ڶ������λ��
			fis.seek(1024 * 1024 * 128);
			
			// 6 ���Խ�
			IOUtils.copyBytes(fis, fos, 1024);
			
			// 7 �ر���
			IOUtils.closeStream(fis);
			IOUtils.closeStream(fos);
		}



}
