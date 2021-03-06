package com.example.reindex.second;

import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class TwoIndexReducer extends Reducer<Text, Text, Text, Text> {

	@Override
	protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
		// atguigu a.txt 3
		// atguigu b.txt 2
		// atguigu c.txt 2

		// atguigu c.txt-->2 b.txt-->2 a.txt-->3

		StringBuilder sb = new StringBuilder();

		for (Text value : values) {
			sb.append(value.toString().replace("\t", "-->") + "\t");
		}
		
		context.write(key, new Text(sb.toString()));
	}
}
