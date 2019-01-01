package com.example.conf;

public enum HdfsEnum {
	SUCCESS("1", "³É¹¦"), FAILED("2", "Ê§°Ü"),
	
	USER("tou", "no desc"),
	
	HDFS102("hdfs://hadoop102:9000","no desc"),
	HADOOP109("192.168.29.109","no desc"),
	
	FILE("D:/work/soft/Linux/localFile/", "no desc");

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

    private HdfsEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
