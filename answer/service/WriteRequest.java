package answer.service;



import java.util.Map;

import answer.model.Answer;

public class WriteRequest {
		private int no;
		private String id;
		private String content;
		public WriteRequest(String id, String content, int no) {
			this.id=id;
			this.content=content;
			this.no=no;
		}
		public String getId() {
			return id;
		}
		public String getContent() {
			return content;
		}
		public int getNo() {
			return no;
		}
		public void validate(Map<String, Boolean> errors) {
			// TODO Auto-generated method stub
			
		}
		
}
