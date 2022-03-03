package member.model;

import java.util.Date;

//p591
//(DB연동시) 회원정보를 저장, 제공등의 기능을 제공하는 클래스
//여기에서는 DB연동시 회원테이블에서 가져온 정보저장,제공등의 기능을 제공하는 클래스
public class MemberDTO {
		//필드
		//private 속성 타입 변수명[=초기값]
		private  String id;
		private  String password;
		private  String name;
		private  String gender;
		private  String email;
		private  String phone;
		private String address;
		private String birth;
		private Date regDate;
		private String u_delete;
		private Date delDate;
		private String grade;

		//생성자	-접근제한자 속성 클래스명(매개변수){}
		//기본생성자는 매개변수가 있는 생성자가 존재하면 수동으로 작성해야합니다
		public MemberDTO(){}



		public MemberDTO(String id, String password,String name, String gender, String email, String phone,String address,String birth,Date regDate, String u_delete, Date delDate, String grade) {
			//super();	//부모생성자 호출
			this.id = id;
			this.password = password;
			this.name = name;
			this.gender = gender;
			this.email = email;
			this.phone = phone;
			this.address = address;
			this.birth = birth;
			this.regDate = regDate;
			this.u_delete = u_delete;
			this.delDate = delDate;
			this.grade = grade;
		}

		//메서드
		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getBirth() {
			return birth;
		}

		public void setBirth(String birth) {
			this.birth = birth;
		}

		public Date getRegDate() {
			return regDate;
		}

		public void setRegDate(Date regDate) {
			this.regDate = regDate;
		}

		public String getU_delete() {
			return u_delete;
		}

		public void setU_delete(String u_delete) {
			this.u_delete = u_delete;
		}

		public Date getDelDate() {
			return delDate;
		}

		public void setDelDate(Date delDate) {
			this.delDate = delDate;
		}

		public String getGrade() {
			return grade;
		}

		public void setGrade(String grade) {
			this.grade = grade;
		}
		//p592 35라인
		//비밀번호 일치여부 체크
		public boolean matchPassword(String password) {
			//여기에서는 password필드에 할당되어있는 값과 매개변수로 넘어온 값이 일치하면 true로 리턴
			return this. password.equals(password);
		}
		public void changePassword(String newPwd) {
			//바꾼 비밀번호가 비밀번호로 저장
			this.password=newPwd;
		}


		@Override
		public String toString() {
			return "MemberDTO [id=" + id + ", password=" + password + ", name=" + name + ", gender=" + gender
					+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", birth=" + birth
					+ ", regDate=" + regDate + ", u_delete=" + u_delete + ", delDate=" + delDate + ", grade=" + grade
					+ "]";
		}
		
		
}
