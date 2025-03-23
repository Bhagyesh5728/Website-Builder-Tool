package com.websitebuilder.entity;




import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
import jakarta.persistence.Table;
@Entity
@Table(name = "userSiteMenu")
//@NamedStoredProcedureQuery(name = "user.addmenu", procedureName = "addData_menuHome_userSiteMenu",
//parameters = {
//		@StoredProcedureParameter(mode = ParameterMode.IN, name = "pageName",type = String.class),
//		@StoredProcedureParameter(mode = ParameterMode.IN, name = "heading_line",type = String.class),
//		@StoredProcedureParameter(mode = ParameterMode.IN, name = "link",type = String.class),
//		@StoredProcedureParameter(mode = ParameterMode.IN, name = "logoName",type = String.class),
//		@StoredProcedureParameter(mode = ParameterMode.IN, name = "siteTitle",type = String.class),
//		@StoredProcedureParameter(mode = ParameterMode.IN, name = "subHeading",type = String.class)
//}
//		)
public class userMenu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String pageName;
	
	@ManyToOne()
	private User login;
 
	@OneToOne(mappedBy = "userMenu",cascade = CascadeType.ALL)
	private menuHome menuHome;
	
	@OneToOne(mappedBy = "menu",cascade = CascadeType.ALL)
	private menuAbout about;
	
	@OneToOne(mappedBy = "userMenu", cascade = CascadeType.ALL)
	private menuServices menuServices;

	@OneToOne(mappedBy = "userMenu", cascade = CascadeType.ALL)
	private menuContact menuContact;


	

	public userMenu(int id, String pageName, User login, com.websitebuilder.entity.menuHome menuHome, menuAbout about,
			com.websitebuilder.entity.menuServices menuServices, com.websitebuilder.entity.menuContact menuContact) {
		super();
		this.id = id;
		this.pageName = pageName;
		this.login = login;
		this.menuHome = menuHome;
		this.about = about;
		this.menuServices = menuServices;
		this.menuContact = menuContact;
		
	}












	public menuContact getMenuContact() {
		return menuContact;
	}



	public void setMenuContact(menuContact menuContact) {
		this.menuContact = menuContact;
	}



	public menuServices getMenuServices() {
		return menuServices;
	}


	public void setMenuServices(menuServices menuServices) {
		this.menuServices = menuServices;
	}


	public menuAbout getAbout() {
		return about;
	}

	public void setAbout(menuAbout about) {
		this.about = about;
	}

	public menuHome getMenuHome() {
		return menuHome;
	}

	public void setMenuHome(menuHome menuHome) {
		this.menuHome = menuHome;
	}

	public userMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public User getLogin() {
		return login;
	}

	public void setLogin(User login) {
		this.login = login;
	}

	
	


	
	
	
}
 