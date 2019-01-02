package com.example.txt;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

/**
 	写入数据时，如果希望数据被其他client立即可见，调用如下方法
	FsDataOutputStream. hflush ();		//清理客户端缓冲区数据，被其他client立即可见
 * @author tou
 *
 */
public class ConsistentClient {
	public void writeFile() throws Exception{
		// 1 创建配置信息对象
		Configuration configuration = new Configuration();
		FileSystem fs = FileSystem.get(configuration);
		
		// 2 创建文件输出流
		Path path = new Path("hdfs://hadoop102:9000/user/atguigu/hello.txt");
		FSDataOutputStream fos = fs.create(path);
		
		// 3 写数据
		fos.write("hello".getBytes());
		
        // 4 一致性刷新
		fos.hflush();//清理客户端缓冲区数据，被其他client立即可见
		
		fos.close();
	}

}
