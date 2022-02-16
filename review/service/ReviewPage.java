package review.service;

import java.util.List;

import review.model.Review;

public class ReviewPage {
	/*
	 * total은 전체 게시글의 개수를 보관, currentPage는 사용자가 요청한 페이지 번호를 보관, content는
	 * 게시글목록,totalPages는 전체페이지 개수보관 startPage아 endPage는 화면 하단에 보여줄 페이지 이동 링크의 시작 번호와
	 * 끝번호를 저장한다
	 */
	private int total;
	private int currentPage;
	private List<Review> content;
	private int totalPages;
	private int startPage;
	private int endPage;
	
public ReviewPage(int total, int currentPage, int size, List<Review> content) {
	this.total=total;
	this.currentPage=currentPage;
	this.content=content;
	//게시글 개수가 0개이면, totalPages,startPage,endPage를 모두 0으로 할당
	if(total ==0) {
		totalPages = 0;
		startPage= 0;
		endPage = 0;
	}else {
		/*
		 * 게시글 개수를 이용해서 전체 페이지 개수를 구한다. 한 페이지에 보여줄 게시글 개수를 size로 전달받고 개시글 개수를 size로 나눈
		 * 값을 페이지 개수로 사용한다 단 전체 게시글 개수를 나눈 나머지가 0보다 크면 페이지수를 1 증가한다
		 */
		totalPages = total/size;
		if(total % size >0) {
			totalPages++;
		}
		//화면 하단에 보여줄 페이지 이동 링크의 시작 페이지 번호를 구한다
		int modVal = currentPage % 5;
		startPage = currentPage / 5 * 5 + 1;
		if (modVal == 0)startPage -= 5;
		//화면 하단에 보여줄 페이지 이동 링크의 끝 페이지 번호를 구한다.
		endPage = startPage +4;
		if(endPage > totalPages) endPage = totalPages;
	}
}
public int getTotal() {
	return total;
}
public boolean hasNoReview() {

	return total == 0;
	
}
public boolean hasReview() {
	return total > 0;
}
public int getCurrentPage() {
	return currentPage;
}
public int getTotalPages() {
	return totalPages;
}
public List<Review> getContent(){
	return content;
}
public int getStartPage() {
	return startPage;
}
public int getEndPage() {
	return endPage;
}
@Override
public String toString() {
	return "ReviewPage [total=" + total + ", currentPage=" + currentPage + ", content=" + content + ", totalPages="
			+ totalPages + ", startPage=" + startPage + ", endPage=" + endPage + "]";
}

}
