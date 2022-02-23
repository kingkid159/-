package review.model;


import java.util.Date;

public class Review {
	private Integer number;
	private String writer;
	private String title;
	private Date regDate;
	private Integer pno;
	private int readCount;
	private String content;
	private String delete;
	
	public Review(Integer number, String writer,Integer pno, String title, String content,Date regDate,int readCount,String delete) {
		
		this.number=number;
		this.writer=writer;
		this.title=title;
		this.regDate=regDate;
		this.readCount=readCount;
		this.pno=pno;
		this.delete = delete;
		this.content = content;
	}
	

	public Integer getNumber() {
		System.out.println("number"+number);
		return number;
	}
	public String getContent() {
		return content;
	}

	public Integer getPno() {
		System.out.println("pno="+pno);
		return pno;
	}
	public String getwriter() {
		return writer;
	}
	public String getTitle() {
		return title;
	}
	public Date getRegDate() {
		return regDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public String getDelete() {
		return delete;
	}
	@Override
	public String toString() {
		return "Review [number=" + number + ", writer=" + writer + ", title=" + title + ", regDate=" + regDate
				+ ", pno=" + pno + ", readCount=" + readCount + ", content=" + content + "]";
	}
	
}

