package review.service;

import java.util.Map;


public class WriteRequest {
	//게시글 쓰는데 필요한 작성자,제목,내용 데이터 정의
	private String id;
	private String title;
	private String content;
	private int productNumber;
	public WriteRequest(String id,String title, String content,int productNumber) {
		this.id = id;
		this.title =	title;
		this.content = content;
		this.productNumber=productNumber;
	}
	public int getPno() {
		return productNumber;
	}
	public String getWriter() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	//데이터 유효성 검사 error가 있으면 error맵 객체에 관련 코드 추가
	public void validate(Map<String, Boolean> errors) {
		if(title ==null || title.trim().isEmpty()) {
			errors.put("title",Boolean.TRUE);
		}
	}
}
