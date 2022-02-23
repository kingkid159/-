package question.service;

import java.util.Map;

import question.model.Question;

public class WriteRequest {
	private String id;
	private String title;
	private String content;
	private int productNumber;
	private int questionNumber;
	public WriteRequest(String id, String title, String content,int productNumber) {
		this.id=id;
		this.title=title;
		this.content=content;
		this.productNumber=productNumber;
		System.out.println("productNumber="+productNumber);
	}
	public WriteRequest(String id, String content, int questionNumber) {
		this.id=id;
		this.content=content;
		this.questionNumber=questionNumber;
	}
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public int getPno() {
		
		return productNumber;
	}
	public int getQno() {
		return questionNumber;
	}
	public void validate(Map<String, Boolean>errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
