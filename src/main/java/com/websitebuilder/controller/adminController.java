package com.websitebuilder.controller;



import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.websitebuilder.entity.User;
import com.websitebuilder.entity.allThemes;
import com.websitebuilder.services.UploadFile;
import com.websitebuilder.services.adminDatabaseServices;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;


@Controller
public class adminController {
	@Autowired
	private UploadFile logoUpload;
	@Autowired
	adminDatabaseServices adminDatabaseServices;
	
	@RequestMapping("/admin/")
	public String adminIndex()
	{
//		String name="dashboard";
		return "admin/loginAdmin";
	}
	
	@RequestMapping("/admin/dashboard")
	public String adminDashboard(HttpSession session)
	{
		String username=(String) session.getAttribute("adminFind");
		
		if(username==null)
		{
			return "redirect:/admin/";
		}
		return "admin/dashboard";
	}
	
//	Handle User Login Form
	@PostMapping("/adminLogin")
	public  String adminLogin(@RequestParam("username") String username,@RequestParam("password") String password,HttpSession session)
	{
	 
		boolean flag= adminDatabaseServices.getAdmin(username, password) !=null;
		
		
		if(flag)
		{
			session.setAttribute("adminFind",username);
			
			session.setAttribute("message","Successfully Login!!");
			return "redirect:/admin/dashboard";
		}
		
		else 
		{
			session.setAttribute("not","Invalid email and password");
			return "redirect:/admin/"; 
		}
	}
	
	@RequestMapping("/admin/adminLogout")
	public String adminLogout(HttpSession session)
	{
		session.removeAttribute("adminFind");
		
		return "redirect:/admin/";
	}
	
	@RequestMapping("/admin/addTheme")
	public String addTheme(HttpSession session)
	{
		String username=(String) session.getAttribute("adminFind");
		if(username==null)
		{
			return "redirect:/admin/";
		}
		return "/admin/addTheme";
	}
	
	@RequestMapping("/admin/users")
	public String usersList(HttpSession session,Model model)
	{
		List<User> user=adminDatabaseServices.getUsers();	
		model.addAttribute("user", user);
		
		return "/admin/users";
	}
	
	
	@PostMapping("/uploadTheme")
	public String uploadTheme()
	{
		return "redirect:/admin/";
				
	}
	@PostMapping("/uploadThemes")
	public String uploadThemeIndatabase(@RequestParam("name") MultipartFile name,@RequestParam("image") MultipartFile photo) throws IOException
	{	
		allThemes allThemes=new allThemes();
		
		String n=StringUtils.cleanPath(name.getOriginalFilename());
		String i=StringUtils.cleanPath(photo.getOriginalFilename());
		allThemes.setImage(i);
		allThemes.setName(n);
		logoUpload.uploadThemeSS(photo);
		logoUpload.uploadThemeFile(name);
		adminDatabaseServices.InsertTheme(allThemes);
		return "redirect:/admin/addTheme";
	}
	
	
	@RequestMapping("/admin/viewThemes")
	public String viewThemes(Model model)
	{
		List<allThemes> findAll = adminDatabaseServices.findAll();
		model.addAttribute("themes",findAll);
		return "/admin/showTheme";
	}
	
	
	@RequestMapping("/admin/deleteTheme/{id}")
	@Transactional
	public String deleteTheme(@PathVariable("id") String name,HttpSession session)
	{
	    String[] arrOfStr = name.split("\\.");
	    String ph=arrOfStr[0];
		adminDatabaseServices.deletetheme(name);
		File photo=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeSS\\"+ph+".png");
		photo.delete();
		File theme=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\templates\\theme\\"+name);
		theme.delete();
		session.setAttribute("themedelete", "delete");
		return "redirect:/admin/viewThemes";
	}
	@RequestMapping("/deleteUser/{id}")
	public String deleteUser(@PathVariable("id") int id)
	{
		adminDatabaseServices.deleteUser(id);
		
		return "redirect:/admin/users";
	}
}
