package com.websitebuilder.entity;


import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;




@Entity
public class User {

	
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private String password;
	@OneToMany(mappedBy = "login",cascade = CascadeType.ALL)
	private List<userMenu> userMenu;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<menuGallery> gallery;

	@OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
	private userTheme theme;
	
	@OneToOne(mappedBy = "user")
	private menuAbout about;
	
	@OneToOne(mappedBy = "user")
	private menuContact contact;
	
	@OneToOne(mappedBy = "user")
	private menuHome home;
	
	@OneToOne(mappedBy = "user")
	private menuServices services;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<menuClients> menuClients;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<menuTeam> menuTeams;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<customPage> customPages;
	
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<backGroundImages> backGroundImages;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<restoMenu> restoMenus;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<specialDishes> specialDishes;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<events> events;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<menuTable> menuTables;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL) 
	private List<contect> contects;
	
	public List<contect> getContects() {
		return contects;
	}
	public void setContects(List<contect> contects) {
		this.contects = contects;
	}
	public List<menuTable> getMenuTables() {
		return menuTables;
	}
	public void setMenuTables(List<menuTable> menuTables) {
		this.menuTables = menuTables;
	}
	public List<events> getEvents() {
		return events;
	}
	public void setEvents(List<events> events) {
		this.events = events;
	}
	public List<specialDishes> getSpecialDishes() {
		return specialDishes;
	}
	public void setSpecialDishes(List<specialDishes> specialDishes) {
		this.specialDishes = specialDishes;
	}
	public List<restoMenu> getRestoMenus() {
		return restoMenus;
	}
	public void setRestoMenus(List<restoMenu> restoMenus) {
		this.restoMenus = restoMenus;
	}
	public List<backGroundImages> getBackGroundImages() {
		return backGroundImages;
	}
	public void setBackGroundImages(List<backGroundImages> backGroundImages) {
		this.backGroundImages = backGroundImages;
	}
	public List<customPage> getCustomPages() {
		return customPages;
	}
	public void setCustomPages(List<customPage> customPages) {
		this.customPages = customPages;
	}
	public List<menuTeam> getMenuTeams() {
		return menuTeams;
	}
	public void setMenuTeams(List<menuTeam> menuTeams) {
		this.menuTeams = menuTeams;
	}
	public List<menuClients> getMenuClients() {
		return menuClients;
	}
	public void setMenuClients(List<menuClients> menuClients) {
		this.menuClients = menuClients;
	}
	public menuAbout getAbout() {
		return about;
	}
	public void setAbout(menuAbout about) {
		this.about = about;
	}
	public menuContact getContact() {
		return contact;
	}
	public void setContact(menuContact contact) {
		this.contact = contact;
	}
	public menuHome getHome() {
		return home;
	}
	public void setHome(menuHome home) {
		this.home = home;
	}
	public menuServices getServices() {
		return services;
	}
	public void setServices(menuServices services) {
		this.services = services;
	}
	public userTheme getTheme() {
		return theme;
	}
	public void setTheme(userTheme theme) {
		this.theme = theme;
	}	
	public List<menuGallery> getGallery() {
		return gallery;
	}
	public void setGallery(List<menuGallery> gallery) {
		this.gallery = gallery;
	}
	public void setUserMenu(List<userMenu> userMenu) {
		this.userMenu = userMenu;
	}
	public List<userMenu> getUserMenu() {
		return userMenu;
	}
	public void setUsersiteinfo(List<userMenu> usersiteinfo) {
		this.userMenu = usersiteinfo;
	}
	public User(int id, String email, String password, String username) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	private String username;
	public int getId() {     
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "userLogin [id=" + id + ", email=" + email + ", password=" + password + ", username=" + username + "]";
	}
	
}
