package com.example.wordcount.conf;

public enum FileLocationEnum {
	SUCCESS("1", "³É¹¦"), FAILED("2", "Ê§°Ü"),
	
	WORDCOUNTINPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/input", "no desc"),
	WORDCOUNTOUTPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/output1", "no desc");

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
