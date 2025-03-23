package com.websitebuilder.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;


@Entity
public class menuHome {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY )
	private int id;
	private String headingLine;
	private String subHeadingLine;
	private String siteTitle;
	private String logo; 
	private String link;

	 
	@OneToOne
	@JoinColumn(name = "menuId")
	private userMenu userMenu;
	
	@OneToOne
	private User user;

	public User getUser() {
		return user;
	}
 
	public void setUser(User user) {
		this.user = user;
	}




	public String getSiteTitle() {
		return siteTitle;
	}


	public void setSiteTitle(String siteTitle) {
		this.siteTitle = siteTitle;
	}
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getHeadingLine() {
		return headingLine;
	}


	public void setHeadingLine(String headingLine) {
		this.headingLine = headingLine;
	}


	public String getSubHeadingLine() {
		return subHeadingLine;
	}


	public void setSubHeadingLine(String subHeadingLine) {
		this.subHeadingLine = subHeadingLine;
	}


	public String getTitle() {
		return siteTitle;
	}


	public void setTitle(String title) {
		this.siteTitle = title;
	}


	public String getLogo() {
		return logo;
	}


	public void setLogo(String logo) {
		this.logo = logo;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public userMenu getUsermenu() {
		return userMenu;
	}


	public void setUsermenu(userMenu usersiteinfo) {
		this.userMenu = usersiteinfo;
	}


	public menuHome(int id, String headingLine, String subHeadingLine, String title, String logo, String link,
			com.websitebuilder.entity.userMenu usersiteinfo) {
		super();
		this.id = id;
		this.headingLine = headingLine;
		this.subHeadingLine = subHeadingLine;
		this.siteTitle = title;
		this.logo = logo;
		this.link = link;
		this.userMenu= usersiteinfo;
	}


	public menuHome() {
		super();
		// TODO Auto-generated constructor stub
	}


	@Override
	public String toString() {
		return "menuHome [id=" + id + ", headingLine=" + headingLine + ", subHeadingLine=" + subHeadingLine + ", title="
				+ siteTitle + ", logo=" + logo + ", link=" + link + ", usersiteinfo=" + userMenu + "]";
	}
	
	
	
	
	
	
}
