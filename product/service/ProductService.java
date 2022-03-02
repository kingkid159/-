package product.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.connection.ConnectionProvider;
import product.dao.ProductDAO;
import product.model.ProductDTO;


public class ProductService {
	private ProductDAO productDao = new ProductDAO();
	
	public ProductData getProduct(int p_no) throws SQLException{
		try(Connection conn = ConnectionProvider.getConnection()){
			ProductDTO productDTO =productDao.selectByPno(conn, p_no);
			if(productDTO.getP_no() == 0){
				throw new ProductNotFoundException();
			}
			return new ProductData(productDTO);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
