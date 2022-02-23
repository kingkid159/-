package qna.model;

import java.util.Date;

public class QnaModel {
private int qno;
private int ano;
private String qTitle;
private String qContent;
private String aContent;
private String qId;
private String aId;
private Date qRegDate;
private Date aRegDate;
private String aDelete;

public QnaModel(int qno,int ano, String qTitle, String qContent, String aContent ,String qId, String aId, Date qRegDate, Date aRegDate,String aDelete) {
	this.qno=qno;
	this.ano=ano;
	this.qTitle=qTitle;
	this.qContent=qContent;
	this.aContent=aContent;
	this.qRegDate=qRegDate;
	this.aRegDate=aRegDate;
	this.qId=qId;
	this.aId=aId;
	this.aDelete=aDelete;
}
public int getQno() {
	return qno;
}
public int getAno() {
	return ano;
}
public String getQTitle() {
	return qTitle;
}
public String getQContent() {
	return qContent;
}
public String getAContent() {
	return aContent;
}
public Date getQRegDate() {
	return qRegDate;
}
public Date getARegDate() {
	return aRegDate;
}
public String getAId() {
	return aId;
}
public String getQId() {
	return qId;
}
public String getADelete() {
	return aDelete;
}
@Override
public String toString() {
	return "QnaModel [qno=" + qno + ", ano=" + ano + ", qTitle=" + qTitle + ", qContent=" + qContent + ", aContent="
			+ aContent + ", qId=" + qId + ", aId=" + aId + ", qRegDate=" + qRegDate + ", aRegDate=" + aRegDate
			+ ", aDelete=" + aDelete + "]";
}

}
