package jblog.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class UserVo {
	@NotEmpty(message="아이디는 필수 입력 항목")
	@Size(min=2, max=8, message = "아이디는 2~8자")
	private String id;

	@NotEmpty(message="이름은 필수 입력 항목")
	private String name;

	@NotEmpty(message="비밀번호는 필수 입력 항목")
	@Size(min=2, max=8, message = "패스워드는 2~8자")
	private String password;
	
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

	@Override
	public String toString() {
		return "UserVo [id=" + id + ", name=" + name + ", password=" + password + "]";
	}
	
}
