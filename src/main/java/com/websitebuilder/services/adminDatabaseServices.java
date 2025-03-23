package com.websitebuilder.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.websitebuilder.DAO.adminLoginRepo;
import com.websitebuilder.DAO.allThemesRepo;
import com.websitebuilder.DAO.getUserInfoRepo;
import com.websitebuilder.entity.User;
import com.websitebuilder.entity.adminLogin;
import com.websitebuilder.entity.allThemes;


@Component
public class adminDatabaseServices {

	
	@Autowired
	adminLoginRepo adminLoginRepo;
	
	@Autowired
	allThemesRepo themesRepo;
	
	@Autowired
	getUserInfoRepo getUserInfoRepo;
	
	
	public adminLogin getAdmin(String username,String password)
	{
		adminLogin admin = adminLoginRepo.findByUsernameAndPassword(username,password);
		
		return admin;
	}
	
	public void InsertTheme(allThemes them)
	{
		themesRepo.save(them);	
	}
	public allThemes findTheme(String name)
	{
		return themesRepo.findByName(name);
	}

	
	public List<allThemes> findAll()
	{
		return (List<allThemes>) themesRepo.findAll();
	}
	public void deletetheme(String name)
	{
		themesRepo.deleteByName(name);
	}
	
	public List<User> getUsers()
	{
		return (List<User>) getUserInfoRepo.findAll();
	}
	public void deleteUser(int id)
	{
		getUserInfoRepo.deleteById(id);
	}
}
