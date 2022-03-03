package auth.service;

import member.model.MemberDTO;

//P604
//로그인을 한 유저에 대한 정보를 저장을 하는 클래스
//로그인을 한 유저 -> 권한이 있는 유저
public class User  extends MemberDTO {
	//필드
	private String id;	//회원id
	private String name;		//회원명
	private String password;	
	private String birth;
	private String gender;
	private String email;
	private String phone;
	private String address;
	
	//생성자
	public User() {}
	
	public User(String id, String name) {
		this.id = id;
		this.name = name;
	}
	


	public User(String id, String name, String password, String birth, String gender, String email, String phone,
			String address) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.birth = birth;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	//메서드
	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public String getBirth() {
		return birth;
	}

	public String getGender() {
		return gender;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}


	
}
