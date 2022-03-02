package product.service;

import product.model.ProductDTO;

public class ProductData {
	private ProductDTO productDTO;
	
	public ProductData(ProductDTO productDTO) {
		
		this.productDTO = productDTO;
		;
	}
	public ProductDTO getProductDTO() {
		return productDTO;
	}

	public void setProductDTO(ProductDTO productDTO) {
		this.productDTO = productDTO;
	}

}
