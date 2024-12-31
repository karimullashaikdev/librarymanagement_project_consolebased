package application;

import java.io.Serializable;

public class Member implements Serializable{
	
	private String name;
	private String memberId;
	@Override
	public String toString() {
		return "Member [name=" + name + ", memberId=" + memberId + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Member(String name, String memberId) {
		super();
		this.name = name;
		this.memberId = memberId;
	}
	public Member() {
		// TODO Auto-generated constructor stub
	}
}
