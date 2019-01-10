package com.example.wordcount.conf;

public enum FileLocationEnum {
	SUCCESS("1", "成功"), FAILED("2", "失败"),
	
	WORDCOUNTINPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/input/*", "带星号表示input文件夹里面的所有文件进行统计"),
	WORDCOUNTOUTPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/output8", "如果output文件夹已存在，会报错");

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
