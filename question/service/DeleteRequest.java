package question.service;

import java.util.Map;

public class DeleteRequest {
	
	private String userId;
	private int questionNumber;
	private String title;
	private String content;
	
	public DeleteRequest(String userId, int questionNumber, String title, String content) {
		this.userId=userId;
		this.questionNumber=questionNumber;
		this.title=title;
		this.content=content;
		System.out.println("userId"+userId);
		System.out.println("title"+title);
		System.out.println("questionNumber"+questionNumber);
	}
	@Override
	public String toString() {
		return "DeleteRequest [userId=" + userId + ", questionNumber=" + questionNumber + ", title=" + title
				+ ", content=" + content + "]";
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
