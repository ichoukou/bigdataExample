package com.example.wordcount.conf;

public enum FileLocationEnum {
	SUCCESS("1", "�ɹ�"), FAILED("2", "ʧ��"),
	
	WORDCOUNTINPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/input/*", "���Ǻű�ʾinput�ļ�������������ļ�����ͳ��"),
	WORDCOUNTOUTPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/output8", "���output�ļ����Ѵ��ڣ��ᱨ��");

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
