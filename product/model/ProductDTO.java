package product.model;

public class ProductDTO {

	//필드
	private int p_no;
	private int t_no;
	private String p_name;
	private int p_price;
	private String info;
	
	public ProductDTO(int p_no, int t_no, String p_name, int p_price, String info) {
		this.p_no = p_no;
		this.t_no = t_no;
		this.p_name = p_name;
		this.p_price =p_price;
		this.info = info;
	}
	public int getP_no() {
		return p_no;
	}
	public int getT_no() {
		return t_no;
	}
	public String getP_name() {
		return p_name;
	}
	public int getP_price() {
		return p_price;
	}
	public String getInfo() {
		return info;
	}
	public void setPno(int p_no) {
		this.p_no = p_no;
	}
	public void setTno(int t_no) {
		this.t_no = t_no;
	}
	public void setPname(String p_name) {
		this.p_name = p_name;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
	
	
	
}
