package io.devk.jpabasic;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
public class Member {
	
	@Id
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String username;
	
	private Integer age;
	
	public Member() {
		
	}

	public Member(Long id, String username, Integer age) {
		super();
		this.id = id;
		this.username = username;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}
	
	
	
	
}
