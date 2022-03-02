package product.service;

import java.util.List;

import product.model.ProductDTO;

public class ProductList {
	private int t_no;
	private List<ProductDTO> content;

	public ProductList(int t_no, List<ProductDTO> content) {
		this.t_no = t_no;
		this.content = content;
	}

	public int getT_no() {
		return t_no;
	}

	public void setT_no(int t_no) {
		this.t_no = t_no;
	}

	public List<ProductDTO> getContent() {
		return content;
	}

	public void setContent(List<ProductDTO> content) {
		this.content = content;
	}


	
}
