package product.service;

import java.util.Map;

public class ProductRequest {
	private String p_name;
	private int p_pirce;
	private String info;
	
	
	
	public ProductRequest(String p_name, int p_pirce, String info) {
		this.p_name = p_name;
		this.p_pirce = p_pirce;
		this.info = info;
	}
	public String getP_name() {
		return p_name;
	}
	public int getP_pirce() {
		return p_pirce;
	}
	public String getInfo() {
		return info;
	}
	public void validate(Map<String, Boolean> errors) {
		if(p_name ==null || p_name.trim().isEmpty()) {
			errors.put("p_name",Boolean.TRUE);
		}if(info ==null || info.trim().isEmpty()) {
			errors.put("info",Boolean.TRUE);
		}
	}
	
}
