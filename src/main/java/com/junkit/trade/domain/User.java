package com.junkit.trade.domain;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import javax.persistence.*;
@Entity
@Table(name="User")
public class User implements UserDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private Long userId;
	
	@Column(name="f_name")
	private String firstName;
	
	@Column(name="l_name")
	private String lastName;
	
	@Column(name="password")
	private String password;
	
	@Column(name="email")
	private String email;

	@Column(name = "username")
	private String username;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities = new HashSet<>();


	public User(){

	}

	public User(User user) {
		this.authorities = user.getAuthorities();
		this.userId = user.getUserId();
		this.firstName = upperCaseFirstLetter(user.getFirstName());
		this.lastName = upperCaseFirstLetter(user.getLastName());
		this.password = user.getPassword();
		this.username = user.getUsername();
		this.email = user.getEmail();
	}

	public static String upperCaseFirstLetter(String name) {
		char[] charArray = name.toCharArray();
		String newName = "";
		newName += charArray[0];
		newName = newName.toUpperCase();
		for(int i = 1; i < charArray.length; i++) {
			newName += charArray[i];
		}
		return newName;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Set<Authorities>  getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<Authorities> authorities) {
		this.authorities = authorities;
	}
}
