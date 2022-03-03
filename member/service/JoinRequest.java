package member.service;

import java.util.Date;
import java.util.Map;

//회원가입 관련 회원정보를 저장,제공 등의 기능을 제공하는 클래스
public class JoinRequest {
	//필드
	String id; //회원id
	String password; //회원비번
	String password2; //회원비번확인
	String name; //회원명
	String birth;	//생년월일
	String gender;	//성별
	String email;	//이메일
	String email1;	//이메일1
	String email2; //이메일2
	String phone; //휴대폰번호
	String phone1; //휴대폰번호1
	String phone2;	//휴대폰번호2
	String phone3;	//휴대폰번호3
	String address;	//주소
	String postcode; //우편번호
	String roadAddress;	//도로명주소
	String detailAddress;	//상세주소
	
	//생성자

	//메서드
	public String getEmail1() {
		return email1;
	}
	public void setEmail1(String email1) {
		this.email1 = email1;
	}
	public String getPhone1() {
		return phone1;
	}
	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}
	public String getEmail2() {
		return email2;
	}
	public void setEmail2(String email2) {
		this.email2 = email2;
	}
	public String getPhone2() {
		return phone2;
	}
	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}
	public String getPhone3() {
		return phone3;
	}
	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public String getRoadAddress() {
		return roadAddress;
	}
	public void setRoadAddress(String roadAddress) {
		this.roadAddress = roadAddress;
	}
	public String getDetailAddress() {
		return detailAddress;
	}
	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	
	//p594 44라인
	//필드 password와 필드confirmPassword에 저장된 값일치 여부 체크
	public boolean isPasswordEqualToConfirmPassword() {
		return password != null && password.equals(password2);
	}
	//p595 48라인
	//회원가입 폼의 각 필드의 데이터가 유효한지 검사 --->완성해야함!!!
	public void validate(Map<String,Boolean> errors)  {
		checkEmpty( errors,id ,"id");//id필수입력체크
		checkEmpty( errors,name ,"name");//name필수입력체크
		checkEmpty( errors,password ,"password");//비번필수입력체크
		checkEmpty( errors,password2,"password2");//비번확인필수입력체크
		checkEmpty( errors,birth ,"birth");
//		checkEmpty( errors,gender ,"gender");
		checkEmpty( errors,email1,"email1");
		checkEmpty( errors,phone ,"phone");
		checkEmpty( errors,address,"address");
		if(!errors.containsKey("password2")) {
			if(!isPasswordEqualToConfirmPassword()){
				errors.put("notMatch",Boolean.TRUE);
			}
		}
	}
	//p595 60라인
	private void checkEmpty(Map<String,Boolean> errors,
			String value,String fieldName){
		if(value==null || value.isEmpty()) {
			errors.put(fieldName,Boolean.TRUE);
		}
	}

}//class
