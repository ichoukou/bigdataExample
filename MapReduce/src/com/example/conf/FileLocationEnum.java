package com.example.conf;

public enum FileLocationEnum {
	SUCCESS("1", "�ɹ�"), FAILED("2", "ʧ��"),
	
	WORDCOUNTINPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/input/*", "���Ǻű�ʾinput�ļ�������������ļ�����ͳ��"),
	WORDCOUNTOUTPUT("D:/work/soft/Linux/localFile/mapreduce/wordcount/output", "���output�ļ����Ѵ��ڣ��ᱨ��"),

	FLOWSUMINPUT("D:/work/soft/Linux/localFile/mapreduce/flowsum/input/*", "���Ǻű�ʾinput�ļ�������������ļ�����ͳ��"),
	FLOWSUMOUTPUT("D:/work/soft/Linux/localFile/mapreduce/flowsum/output", "���output�ļ����Ѵ��ڣ��ᱨ��"),

	ORDERINPUT("D:/work/soft/Linux/localFile/mapreduce/order/input/*", "���Ǻű�ʾinput�ļ�������������ļ�����ͳ��"),
	ORDEROUTPUT("D:/work/soft/Linux/localFile/mapreduce/order/output", "���output�ļ����Ѵ��ڣ��ᱨ��"),

	TABLEINPUT("D:/work/soft/Linux/localFile/mapreduce/table/input/*", "���Ǻű�ʾinput�ļ�������������ļ�����ͳ��"),
	TABLEOUTPUT("D:/work/soft/Linux/localFile/mapreduce/table/output", "���output�ļ����Ѵ��ڣ��ᱨ��"),
	ORDERTXT("D:/work/soft/Linux/localFile/mapreduce/table/input/order.txt", ""),
	PDTXT("D:/work/soft/Linux/localFile/mapreduce/table/input/pd.txt", ""),

	INPUTFORMATINPUT("D:/work/soft/Linux/localFile/mapreduce/inputformat/input/*", "���Ǻű�ʾinput�ļ�������������ļ�����ͳ��"),
	INPUTFORMATOUTPUT("D:/work/soft/Linux/localFile/mapreduce/inputformat/output", "���output�ļ����Ѵ��ڣ��ᱨ��"),

	OUTPUTFORMATINPUT("D:/work/soft/Linux/localFile/mapreduce/outputformat/input/*", "���Ǻű�ʾinput�ļ�������������ļ�����ͳ��"),
	OUTPUTFORMATOUTPUT("D:/work/soft/Linux/localFile/mapreduce/outputformat/output", "���output�ļ����Ѵ��ڣ��ᱨ��"),

	ATGUIGULOG("D:/work/soft/Linux/localFile/mapreduce/outputformat/atguigu.log", "�ļ����Զ�����"),
	OTHERLOG("D:/work/soft/Linux/localFile/mapreduce/outputformat/other.log", "�ļ����Զ�����");

	
	
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
