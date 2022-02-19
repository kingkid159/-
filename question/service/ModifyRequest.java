package question.service;

import java.util.Map;

public class ModifyRequest {
	
	private String userId;
	private int questionNumber;
	private String title;
	private String content;
	
	public ModifyRequest(String userId, int questionNumber, String title, String content) {
		this.userId=userId;
		this.questionNumber=questionNumber;
		this.title=title;
		this.content=content;
	}
	public String getUserId() {
		return userId;
	}
	public int getQuestionNumber() {
		return questionNumber;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	
	public void validate(Map<String,Boolean>errors) {
		if(title==null||title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
	}
}
