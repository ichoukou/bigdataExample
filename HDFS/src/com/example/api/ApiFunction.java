package com.example.api;

import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;

import com.example.conf.HdfsEnum;

public class ApiFunction {

	/**
	 * 获取对象信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void initHDFS() throws Exception {
		
		// 1 创建配置信息对象
		// new Configuration();的时候，它就会去加载jar包中的hdfs-default.xml
		// 然后再加载classpath下的hdfs-site.xml
		Configuration configuration = new Configuration();

		// 2 设置参数
		// 参数优先级： 1、客户端代码中设置的值 2、classpath下的用户自定义配置文件 3、然后是服务器的默认配置
//		configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
		configuration.set("dfs.replication", "3");

		// 3 获取文件系统
		FileSystem fs = FileSystem.get(configuration);

		// 4 打印文件系统
		System.out.println(fs.toString());

	}

	FileSystem initHDFSEx() throws Exception {
		// 1 创建配置信息对象
		Configuration configuration = new Configuration();

		FileSystem fs = FileSystem.get(new URI(HdfsEnum.HDFS102.getValue()), configuration, HdfsEnum.USER.getValue());

		return fs;
	}

	/**
	 * 上传文件
	 * 
	 * @throws Exception
	 */
	public void putFileToHDFS() throws Exception {

		// 1 创建配置信息对象
		FileSystem fs = initHDFSEx();

		// 2 创建要上传文件所在的本地路径
		Path src = new Path(HdfsEnum.FILE.getValue() + "putFileToHDFS2.txt");

		// 3 创建要上传到hdfs的目标路径
		Path dst = new Path(HdfsEnum.HDFS102.getValue() + "/user/atguigu/putFileToHDFS2.txt");

		// 4 拷贝文件
		fs.copyFromLocalFile(src, dst);
		fs.close();
	}

	/**
	 * 下载文件
	 * 
	 * @throws Exception
	 */
	public void getFileFromHDFS() throws Exception {

		FileSystem fs = initHDFSEx();

//		fs.copyToLocalFile(new Path("hdfs://hadoop102:9000/user/atguigu/hello.txt"), new Path("d:/hello.txt"));
		// boolean delSrc 指是否将原文件删除
		// Path src 指要下载的文件路径
		// Path dst 指将文件下载到的路径
		// boolean useRawLocalFileSystem 是否开启文件效验
		// 2 下载文件
		fs.copyToLocalFile(false, new Path(HdfsEnum.HDFS102.getValue() + "/user/atguigu/hello.txt"),
				new Path(HdfsEnum.FILE.getValue() + "hellocopy.txt"), true);
		fs.close();
	}

	/**
	 * 删除文件夹
	 * 
	 * @throws Exception
	 */
	public void deleteAtHDFS() throws Exception {
		// 1 创建配置信息对象
		FileSystem fs = initHDFSEx();

		// 2 删除文件夹 ，如果是非空文件夹，参数2必须给值true【删除文件夹以及文件内容】
		fs.delete(new Path(HdfsEnum.HDFS102.getValue() + "/user/atguigu/output"), true);
	}

	/**
	 * 文件重命名
	 * @throws Exception
	 */
	public void renameAtHDFS() throws Exception {
		// 1 创建配置信息对象
		FileSystem fs = initHDFSEx();

		// 2 重命名文件或文件夹
		fs.rename(new Path("hdfs://hadoop102:9000/user/atguigu/hello.txt"),
				new Path("hdfs://hadoop102:9000/user/atguigu/hellonihao.txt"));
	}
	
	/**
	 *  HDFS文件详情查看
	 * @throws Exception
	 */
	public void readListFiles() throws Exception {
		// 1 创建配置信息对象
		FileSystem fs = initHDFSEx();
		
		// 思考：为什么返回迭代器，而不是List之类的容器
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
		//1、如果文件数量巨大，那么将全部放入运行端的内存中，运行端会吃不消。
		//2、而且由于list巨大，传输将消耗大量时间。
		//这时候就需要使用迭代器，不再返回一个list对象，而是返回一个迭代器实例对象，然后调用hasnext（）判断是否有下一个数据，有的情况下再调用next方法取数据。不再消耗大量内存。

		
		while (listFiles.hasNext()) {
			LocatedFileStatus fileStatus = listFiles.next();
				
			System.out.println(fileStatus.getPath().getName());
			System.out.println(fileStatus.getBlockSize());
			System.out.println(fileStatus.getPermission());
			System.out.println(fileStatus.getLen());
				
			BlockLocation[] blockLocations = fileStatus.getBlockLocations();
				
			for (BlockLocation bl : blockLocations) {
					
				System.out.println("block-offset:" + bl.getOffset());
					
				String[] hosts = bl.getHosts();
					
				for (String host : hosts) {
					System.out.println(host);
				}
			}
				
			System.out.println("--------------李冰冰的分割线--------------");
		}
		}

	/**
	 * 该目录下文件夹与文件信息
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public void findAtHDFS() throws Exception, IllegalArgumentException, IOException{
		
		// 1 创建配置信息对象
		FileSystem fs = initHDFSEx();
		
		// 2 获取查询路径下的文件状态信息
		FileStatus[] listStatus = fs.listStatus(new Path("/"));

		// 3 遍历所有文件状态
		for (FileStatus status : listStatus) {
			if (status.isFile()) {
				System.out.println("f--" + status.getPath().getName());
			} else {
				System.out.println("d--" + status.getPath().getName());
			}
		}
	}

}
