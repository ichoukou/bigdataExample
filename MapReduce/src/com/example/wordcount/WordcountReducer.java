package com.example.wordcount;
import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * KEYIN , VALUEIN ��Ӧmapper�����KEYOUT, VALUEOUT����
 * 
 * KEYOUT��VALUEOUT ��Ӧ�Զ���reduce�߼�������������������� KEYOUT�ǵ��� VALUEOUT���ܴ���
* @author Administrator
*/
public class WordcountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	/**
	 * key����һ����ͬ����kv�Ե�key
	 */
	@Override
	protected void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int count = 0;

		// 1 ���ܸ���key�ĸ���
		for(IntWritable value:values){
			count +=value.get();
		}
		
		// 2�����key���ܴ���
		context.write(key, new IntWritable(count));
	}
}
