package product.command;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import product.service.ProductData;
import product.service.ProductList;

import product.service.ProductNotFoundException;
import product.service.ProductService;

public class ProductHandler implements CommandHandler{
	private static final String FORM_VIEW = "view/product/productData.jsp";
	private ProductService productService =new ProductService();
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
			Map<String,Boolean> errors = new HashMap<String,Boolean>(); //���������� �����ϱ����� ��������-Map(Ű��,����������)������� ����
			request.setAttribute("errors",errors);
			String strPno=request.getParameter("p_no").trim();
			int p_no= Integer.parseInt(strPno);
			try {
				ProductData productData=productService.getProduct(p_no);
				System.out.println(strPno);
				request.setAttribute("productData",productData);
				System.out.println( productData.getProductDTO().getInfo());
				System.out.println( productData.getProductDTO());
				System.out.println( productData.getProductDTO().getP_no());
				return FORM_VIEW;
			}catch(ProductNotFoundException e){
				errors.put("NotFound",Boolean.TRUE);
				return FORM_VIEW;	
				
			}
		
	}
	//���ڿ� �¿��� ������ ����-p607 64����
		private String trim(String str) {
			//�Ű������� �Ѿ�� ���ڿ��� null�̸�   null�� ����
			//�Ű������� �Ѿ�� ���ڿ��� null�ƴϸ� ���ڿ� �¿��� ������ �����Ͽ� ����
			//     (����)?true�ϰ��:false��� 
			return (str==null)? null: str.trim();
		}
		@Override
		public String toString() {
			return "ProductHandler [productService=" + productService + "]";
		}
}
