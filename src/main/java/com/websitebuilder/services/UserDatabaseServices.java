package com.websitebuilder.services;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.websitebuilder.DAO.ContactRepo;
import com.websitebuilder.DAO.addTableRepo;
import com.websitebuilder.DAO.allThemesRepo;
import com.websitebuilder.DAO.backimageRepo;
import com.websitebuilder.DAO.clientsImageRepo;
import com.websitebuilder.DAO.custompageRepo;
import com.websitebuilder.DAO.eventRepo;
import com.websitebuilder.DAO.fetchImages;
import com.websitebuilder.DAO.getUserInfoRepo;
import com.websitebuilder.DAO.restoMenuRepo;
import com.websitebuilder.DAO.specialDeshRepo;
import com.websitebuilder.DAO.teamRepo;
import com.websitebuilder.DAO.userMenuRepo;
import com.websitebuilder.entity.contect;
import com.websitebuilder.entity.customPage;
import com.websitebuilder.entity.events;
import com.websitebuilder.entity.menuClients;
import com.websitebuilder.entity.menuGallery;
import com.websitebuilder.entity.menuTable;
import com.websitebuilder.entity.menuTeam;
import com.websitebuilder.entity.restoMenu;
import com.websitebuilder.entity.specialDishes;
import com.websitebuilder.entity.userMenu;
import com.websitebuilder.entity.User;
import com.websitebuilder.entity.allThemes;
import com.websitebuilder.entity.backGroundImages;

@Service
public class UserDatabaseServices {

	@Autowired
	getUserInfoRepo getUserInfoRepo;//user database operations
	
	@Autowired
	ContactRepo contactRepo; //this class use for database operation for Contatc form
	
	@Autowired
	userMenuRepo userMenuRepo;//handle procedure and other operations
	
	@Autowired
	emailService emailService;
	
	@Autowired
	fetchImages fetchImages;
	
	@Autowired
	allThemesRepo AllThemesRepo;
	
	@Autowired
	clientsImageRepo clientsImageRepo;
	
	@Autowired
	teamRepo teameRepo;
	
	@Autowired
	custompageRepo customRepo;
	
	@Autowired
	eventRepo eventRepo;
	
	@Autowired
	backimageRepo backimageRepo;
	
	@Autowired
	restoMenuRepo restoMenuRepo;
	
	@Autowired
	specialDeshRepo specialDeshRepo;
	
	@Autowired
	addTableRepo tableRepo;
	
	public User findUserByEmail(String email)
	{
		return getUserInfoRepo.findByEmail(email);
	}
	
	
	public void saveUser(User login)
	{
		getUserInfoRepo.save(login);
	}
	
	public List<User> findEmailAndPassword(String email,String password)
	{
		return getUserInfoRepo.findByEmailAndPassword(email, password);
	}
	
	public void saveContact(contect contect)
	{
		contactRepo.save(contect);
	}
	
	
	
	
	public void saveUserMenu(userMenu usersiteinfo)
	{
		userMenuRepo.save(usersiteinfo);
	}
	
	public void deleteUserById(int id)
	{
		getUserInfoRepo.deleteById(id);
	}
	
	public void deleteMenuFromSite(int id)
	{
		userMenuRepo.deleteById(id);
		
	}
	
	public void sendEmail(String toEmail,String subject,String body)
	{
		emailService.sendEmail(toEmail, subject, body);
	}

	public void insertImages(List<menuGallery> gallery)
	{
		fetchImages.saveAll(gallery);
	}
	
	public void insertClient(List<menuClients> clients)
	{
		clientsImageRepo.saveAll(clients);
	}
	
	
	public List<userMenu> findPage(User user)
	{
	return userMenuRepo.findByLogin(user);
	}
	
	public void deleteImage(String img)
	{
		fetchImages.deleteByImg(img);;
	}
	
	public void deleteAllImages(List<Integer> id)
	{
		fetchImages.deleteAllById(id);;
	}
	

	
	
	public List<allThemes> FetchAllThems()
	{
		List<allThemes> f= (List<allThemes>) AllThemesRepo.findAll();
		return f;
	}
	
	public  allThemes findThemeByName(String name)
	{
		return AllThemesRepo.findByName(name);
	}
	
	public void deleteClientImage(String img)
	{
		clientsImageRepo.deleteByClientImage(img);;
	}
	
	public void saveTeam(menuTeam menuTeam)
	{
		teameRepo.save(menuTeam);
	}
	
	public void deleteTeamMember(String id)
	{
		teameRepo.deleteByMemberImage(id);
	}
	
	public void saveCustomPage(customPage customPage)
	{
		customRepo.save(customPage);
	}
	
	public void removeCustomMenu(int id)
	{
		customRepo.deleteById(id);
	}
	public void addBackImage(backGroundImages  backGroundImages)
	{
		backimageRepo.save(backGroundImages);
	}
	
	public void saveRestoMenu(restoMenu menu)
	{
		restoMenuRepo.save(menu);
	}
	public void saveSpecialDish(specialDishes specialDishes)
	{
		specialDeshRepo.save(specialDishes);
	}
	public void saveEvent(events events)
	{
		eventRepo.save(events);
	}
	public void deleteBackImage(int id)
	{
		backimageRepo.deleteById(id);;
	}
	public List<backGroundImages> findBackImage(int id)
	{
		return backimageRepo.findById(id);
	}
	
	public void deleteSpecial(String name)
	{
		specialDeshRepo.deleteByImage(name);
	}
	
	public void deleteRestoMenu(int id)
	{
		restoMenuRepo.deleteById(id);
	}
	public void deleteEvent(String image)
	{
		eventRepo.deleteByImage(image);
	}
	public void addTableInfo(menuTable menuTable)
	{
		tableRepo.save(menuTable);
	}
	public void deleteBooking(int id)
	{
		tableRepo.deleteById(id);
	}
	public void deletContact(int id)
	{
		contactRepo.deleteById(id);
	}
	
}
