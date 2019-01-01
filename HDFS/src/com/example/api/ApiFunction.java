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
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 * @throws Exception
	 */
	public void initHDFS() throws Exception {
		
		// 1 ����������Ϣ����
		// new Configuration();��ʱ�����ͻ�ȥ����jar���е�hdfs-default.xml
		// Ȼ���ټ���classpath�µ�hdfs-site.xml
		Configuration configuration = new Configuration();

		// 2 ���ò���
		// �������ȼ��� 1���ͻ��˴��������õ�ֵ 2��classpath�µ��û��Զ��������ļ� 3��Ȼ���Ƿ�������Ĭ������
//		configuration.set("fs.defaultFS", "hdfs://hadoop102:9000");
		configuration.set("dfs.replication", "3");

		// 3 ��ȡ�ļ�ϵͳ
		FileSystem fs = FileSystem.get(configuration);

		// 4 ��ӡ�ļ�ϵͳ
		System.out.println(fs.toString());

	}

	FileSystem initHDFSEx() throws Exception {
		// 1 ����������Ϣ����
		Configuration configuration = new Configuration();

		FileSystem fs = FileSystem.get(new URI(HdfsEnum.HDFS102.getValue()), configuration, HdfsEnum.USER.getValue());

		return fs;
	}

	/**
	 * �ϴ��ļ�
	 * 
	 * @throws Exception
	 */
	public void putFileToHDFS() throws Exception {

		// 1 ����������Ϣ����
		FileSystem fs = initHDFSEx();

		// 2 ����Ҫ�ϴ��ļ����ڵı���·��
		Path src = new Path(HdfsEnum.FILE.getValue() + "putFileToHDFS2.txt");

		// 3 ����Ҫ�ϴ���hdfs��Ŀ��·��
		Path dst = new Path(HdfsEnum.HDFS102.getValue() + "/user/atguigu/putFileToHDFS2.txt");

		// 4 �����ļ�
		fs.copyFromLocalFile(src, dst);
		fs.close();
	}

	/**
	 * �����ļ�
	 * 
	 * @throws Exception
	 */
	public void getFileFromHDFS() throws Exception {

		FileSystem fs = initHDFSEx();

//		fs.copyToLocalFile(new Path("hdfs://hadoop102:9000/user/atguigu/hello.txt"), new Path("d:/hello.txt"));
		// boolean delSrc ָ�Ƿ�ԭ�ļ�ɾ��
		// Path src ָҪ���ص��ļ�·��
		// Path dst ָ���ļ����ص���·��
		// boolean useRawLocalFileSystem �Ƿ����ļ�Ч��
		// 2 �����ļ�
		fs.copyToLocalFile(false, new Path(HdfsEnum.HDFS102.getValue() + "/user/atguigu/hello.txt"),
				new Path(HdfsEnum.FILE.getValue() + "hellocopy.txt"), true);
		fs.close();
	}

	/**
	 * ɾ���ļ���
	 * 
	 * @throws Exception
	 */
	public void deleteAtHDFS() throws Exception {
		// 1 ����������Ϣ����
		FileSystem fs = initHDFSEx();

		// 2 ɾ���ļ��� ������Ƿǿ��ļ��У�����2�����ֵtrue��ɾ���ļ����Լ��ļ����ݡ�
		fs.delete(new Path(HdfsEnum.HDFS102.getValue() + "/user/atguigu/output"), true);
	}

	/**
	 * �ļ�������
	 * @throws Exception
	 */
	public void renameAtHDFS() throws Exception {
		// 1 ����������Ϣ����
		FileSystem fs = initHDFSEx();

		// 2 �������ļ����ļ���
		fs.rename(new Path("hdfs://hadoop102:9000/user/atguigu/hello.txt"),
				new Path("hdfs://hadoop102:9000/user/atguigu/hellonihao.txt"));
	}
	
	/**
	 *  HDFS�ļ�����鿴
	 * @throws Exception
	 */
	public void readListFiles() throws Exception {
		// 1 ����������Ϣ����
		FileSystem fs = initHDFSEx();
		
		// ˼����Ϊʲô���ص�������������List֮�������
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(new Path("/"), true);
		//1������ļ������޴���ô��ȫ���������ж˵��ڴ��У����ж˻�Բ�����
		//2����������list�޴󣬴��佫���Ĵ���ʱ�䡣
		//��ʱ�����Ҫʹ�õ����������ٷ���һ��list���󣬶��Ƿ���һ��������ʵ������Ȼ�����hasnext�����ж��Ƿ�����һ�����ݣ��е�������ٵ���next����ȡ���ݡ��������Ĵ����ڴ档

		
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
				
			System.out.println("--------------������ķָ���--------------");
		}
		}

	/**
	 * ��Ŀ¼���ļ������ļ���Ϣ
	 * @throws Exception
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	public void findAtHDFS() throws Exception, IllegalArgumentException, IOException{
		
		// 1 ����������Ϣ����
		FileSystem fs = initHDFSEx();
		
		// 2 ��ȡ��ѯ·���µ��ļ�״̬��Ϣ
		FileStatus[] listStatus = fs.listStatus(new Path("/"));

		// 3 ���������ļ�״̬
		for (FileStatus status : listStatus) {
			if (status.isFile()) {
				System.out.println("f--" + status.getPath().getName());
			} else {
				System.out.println("d--" + status.getPath().getName());
			}
		}
	}

}
