package com.websitebuilder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class menuAbout {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String aboutBusiness;
	private String email;
	private Long mobile;
	
	
	@OneToOne
	private userMenu menu;
	
	@OneToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAboutBusiness() {
		return aboutBusiness;
	}

	public void setAboutBusiness(String aboutBusiness) {
		this.aboutBusiness = aboutBusiness;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public userMenu getMenu() {
		return menu;
	}

	public void setMenu(userMenu menu) {
		this.menu = menu;
	}

	public menuAbout(int id, String aboutBusiness, String email, Long mobile, userMenu menu) {
		super();
		this.id = id;
		this.aboutBusiness = aboutBusiness;
		this.email = email;
		this.mobile = mobile;
		this.menu = menu;
	}

	public menuAbout() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "menuAbout [id=" + id + ", aboutBusiness=" + aboutBusiness + ", email=" + email + ", mobile=" + mobile
				+ ", menu=" + menu + ", getId()=" + getId() + ", getAboutBusiness()=" + getAboutBusiness()
				+ ", getEmail()=" + getEmail() + ", getMobile()=" + getMobile() + ", getMenu()=" + getMenu()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
}


