package product.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import jdbc.connection.ConnectionProvider;
import product.dao.ProductDAO;
import product.model.ProductDTO;

public class ProductListService {
	private ProductDAO productDAO= new ProductDAO();
	
	public ProductList getProduct(int t_no) {
		Connection conn=null;
		try {
			conn = ConnectionProvider.getConnection();
			List<ProductDTO> content=productDAO.selectByTno(conn,t_no);
			return new ProductList(t_no,content);
		}catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		
	}
	
}
