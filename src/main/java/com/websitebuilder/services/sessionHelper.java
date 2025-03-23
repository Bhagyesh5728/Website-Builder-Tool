package com.websitebuilder.services;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Component
public class sessionHelper {

	public void removeMessageFromMainPage()
	{
		 
		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         HttpSession session = request.getSession();
         session.removeAttribute("message");
	}
	public void removeMessageFromLoginFrom()
	{

		 HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
         HttpSession session = request.getSession();
         session.removeAttribute("notmatch");
         session.removeAttribute("match");
         session.removeAttribute("exist");
         session.removeAttribute("not");
         session.removeAttribute("login");
         session.removeAttribute("message");
         session.removeAttribute("logout");
         session.removeAttribute("wrongotp");
         session.removeAttribute("messageTable");
         session.removeAttribute("contact");
         session.removeAttribute("userNotExists");
         session.removeAttribute("sendMail");
         session.removeAttribute("reset");
	}
	public void removeMessageFromGallery()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("imageExist");
		session.removeAttribute("clientImageExist");
		session.removeAttribute("teamImageExist");
		session.removeAttribute("eventExist");
		session.removeAttribute("menuExist");
	}
	
	public void removeMessageFromShowTheme()
	{
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("themedelete");
	}
}
