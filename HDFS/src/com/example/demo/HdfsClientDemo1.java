package com.example.demo;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import com.example.conf.HdfsEnum;

public class HdfsClientDemo1 {
	public static void main(String[] args) throws Exception {
		// 1 获取文件系统
		Configuration configuration = new Configuration();
		// 配置在集群上运行
		configuration.set("fs.defaultFS", HdfsEnum.HDFS102.getValue());// "192.168.29.102"
		FileSystem fileSystem = FileSystem.get(configuration);

		// 直接配置访问集群的路径和访问集群的用户名称
//		FileSystem fileSystem = FileSystem.get(new URI("hdfs://hadoop102:9000"),configuration, "atguigu");

		// 2 把本地文件上传到文件系统中
		fileSystem.copyFromLocalFile(new Path(HdfsEnum.FILE.getValue()+"hellos.txt"), new Path("/hellos1.copy.txt"));

		// 3 关闭资源
		fileSystem.close();
		System.out.println("over"); 
	}

}
