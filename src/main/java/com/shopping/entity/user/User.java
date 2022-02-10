package com.shopping.entity.user;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "user")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private String name;
	
	private String surname;
	
	private String email;
	
	private Double wallet;
	
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	private String password;
	
	private Date createdDate;
	
	private Boolean accountExpired = false;
	
	private Boolean locked = false;
	
	private Boolean enabled = true;
	
	private Boolean credentialsExpired = false;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		 SimpleGrantedAuthority authority =
	                new SimpleGrantedAuthority(userRole.name());
	        return Collections.singletonList(authority);
	}
	
	@Override
	public String getPassword() {
		return this.password;
	}
	
	public Double getWallet() {
		return wallet;
	}
	
	public void setWallet(Double wallet) {
		this.wallet = wallet;
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

	
	public UserRole getUserRole() {
		return userRole;
	}


	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
