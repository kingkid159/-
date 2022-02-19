package question.service;

import java.util.Map;

import question.model.Question;

public class WriteRequest {
	private String id;
	private String title;
	private String content;
	public WriteRequest(Question question, String title, String content) {
		this.id=question.getId();
		this.title=title;
		this.content=content;
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
	
	public void validate(Map<String, Boolean>errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
