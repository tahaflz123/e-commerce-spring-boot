package com.shopping.entity;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Setter;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	@Id
	@SequenceGenerator(name = "user-sequence",allocationSize = 1)
	@GeneratedValue(generator = "user-sequence",strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private String password;
	
	private Boolean accountExpired;
	
	private Boolean locked = false;
	
	private Boolean enabled = true;
	
	private Boolean credentialsExpired;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	public void setUsername(String email) {
		this.email = email;
	}
	
	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.accountExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.locked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.credentialsExpired;
	}

	@Override
	public boolean isEnabled() {
		return this.enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Long getId() {
		return id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountExpired(Boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	public void setLocked(Boolean locked) {
		this.locked = locked;
	}

	public void setCredentialsExpired(Boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	
	
	
	
	
}
