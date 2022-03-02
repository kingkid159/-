package product.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;

import product.service.ProductList;
import product.service.ProductListService;
import product.service.ProductNotFoundException;


public class ProductListHandler implements CommandHandler{
	private static final String FORM_VIEW = "view/product/productList.jsp";
	private ProductListService productListService =new ProductListService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Map<String,Boolean> errors = new HashMap<String,Boolean>(); //에러정보를 저장하기위한 변수선언-Map(키명,에러정보값)방식으로 저장
			request.setAttribute("errors",errors);
			String strTno=request.getParameter("t_no").trim();
			int t_no= Integer.parseInt(strTno);
			try {
				ProductList productList =productListService.getProduct(t_no);
				System.out.println(strTno);
				request.setAttribute("productList", productList);
				System.out.println( productList.getContent());
				System.out.println( productList.getT_no());
				return FORM_VIEW;
			}catch(ProductNotFoundException e){
				errors.put("NotFound",Boolean.TRUE);
				return FORM_VIEW;	
				
			}
		
	}
	//문자열 좌우의 공백을 제거-p607 64라인
		private String trim(String str) {
			//매개변수로 넘어온 문자열이 null이면   null을 리턴
			//매개변수로 넘어온 문자열이 null아니면 문자열 좌우의 공백을 제거하여 리턴
			//     (조건)?true일경우:false경우 
			return (str==null)? null: str.trim();
		}
		@Override
		public String toString() {
			return "ProductHandler [productService=" + productListService + "]";
		}
	
}
