package com.websitebuilder.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class menuContact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 2000)
	private String mapLink;
	private String address;
	private String email;
	private Long mobile;
	
	@OneToOne
	private userMenu userMenu;
	
	@OneToOne
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public menuContact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public menuContact(int id, String mapLink, String address, String email, Long mobile,
			com.websitebuilder.entity.userMenu userMenu) {
		super();
		this.id = id;
		this.mapLink = mapLink;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.userMenu = userMenu;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMapLink() {
		return mapLink;
	}

	public void setMapLink(String mapLink) {
		this.mapLink = mapLink;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public userMenu getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(userMenu userMenu) {
		this.userMenu = userMenu;
	}

	@Override
	public String toString() {
		return "menuContact [id=" + id + ", mapLink=" + mapLink + ", address=" + address + ", email=" + email
				+ ", mobile=" + mobile + ", userMenu=" + userMenu + "]";
	}
	
	
}
