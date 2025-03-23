package com.websitebuilder.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToOne;

@Entity
public class menuServices {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String serviceDescription;
	private String service1;
	private String service2;
	private String service3;
	private String service4;
	
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

	public menuServices() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public menuServices(int id, String serviceDescription, String service1, String service2, String service3,
			String service4, com.websitebuilder.entity.userMenu userMenu) {
		super();
		this.id = id;
		this.serviceDescription = serviceDescription;
		this.service1 = service1;
		this.service2 = service2;
		this.service3 = service3;
		this.service4 = service4;
		this.userMenu = userMenu;
	}


	public String getServiceDescription() {
		return serviceDescription;
	}


	public void setServiceDescription(String serviceDescription) {
		this.serviceDescription = serviceDescription;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getService1() {
		return service1;
	}

	public void setService1(String service1) {
		this.service1 = service1;
	}

	public String getService2() {
		return service2;
	}

	public void setService2(String service2) {
		this.service2 = service2;
	}

	public String getService3() {
		return service3;
	}

	public void setService3(String service3) {
		this.service3 = service3;
	}

	public String getService4() {
		return service4;
	}

	public void setService4(String service4) {
		this.service4 = service4;
	}

	public userMenu getUserMenu() {
		return userMenu;
	}

	public void setUserMenu(userMenu userMenu) {
		this.userMenu = userMenu;
	}

	@Override
	public String toString() {
		return "menuServices [id=" + id + ", service1=" + service1 + ", service2=" + service2 + ", service3=" + service3
				+ ", service4=" + service4 + ", userMenu=" + userMenu + "]";
	}
	
	
	
}
