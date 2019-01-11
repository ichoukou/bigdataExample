package com.example.conf;

public enum FileLocationEnum {
	SUCCESS("1", "成功"), FAILED("2", "失败"),
	
	WORDCOUNTINPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/input/*", "带星号表示input文件夹里面的所有文件进行统计"),
	WORDCOUNTOUTPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/output", "如果output文件夹已存在，会报错"),

	FLOWSUMINPUT("D:/work/soft/Linux/localFile/mapreduce/flowsum/input/*", "带星号表示input文件夹里面的所有文件进行统计"),
	FLOWSUMOUTPUT("D:/work/soft/Linux/localFile/mapreduce/flowsum/output", "如果output文件夹已存在，会报错"),

	ORDERINPUT("D:/work/soft/Linux/localFile/mapreduce/order/input/*", "带星号表示input文件夹里面的所有文件进行统计"),
	ORDEROUTPUT("D:/work/soft/Linux/localFile/mapreduce/order/output", "如果output文件夹已存在，会报错"),

	TABLEINPUT("D:/work/soft/Linux/localFile/mapreduce/table/input/*", "带星号表示input文件夹里面的所有文件进行统计"),
	TABLEOUTPUT("D:/work/soft/Linux/localFile/mapreduce/table/output", "如果output文件夹已存在，会报错"),
	ORDERTXT("D:/work/soft/Linux/localFile/mapreduce/table/input/order.txt", ""),
	PDTXT("D:/work/soft/Linux/localFile/mapreduce/table/input/pd.txt", ""),

	INPUTFORMATINPUT("D:/work/soft/Linux/localFile/mapreduce/inputformat/input/*", "带星号表示input文件夹里面的所有文件进行统计"),
	INPUTFORMATOUTPUT("D:/work/soft/Linux/localFile/mapreduce/inputformat/output", "如果output文件夹已存在，会报错"),

	OUTPUTFORMATINPUT("D:/work/soft/Linux/localFile/mapreduce/outputformat/input/*", "带星号表示input文件夹里面的所有文件进行统计"),
	OUTPUTFORMATOUTPUT("D:/work/soft/Linux/localFile/mapreduce/outputformat/output", "如果output文件夹已存在，会报错"),

	ATGUIGULOG("D:/work/soft/Linux/localFile/mapreduce/outputformat/atguigu.log", "文件会自动生成"),
	OTHERLOG("D:/work/soft/Linux/localFile/mapreduce/outputformat/other.log", "文件会自动生成");

	
	
    private String value;
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    private FileLocationEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
