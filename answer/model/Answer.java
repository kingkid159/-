package answer.model;

import java.util.Date;

public class Answer {
	private Integer no;
	private String id;
	private String content;
	private Date regdate;
	private String delete;
	
	public Answer(Integer no, String id, String content,Date regdate,String delete) {
		this.no = no;
		this.id = id;
		this.content = content;
		this.regdate = regdate;
		this.delete = delete;
		System.out.println("answer.getcontent="+content);
	}
	public Answer(String id2) {
		// TODO Auto-generated constructor stub
	}
	public Integer getNo(){
		return no;
	}
	public String getId() {
		return id;
	}
	public String getContent() {
		System.out.println("answer.getgetcontent="+content);
		return content;
	}
	public Date getDate() {
		return regdate;
	}
	public String delete() {
		return delete;
	}
	@Override
	public String toString() {
		return "Answer [no=" + no + ", id=" + id + ", content=" + content + ", regdate=" + regdate + ", delete="
				+ delete + "]";
	}
	
}
