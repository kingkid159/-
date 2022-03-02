package product.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import jdbc.JdbcUtil;
import product.model.ProductDTO;

public class ProductDAO {
	//t_no�� ��ȸ�ؼ� �ش� t_no�� �ش��ϴ� ��ǰ���� ����Ʈ�� �޾ƿ´� 
	public List<ProductDTO>selectByTno (Connection conn, int t_no)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select * from product   where t_no=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,t_no);
			rs=pstmt.executeQuery();
			List<ProductDTO> content = new ArrayList<>();
			while(rs.next()) {
				content.add(convertProduct(rs));
			}
			
			return content;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			
		}
	}
	//�ϳ��� ��ǰ��ȸ ���
	public ProductDTO selectByPno(Connection conn, int p_no)
			throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql ="select * from product   where p_no=?";
		ProductDTO productDTO=null;
		try {	
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,p_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				productDTO= convertProduct(rs);
			}
			return productDTO;
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(conn);
		}
	}
	private ProductDTO convertProduct(ResultSet rs) throws  SQLException{
		return new ProductDTO(rs.getInt("p_no"),
											rs.getInt("t_no"),
											rs.getString("p_name"),
											rs.getInt("p_price"),
											rs.getString("info"));
	}

	

}
	
	
	
	
	

