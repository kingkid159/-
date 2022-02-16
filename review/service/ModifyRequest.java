package review.service;

import java.util.Map;

public class ModifyRequest {

	private String userId;
	private int reviewNumber;
	private String title;
	private String content;
	
	public ModifyRequest(String userId, int reviewNumber, String title, String content) {
		this.userId=userId;
		this.reviewNumber=reviewNumber;
		this.title=title;
		this.content=content;
		System.out.println("userId="+userId);
		System.out.println("title="+title);
		System.out.println("content"+content);
	}
	
	@Override
	public String toString() {
		return "ModifyRequest [userId=" + userId + ", reviewNumber=" + reviewNumber + ", title=" + title + ", content="
				+ content + "]";
	}

	public String getUserId() {
		return userId;
	}
	public int getReviewNumber() {
		return reviewNumber;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	
	public void validate(Map<String,Boolean>errors) {
		if(title == null || title.trim().isEmpty()) {
			errors.put("title", Boolean.TRUE);
		}
	}
}
