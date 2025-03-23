package com.websitebuilder.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.websitebuilder.entity.User;
import com.websitebuilder.entity.allThemes;
import com.websitebuilder.entity.backGroundImages;
import com.websitebuilder.entity.contect;
import com.websitebuilder.entity.customPage;
import com.websitebuilder.entity.email;
import com.websitebuilder.entity.events;
import com.websitebuilder.entity.menuAbout;
import com.websitebuilder.entity.menuClients;
import com.websitebuilder.entity.menuContact;
import com.websitebuilder.entity.menuGallery;
import com.websitebuilder.entity.menuHome;
import com.websitebuilder.entity.menuServices;
import com.websitebuilder.entity.menuTable;
import com.websitebuilder.entity.menuTeam;
import com.websitebuilder.entity.restoMenu;
import com.websitebuilder.entity.specialDishes;
import com.websitebuilder.entity.userMenu;
import com.websitebuilder.entity.userTheme;
import com.websitebuilder.services.UserDatabaseServices;

import com.websitebuilder.services.UploadFile;


import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

@Controller
public class SiteController {
	
	@Autowired
	private UserDatabaseServices userdatabaseServices;//this handle all database operations
	@Autowired
	private UploadFile logoUpload;
	
	
	
	
	
	

//	goto dashboard page
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session,Model model)
	{
		String email=(String) session.getAttribute("ok"); 
		if(email==null)
		{
			return "redirect:/login";
		}
		User user=userdatabaseServices.findUserByEmail(email); 
		String username=user.getUsername();
		
		session.setAttribute("username", username);
		return "dashboard";
	}
	
//	goto profle page
	@GetMapping("/profile")
	public String profile(HttpSession session,Model model)
	{
		
		String email=(String) session.getAttribute("ok"); 
		if(email==null)
		{
			return "redirect:/login";
		}
		 
		  String username=(String) session.getAttribute("username");
		  model.addAttribute("email",email);
		  model.addAttribute("username",username);
		 
		return "profile";
	}
	
//	goto to login page
	@RequestMapping("/login")
	public String login()
	{
		return "login";
	}
	
	@RequestMapping("/showBooking")
	public String tableList(Model model,HttpSession session )
	{String email=(String) session.getAttribute("ok"); 
	User user=userdatabaseServices.findUserByEmail(email);
		model.addAttribute("user",user);
		return "tableBooking";
	}
	
	@RequestMapping("/showContact")
	public String contactList(Model model,HttpSession session )
	{String email=(String) session.getAttribute("ok"); 
	User user=userdatabaseServices.findUserByEmail(email);
	
		model.addAttribute("user",user);
		return "contact";
	}
	
//	goto signup page
	@RequestMapping("/signup")
	public String signup()
	{
		return "signup";
	}
	
//	goto index page
	@RequestMapping("/")
	public String index(Model model)
	{
		model.addAttribute("contect", new contect());
		
		return "index";
	}
	
//	goto addpage
	@RequestMapping("/addPage")
	public String addPage(Model model,HttpSession session)
	{	
		session.removeAttribute("noMenuFound");
		String email2=(String) session.getAttribute("ok"); 
		
		if(email2==null)
		{
			return "redirect:/login";
		}
		
		String email=(String) session.getAttribute("ok"); 
		User user=userdatabaseServices.findUserByEmail(email);
	 
		String themename=user.getTheme().getName().toString();
		
		String[] arrOfStr = themename.split("\\.");
	    String th=arrOfStr[0];
	    
	    String theme=user.getTheme().getName();
	    System.out.print(theme);
	
		if(!user.getBackGroundImages().isEmpty())
		{
			session.setAttribute("backgroundImagesFound", "found");
		}
		if(!user.getSpecialDishes().isEmpty())
		{
			session.setAttribute("specialMenuMenuFound", "special");
		}
		
		session.setAttribute("theme", th);
		System.out.print(themename);
		if(user.getUserMenu().isEmpty())
		{
			 
		}
		else
		{
			
			for (userMenu use : user.getUserMenu()) {
				if(use.getPageName().equals("Home"))
				{
					session.setAttribute("homeMenuFound", "Home");
				}
				if(use.getPageName().equals("About"))
				{
					session.setAttribute("aboutMenuFound", "About");
				}
				if(use.getPageName().equals("Services"))
				{
					session.setAttribute("serviceMenuFound", "Service");
				}
				if(use.getPageName().equals("Contact"))
				{
					session.setAttribute("contactMenuFound", "Contact");
				}
				if(use.getPageName().equals("Gallery"))
				{
					session.setAttribute("galleryMenuFound", "Gallery");
				}
				if(use.getPageName().equals("Client"))
				{
					session.setAttribute("clientMenuFound", "Client");
				}
				if(use.getPageName().equals("Team"))
				{
					session.setAttribute("teamMenuFound", "Team");
				}
				if(use.getPageName().equals("Menu"))
				{
					session.setAttribute("ourMenuMenuFound", "OurMenu");
				}
				if(use.getPageName().equals("Event"))
				{
					session.setAttribute("eventMenuFound", "OurMenu");
				}
				if(use.getPageName().equals("BookTable"))
				{
					session.setAttribute("tableMenuFound", "OurMenu");
				}
				
				
			}
		
		}



	
		return "addPage";
		
	}
	
	
//	goto to basciinfo page
	@RequestMapping("/viewPages")
	public String viewPages(Model model,HttpSession session)
	{
		String email=(String) session.getAttribute("ok"); 
		if(email==null)
		{
			return "redirect:/login";
		}
		System.out.print(session.getAttribute("backgroundImagesFound"));
		
		
		User user = userdatabaseServices.findUserByEmail(email);
		if(!user.getBackGroundImages().isEmpty())
		{
			session.setAttribute("backgroundImagesFound", "found");
		}
		else
		{
			session.removeAttribute("backgroundImagesFound");
		}
		if(!user.getSpecialDishes().isEmpty())
		{
			session.setAttribute("specialMenuMenuFound", "special");
		}
		else
		{
			session.removeAttribute("specialMenuMenuFound");	
		}
		if(user.getCustomPages().isEmpty())
		{
			session.removeAttribute("custompage");
		}
		else
		{
			session.setAttribute("custompage", "custom");
		}
		if(user.getUserMenu().isEmpty() && user.getCustomPages().isEmpty())
		{
			return "noPageFound";
		}
		else
		{
			model.addAttribute("user", user);
		}
		return "viewPages";
	}
	
	
	
//	goto to basciinfo page
	@RequestMapping("/basciInfo" )
	public String basicInfo(Model model)
	{
		return "basciInfo";
	}
	
	
	
	
//	handle contact form and insert data in database 
	@PostMapping("/contect")
	public String contactFormHandler(@ModelAttribute contect con,HttpSession session)
	{
			
			this.userdatabaseServices.saveContact(con);
			session.setAttribute("message", "ok");
			return "redirect:/";
	}
	
//	goto previw page
	@RequestMapping("/preview")
	public String preview(HttpSession session,Model model)
	{
		
		String email=(String) session.getAttribute("ok"); 
		if(email==null)
		{
			return "redirect:/login";
		}

		
		User user=userdatabaseServices.findUserByEmail(email);	
		if(!user.getBackGroundImages().isEmpty())
		{
			session.setAttribute("backgroundImagesFound", "found");
		}
		if(!user.getSpecialDishes().isEmpty())
		{
			session.setAttribute("specialMenuMenuFound", "special");
		}
		
		if(user.getUserMenu().isEmpty() && user.getCustomPages().isEmpty())
		{
			session.setAttribute("noMenuFound", "ok");
			
		}

		
		
			
		model.addAttribute("user", user);
	
		String n=user.getTheme().getName().toString();
		
	    String[] arrOfStr = n.split("\\.");
	    String name=arrOfStr[0];
	   
		return "theme/"+name;
	}
	
//	handel user Signup Form
	@PostMapping("/signup")
	public String  signupHandler(@ModelAttribute User userlogin,HttpSession session,@RequestParam("re_password") String re_password,@RequestParam("password") String password ,@RequestParam("email") String email,Model model)
	{
//		boolean user=userdatabaseServices.findUserByEmail(email) != null;
		boolean user= userdatabaseServices.findUserByEmail(email) != null;
		if(user)
		{
			session.setAttribute("exist","This email id already registered please login!!");
			return "redirect:/login";
		}
		else
		{
			if(password.equals(re_password))
			{	
				User use=new User();
				use.setEmail(userlogin.getEmail());
				use.setPassword(userlogin.getPassword());
				use.setUsername(userlogin.getUsername());
				
				session.setAttribute("vuser", use);
				session.setAttribute("match","Please Check Your Email And Verify Email");
				int otp= (int)(Math.random()*1000000);
				session.setAttribute("votp", otp);
				String ot="Click Bellow Link For Verify \n\n http://localhost:8585/verifyEmail/"+otp+"";
				userdatabaseServices.sendEmail(email,ot , "Email Verification From BM Tools");
			}
			else 
			{
				session.setAttribute("notmatch","Password Not Match!!");
				return "redirect:/login";
			}
		}
		
		return "redirect:/login";
	}
	
	
	
//	Handle User Login Form
	@PostMapping("/login")
	public  String login(@RequestParam("email") String email,@RequestParam("password") String password,HttpSession session)
	{
		User use=userdatabaseServices.findUserByEmail(email);
		
		List<User> user=userdatabaseServices.findEmailAndPassword(email, password);
		int exist=user.size();
		if(exist>0)
		{	
			
			session.setAttribute("ok",email);
			
			session.setAttribute("message","Successfully Login!!");
			if(use.getTheme()!=null)
			{
			return "redirect:/dashboard";
			}
			else
			{
				return "redirect:/welcome";
			}
	
		}
		else 
		{
			session.setAttribute("not","Invalid email and password");
			return "redirect:/login"; 
		}
	}
	
	//handel basicINfo infromation form
	@PostMapping("/startingInfo")
	public String info(HttpSession session)
	{
		session.setAttribute("login", "Please Login or Signup to create your site");
		return "redirect:/login";
	}
	
	
	
	@PostMapping("/addHome")
	public String addHomeMenu(@RequestParam("headingLine") String headingline,@RequestParam("subHeadingLine") String subheadingline,@RequestParam("siteTitle") String title,@RequestParam("link") String link,
			@RequestParam("pageName") String pageName,HttpSession session,@RequestParam("logo") MultipartFile file) throws IOException
	{
		String email=(String) session.getAttribute("ok");  
		userMenu userMenu=new userMenu();
		User user= userdatabaseServices.findUserByEmail(email);
		
		menuHome menuHome=new menuHome();
		String logo=StringUtils.cleanPath(file.getOriginalFilename());
		
		menuHome.setLogo(logo);
	
		menuHome.setHeadingLine(headingline);
		menuHome.setSubHeadingLine(subheadingline);
		menuHome.setTitle(title);
		menuHome.setLink(link);
		
		
		
		
		userMenu.setPageName(pageName); 
		menuHome.setUser(user);
		menuHome.setLogo(logo);
		menuHome.setUsermenu(userMenu);
		userMenu.setMenuHome(menuHome);
		userMenu.setLogin(user);
		
	
		logoUpload.uploadGallery(file);
		logoUpload.uploadImages(file);
    	userdatabaseServices.saveUserMenu(userMenu);                                    
    	
		
//    	userdatabaseServices.saveMenuAndHomeMenuInfoPro(pageName,menuHome.getHeadingLine() ,menuHome.getLink(), menuHome.getLogo(), menuHome.getSiteTitle(), menuHome.getSubHeadingLine());
    	
		
		return "redirect:/addPage";
	}
	
	
	@PostMapping("/addBimage")
	public String addBackImage(HttpSession session,@RequestParam("heading") String heading ,@RequestParam("subHeading") String subheading , @RequestParam("pageName") String pageName,@RequestParam("image") MultipartFile file) throws IOException
	{
		
		String email=(String) session.getAttribute("ok");  
		User user= userdatabaseServices.findUserByEmail(email);
		backGroundImages bi=new  backGroundImages();
		String image=StringUtils.cleanPath(file.getOriginalFilename());
		
		bi.setImage(image);
		bi.setHeading(heading);
		bi.setSubHeading(subheading);
		bi.setUser(user);
		
		logoUpload.uploadGallery(file);
		logoUpload.uploadImages(file);
		
		userdatabaseServices.addBackImage(bi);
		session.setAttribute("backgroundImagesFound", "image");
		return "redirect:/addPage";
	}
	
	@PostMapping("/addOurmenu")
	public String addMenuResto(HttpSession session,@RequestParam("pageName") String pageName,@RequestParam("menuName") String menuName,@RequestParam("rate") int rate)
	{
		String email=(String) session.getAttribute("ok");  
		User user= userdatabaseServices.findUserByEmail(email);
		userMenu menu=new userMenu();
		
		
		restoMenu restoMenu=new restoMenu();
		restoMenu.setDishesName(menuName);
		restoMenu.setRate(rate);
		restoMenu.setUser(user);
		
		
		
		boolean flag=true;
		List<userMenu> pages=userdatabaseServices.findPage(user);
		
		for (userMenu userMenu2 : pages) {
			if(userMenu2.getPageName().toString().equals("Menu"))
			{
				flag=false;
			}
		
		}
		
		if(flag)
		{
		menu.setPageName(pageName);
		menu.setLogin(user);
		userdatabaseServices.saveUserMenu(menu);
		}
		
		
		
		userdatabaseServices.saveRestoMenu(restoMenu);
		
		return "redirect:addPage";
	}
	
	@PostMapping("/addSmenu")
	public String addSpecialMenu(HttpSession session,@RequestParam("menuName") String menuName,@RequestParam("desci") String desc,@RequestParam("image") MultipartFile file) throws IOException
	{
		String email=(String) session.getAttribute("ok");  
		User user= userdatabaseServices.findUserByEmail(email);
		String image=StringUtils.cleanPath(file.getOriginalFilename());
		
		specialDishes dishes=new specialDishes();
		
		dishes.setDescription(desc);
		dishes.setDisheName(menuName);
		dishes.setImage(image);
		dishes.setUser(user);
		logoUpload.uploadGallery(file);
		logoUpload.uploadImages(file);
		
		userdatabaseServices.saveSpecialDish(dishes);
		session.setAttribute("specialMenuMenuFound", "sd");
		
		return "redirect:/addPage";
	}
	
	
	@PostMapping("/addEvent")
	public String addevent(HttpSession session,@RequestParam("pageName") String pageName,@RequestParam("eventTitle") String eventTitle,@RequestParam("desci") String desci,@RequestParam("rate") int rate, @RequestParam("image") MultipartFile file) throws IOException
	{
		String email=(String) session.getAttribute("ok");  
		User user= userdatabaseServices.findUserByEmail(email);
		userMenu menu=new userMenu();
		String image=StringUtils.cleanPath(file.getOriginalFilename());
		events  events=new events();
		events.setEventDescription(desci);
		events.setEventTitle(eventTitle);
		events.setImage(image);
		events.setRate(rate);
		events.setUser(user);
		
		
		
		logoUpload.uploadGallery(file);
		logoUpload.uploadImages(file);
		
		
		boolean flag=true;
		List<userMenu> pages=userdatabaseServices.findPage(user);
		
		for (userMenu userMenu2 : pages) {
			if(userMenu2.getPageName().toString().equals("Event"))
			{
				flag=false;
			}
		
		}
		
		if(flag)
		{
		menu.setPageName(pageName);
		menu.setLogin(user);
		userdatabaseServices.saveUserMenu(menu);
		}
		
		
		
		userdatabaseServices.saveEvent(events);
		
		
		return "redirect:/addPage";
	}
	@PostMapping("/addClient")
	public String addClient(@RequestParam("clientimg") MultipartFile[] img,HttpSession session,@RequestParam("pageName") String pageName) throws IOException
	{
		String email=(String) session.getAttribute("ok");
		userMenu userMenu=new userMenu();
		
		User user= userdatabaseServices.findUserByEmail(email);
		
		List<menuClients> client=new ArrayList<>();
		for (MultipartFile multipartFile : img) {
			menuClients clients=new menuClients();
			clients.setClientImage(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
			clients.setUser(user);
			
			client.add(clients);
			logoUpload.uploadGallery(multipartFile);
			logoUpload.uploadImages(multipartFile);
			
		}
		
		
//		userMenu.setPageName(pageName);

		boolean flag=true;
		List<userMenu> pages=userdatabaseServices.findPage(user);
		
		for (userMenu userMenu2 : pages) {
			if(userMenu2.getPageName().toString().equals("Client"))
			{
				flag=false;
			}
		
		}
		
		
		
		if(flag)
		{
		userMenu.setPageName(pageName);
		userMenu.setLogin(user);
		userdatabaseServices.saveUserMenu(userMenu);
		}
		
		
		
		
		
		userdatabaseServices.insertClient(client);
		return "redirect:/addPage";
	}
	
	
	@PostMapping("/addContacts")
	public String addContacts(@ModelAttribute("contect") contect contect,HttpSession session)
	{
	
		String email=(String) session.getAttribute("ok");
	
		
		User user= userdatabaseServices.findUserByEmail(email);
		contect.setUser(user);
		session.setAttribute("contact", "c");
		userdatabaseServices.saveContact(contect);
		return "redirect:/preview";
	}
	
	@PostMapping("/addImage")
	public String addImages(@RequestParam("img") MultipartFile[] image,@RequestParam("pageName") String pageName,HttpSession session)throws IOException
	{
		String email=(String) session.getAttribute("ok");
		userMenu userMenu=new userMenu();
		User user= userdatabaseServices.findUserByEmail(email);
		
		List<menuGallery> g = new ArrayList<>();
		String img="";
	for (MultipartFile multipartFile : image) {
		menuGallery gallery=new menuGallery();
		gallery.setImg(StringUtils.cleanPath(multipartFile.getOriginalFilename()));
		gallery.setUser(user);
		g.add(gallery);
		logoUpload.uploadImages(multipartFile);
		logoUpload.uploadGallery(multipartFile);
		
	}
	
		
		
		userMenu.setPageName(pageName);

		boolean flag=true;
		List<userMenu> pages=userdatabaseServices.findPage(user);
		
		for (userMenu userMenu2 : pages) {
			if(userMenu2.getPageName().toString().equals("Gallery"))
			{
				flag=false;
			}
		
		}
		
		if(flag)
		{
		userMenu.setPageName(pageName);
		userMenu.setLogin(user);
		userdatabaseServices.saveUserMenu(userMenu);
		}
		
		
		userdatabaseServices.insertImages(g);
		
		return "redirect:/addPage";
		
		
	}
	
	@PostMapping("/addTableMenu")
	public String addTableMenu(HttpSession session,@RequestParam("pageName") String pageName)
	{
		String email=(String) session.getAttribute("ok");
		User user= userdatabaseServices.findUserByEmail(email);
		
		userMenu menu=new userMenu();
		menu.setPageName(pageName);
		menu.setLogin(user);
		
		
		userdatabaseServices.saveUserMenu(menu);
		
		
		return "redirect:/addPage";
	}
	
	@PostMapping("/updateHomeMenu")
	public String updateHomeMenu(@RequestParam("headingLine") String headingline,@RequestParam("subHeadingLine") String subheadingline,@RequestParam("siteTitle") String title,@RequestParam("link") String link,HttpSession session,@RequestParam("logo") MultipartFile file) throws IOException
	{
		String logo = null;
		boolean flag=false;
		String email=(String) session.getAttribute("ok"); 
		
		User user= userdatabaseServices.findUserByEmail(email);
		if(file.isEmpty())
		{
			flag=false;
		}
		else {
			flag=true;
		}
		if(flag)
		{
			 logo=StringUtils.cleanPath(file.getOriginalFilename());
		}
		
		 
		for(userMenu u: user.getUserMenu())
		{
			if(u.getPageName().equals("Home"))
			{	
				if(flag)
				{
				u.getMenuHome().setLogo(logo);
				}
				u.getMenuHome().setUser(user);
				u.getMenuHome().setTitle(title);
				u.getMenuHome().setHeadingLine(headingline);
				u.getMenuHome().setSubHeadingLine(subheadingline);
				u.getMenuHome().setLink(link);
			}
		}
		 	
		if(flag)
		{
		logoUpload.uploadGallery(file);
		logoUpload.uploadImages(file);
		}
		 userdatabaseServices.saveUser(user);
    	
		
		return "redirect:/viewPages";
	}
	

	@PostMapping("/addCustomPage")
	public String addCustomePage(@ModelAttribute customPage customPage,HttpSession session)
	{
		String email=(String) session.getAttribute("ok"); 
		
		User user= userdatabaseServices.findUserByEmail(email);
		customPage.setUser(user);
		
		userdatabaseServices.saveCustomPage(customPage);
		return "redirect:/addPage";
	}
	
	@PostMapping("/updateCustomPage")
	public String updateCustomePage(@ModelAttribute customPage customPage,HttpSession session)
	{
		String email=(String) session.getAttribute("ok"); 
		
		User user= userdatabaseServices.findUserByEmail(email);
		customPage.setUser(user);
		
		for (customPage c : user.getCustomPages()) {
			
			c.setPageName(customPage.getPageName());
			c.setCode(customPage.getCode());
			userdatabaseServices.saveCustomPage(c);
		}
		
		
		
		return "redirect:/viewPages";
	}
	
	@PostMapping("/updateAboutMenu")
	public String updateAboutMenu(@ModelAttribute menuAbout menuAbout,HttpSession session)
	{

		
		String email=(String) session.getAttribute("ok"); 
		
		User user= userdatabaseServices.findUserByEmail(email);
		
		for(userMenu u: user.getUserMenu())
		{
			if(u.getPageName().equals("About"))
			{
				u.getAbout().setAboutBusiness(menuAbout.getAboutBusiness());
				u.getAbout().setEmail(menuAbout.getEmail());
				u.getAbout().setUser(user);
				u.getAbout().setMobile(menuAbout.getMobile());
			}
		}
	
		 
		 userdatabaseServices.saveUser(user);
    	
		
		return "redirect:/viewPages";
	}

	@PostMapping("/updateServiceMenu")
	public String updateServiceMenu(@ModelAttribute menuServices menuServices,HttpSession session)
	{

		
		String email=(String) session.getAttribute("ok"); 
		
		User user= userdatabaseServices.findUserByEmail(email);
		
		for(userMenu u: user.getUserMenu())
		{
			if(u.getPageName().equals("Services"))
			{
				u.getMenuServices().setServiceDescription(menuServices.getServiceDescription());
				u.getMenuServices().setService1(menuServices.getService1());
				u.getMenuServices().setService2(menuServices.getService2());
				u.getMenuServices().setService3(menuServices.getService3());
				u.getMenuServices().setService4(menuServices.getService4());
				u.getMenuServices().setUser(user);
			}
		}
	
		 
		 userdatabaseServices.saveUser(user);
    	
		
		return "redirect:/viewPages";
	}
	
	@PostMapping("/updateContactMenu")
	public String updateContactMenu(@ModelAttribute menuContact menuContact,HttpSession session)
	{
		String email=(String) session.getAttribute("ok"); 
		
		User user= userdatabaseServices.findUserByEmail(email);
		
		for(userMenu u: user.getUserMenu())
		{
			if(u.getPageName().equals("Contact"))
			{
				u.getMenuContact().setMapLink(menuContact.getMapLink());
				u.getMenuContact().setAddress(menuContact.getAddress());
				u.getMenuContact().setEmail(menuContact.getEmail());
				u.getMenuContact().setMobile(menuContact.getMobile());
				u.getMenuContact().setUser(user);
			}
		}
	
		 
		 userdatabaseServices.saveUser(user);
		
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeHomeMenu/{id}")
	public  String deleteHomeMenu(@PathVariable("id") int id,HttpSession session)
	{
		String email=(String) session.getAttribute("ok");
		User u=userdatabaseServices.findUserByEmail(email);
		String logo=u.getHome().getLogo().toString();
		session.removeAttribute("homeMenuFound");
	
		userdatabaseServices.deleteMenuFromSite(id);
		
		File f=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages\\"+logo);
		f.delete();
		
		File file=new File("C:\\xampp\\htdocs\\themes\\gallery\\"+logo);
		file.delete();
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeAboutMenu/{id}")
	public  String deleteAboutMenu(@PathVariable("id") int id,HttpSession session)
	{
		
	
		session.removeAttribute("aboutMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeCustomMenu/{id}")
	public  String deleteCustomMenu(@PathVariable("id") int id,HttpSession session)
	{
		
		String email=(String) session.getAttribute("ok");
		User u=userdatabaseServices.findUserByEmail(email);
		
		userdatabaseServices.removeCustomMenu(id);
		if(u.getCustomPages().isEmpty())
		{
			session.removeAttribute("custompage");
		}
		return "redirect:/viewPages";
	}
	

	@RequestMapping("/deleteBooking/{id}")
	public String deleteBookingTable(@PathVariable("id") int id)
	{
		userdatabaseServices.deleteBooking(id);
		return "redirect:/showBooking";
	}
	
	@GetMapping("/removeServiceMenu/{id}")
	public  String deleteServiceMenu(@PathVariable("id") int id,HttpSession session)
	{
		
	
		session.removeAttribute("serviceMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		return "redirect:/viewPages";
	}
	
	@RequestMapping("/deleteContact/{id}")
	public String removeContact(@PathVariable("id") int id)
	{
		userdatabaseServices.deletContact(id);
		return "redirect:/showContact";
	}
	
	@GetMapping("/removeContactMenu/{id}")
	public  String deleteContactMenu(@PathVariable("id") int id,HttpSession session)
	{
		
	
		session.removeAttribute("contactMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeTeamMenu/{id}")
	public  String deleteTeamMenu(@PathVariable("id") int id,HttpSession session)
	{
		String email=(String)session.getAttribute("ok");
		User user=userdatabaseServices.findUserByEmail(email);
		List<menuTeam> menuTeam = user.getMenuTeams();
		if(menuTeam.size()>0)
		{
			
			session.setAttribute("teamImageExist", "image");
		}
		else
		{
		session.removeAttribute("teamMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		}
	
		
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeMenuMenu/{id}")
	public  String deleteMenuMenu(@PathVariable("id") int id,HttpSession session)
	{
		String email=(String)session.getAttribute("ok");
		User user=userdatabaseServices.findUserByEmail(email);
		List<restoMenu> restoMenu= user.getRestoMenus();
		if(restoMenu.size()>0)
		{
			
			session.setAttribute("menuExist", "image");
		}
		else
		{
		session.removeAttribute("ourMenuMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		}
	
		
		return "redirect:/viewPages";
	}
	
	@RequestMapping("/removeRestoMenu/{id}")
	public String removeRestoMenu(@PathVariable("id") int id)
	{
		userdatabaseServices.deleteRestoMenu(id);
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeEventMenu/{id}")
	public  String deleteevenMenu(@PathVariable("id") int id,HttpSession session)
	{
		String email=(String)session.getAttribute("ok");
		User user=userdatabaseServices.findUserByEmail(email);
		List<events> event= user.getEvents();
		if(!event.isEmpty())
		{
			
			session.setAttribute("eventExist", "image");
		}
		else
		{
		session.removeAttribute("eventMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		}
	
		
		return "redirect:/viewPages";
	}

	@GetMapping("/removeGalleryMenu/{id}")
	public  String deleteGalleryMenu(@PathVariable("id") int id,HttpSession session)
	{
		
		String email=(String)session.getAttribute("ok");
		User user=userdatabaseServices.findUserByEmail(email);
		
		List<menuGallery> g=user.getGallery();
		if(g.size()>0)
		{
			session.setAttribute("imageExist", "image");
		}
		else
		{
		session.removeAttribute("galleryMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		}
	
		return "redirect:/viewPages";
	}
	
	
	@GetMapping("/removeClientMenu/{id}")
	public  String deleteClientMenu(@PathVariable("id") int id,HttpSession session)
	{
		
		String email=(String)session.getAttribute("ok");
		User user=userdatabaseServices.findUserByEmail(email);
		List<menuClients> menuClients = user.getMenuClients();
		if(menuClients.size()>0)
		{
			session.setAttribute("clientImageExist", "image");
		}
		else
		{
		session.removeAttribute("clientMenuFound");
		userdatabaseServices.deleteMenuFromSite(id);
		}
	
		return "redirect:/viewPages";
	}
	
	
	@RequestMapping("/removeEvents/{id}")
	@Transactional
	public String removeEvent(@PathVariable("id") String image)
	{
		File  file=new File("C:\\xampp\\htdocs\\themes\\gallery\\"+image);
		file.delete();
		File  file1=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages\\"+image);
		file1.delete();
		userdatabaseServices.deleteEvent(image);
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeImage/{id}")
	@Transactional
	public String removeImage(@PathVariable("id") String id)
	{
		File  file=new File("C:\\xampp\\htdocs\\themes\\gallery\\"+id);
		file.delete();
		File  file1=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages\\"+id);
		file1.delete();
		
		userdatabaseServices.deleteImage(id);
		return "redirect:/viewPages";
	}
	
	
	@GetMapping("/removeClientImage/{id}")
	@Transactional
	public String removeClentImage(@PathVariable("id") String id)
	{
		File  file=new File("C:\\xampp\\htdocs\\themes\\gallery\\"+id);
		file.delete();
		File  file1=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages\\"+id);
		file1.delete();
		
		userdatabaseServices.deleteClientImage(id);
		return "redirect:/viewPages";
	}
	
	
	@GetMapping("/removeBackImage/{id}")
	@Transactional
	public String removeBackImage(@PathVariable("id") int  id)
	{
		List<backGroundImages> findBackImage = userdatabaseServices.findBackImage(id);
		String image = null;
		for (backGroundImages back : findBackImage) {
			image=back.getImage().toString();
		}
	
		File  file=new File("C:\\xampp\\htdocs\\themes\\gallery\\"+image);
		file.delete();
		File  file1=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages\\"+image);
		file1.delete();
		
		userdatabaseServices.deleteBackImage(id);
		return "redirect:/viewPages";
	}
	@GetMapping("/removeSpecialImage/{id}")
	@Transactional
	public String removeBackImage(@PathVariable("id") String  image)
	{
		
	
		File  file=new File("C:\\xampp\\htdocs\\themes\\gallery\\"+image);
		file.delete();
		File  file1=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages\\"+image);
		file1.delete();
		
		userdatabaseServices.deleteSpecial(image);
		return "redirect:/viewPages";
	}
	
	
	@RequestMapping("/removeTableMenu/{id}")
	public String deletetableMenu(@PathVariable("id") int id,HttpSession session)
	{
		userdatabaseServices.deleteMenuFromSite(id);
		session.removeAttribute("tableMenuFound");
		return "redirect:/viewPages";
	}
	
	@GetMapping("/removeTeamImage/{id}")
	@Transactional
	public String removeTeamImage(@PathVariable("id") String id)
	{
		File  file=new File("C:\\xampp\\htdocs\\themes\\gallery\\"+id);
		file.delete();
		File  file1=new File("D:\\sts project\\BMWebsiteBuilderTool\\src\\main\\resources\\static\\themeImages\\"+id);
		file1.delete();
		
		userdatabaseServices.deleteTeamMember(id); 
		return "redirect:/viewPages";
	}
	
	@PostMapping("/addAbout")
	public String addAbout(@ModelAttribute menuAbout about,HttpSession session,@RequestParam("pageName") String pageName)
	{
		 userMenu menu =  new userMenu();
		 String email=(String) session.getAttribute("ok");
		 User user = userdatabaseServices.findUserByEmail(email);
		
		 menu.setPageName(pageName);
		 about.setUser(user);
		 about.setMenu(menu);
		 menu.setAbout(about);
		 menu.setLogin(user);
		
		 
		
		userdatabaseServices.saveUserMenu(menu);
	
 		return "redirect:/addPage";
	}
	
	@PostMapping("/addServices")
	public String addService(@ModelAttribute menuServices menuServices,HttpSession session,@RequestParam("pageName") String pageName)
	{
		userMenu menu =  new userMenu();
		 String email=(String) session.getAttribute("ok");
		 User user = userdatabaseServices.findUserByEmail(email);
		 
		 menu.setPageName(pageName);
		 menu.setLogin(user);
		 menuServices.setUser(user);
		 menuServices.setUserMenu(menu);
		 menu.setMenuServices(menuServices);
		 userdatabaseServices.saveUserMenu(menu);
		 
		 
		return "redirect:/addPage";
	}
	
	@PostMapping("/addTableDetails")
	public String addableDetails(@ModelAttribute("menuTable") menuTable menuTable,HttpSession session)
	{
		String email=(String) session.getAttribute("ok");
		 User user = userdatabaseServices.findUserByEmail(email);
		 menuTable.setUser(user);
session.setAttribute("messageTable", "bok");
		 userdatabaseServices.addTableInfo(menuTable);
		
		return "redirect:/preview";
	}
	
	@PostMapping("/addContact")
	public String addContact(@ModelAttribute menuContact menuContact,@RequestParam("pageName") String pageName,HttpSession session)
	{
		userMenu userMenu = new userMenu();
		String email=(String) session.getAttribute("ok");
		
		User user = userdatabaseServices.findUserByEmail(email);
		userMenu.setPageName(pageName);
		userMenu.setLogin(user);
		menuContact.setUser(user);
		menuContact.setUserMenu(userMenu);
		userMenu.setMenuContact(menuContact);
		 userdatabaseServices.saveUserMenu(userMenu);
		 
		 
		 return "redirect:/addPage";
		
	}
	
	@PostMapping("/addTeam")
	@Transactional
	public String addTeam(HttpSession session,@RequestParam("pageName") String pageName ,@RequestParam("img") MultipartFile file,@RequestParam("name") String name,@RequestParam("post") String post) throws IOException
	{
		String email=(String) session.getAttribute("ok");
		userMenu userMenu=new userMenu();
		menuTeam menuTeam=new menuTeam();
		User user = userdatabaseServices.findUserByEmail(email);
		String teamImage=StringUtils.cleanPath(file.getOriginalFilename());
		
		menuTeam.setMemberName(name);
		menuTeam.setPost(post);
		menuTeam.setMemberImage(teamImage);
		menuTeam.setUser(user);
		logoUpload.uploadGallery(file);
		logoUpload.uploadImages(file);
		
		userMenu.setPageName(pageName);

		boolean flag=true;
		List<userMenu> pages=userdatabaseServices.findPage(user);
		
		for (userMenu userMenu2 : pages) {
			if(userMenu2.getPageName().toString().equals("Team"))
			{
				flag=false;
			}
		
		}
		
		if(flag)
		{
		userMenu.setPageName(pageName);
		userMenu.setLogin(user);
		userdatabaseServices.saveUserMenu(userMenu);
		}
		
		
		
		
		
		
		userdatabaseServices.saveTeam(menuTeam);
		
		return "redirect:/addPage";
	}
	  
	@RequestMapping("/showTheme")
	public String settings(Model model,HttpSession session)
	{
		List<allThemes> allThemes = userdatabaseServices.FetchAllThems();
		
		model.addAttribute("allThemes", allThemes);
	
		
		return "showTheme";
	}
	
	
	@RequestMapping("/welcome")
	public String welcome(Model model,HttpSession session)
	{
		String email=(String) session.getAttribute("ok");
		User user=userdatabaseServices.findUserByEmail(email);
		
		model.addAttribute("user", user);
		return "welcome";
	}
	
	@RequestMapping("/setDefault/{id}")
	public String changeTheme(@PathVariable("id") String name,HttpSession session)
	{
		String email=(String)session.getAttribute("ok");
		User user=userdatabaseServices.findUserByEmail(email);
		session.removeAttribute("backgroundImagesFound");
		session.removeAttribute("specilaMenuMenuFound");
		
	    String[] arrOfStr = name.split("\\.");
	    String themename=arrOfStr[0];
		
		session.setAttribute("theme", themename);
		if(user.getTheme()!=null)
		{
		user.getTheme().setName(name);
		
		
		}
		else
		{
			userTheme theme=new userTheme();
			theme.setName(name);
			theme.setUser(user);
			user.setTheme(theme);
		}
		userdatabaseServices.saveUser(user);
		return "redirect:/dashboard";
	}
	
	
	@PostMapping("/sendEmail")
	public String sendEmail(@ModelAttribute email email,HttpSession session)
	{
		String e=(String) session.getAttribute("ok");
		userdatabaseServices.sendEmail( "bhagyeshmorvadiya213@gmail.com", email.getMessage(),email.getEmail());
		return "redirect:/preview";
	}
	
	
	@GetMapping("/verifyEmail/{otp}")
	public String emailVerufy(@PathVariable("otp") String ot,HttpSession session)
	{
		
		try {
			int otp=(Integer) session.getAttribute("votp");
			User use=(User) session.getAttribute("vuser");
			if(use == null)
			{
				return "expire";
			}
			else {
				int ot1=Integer.parseInt(ot);
				
				if(otp==ot1)
				{
					userdatabaseServices.saveUser(use);
					session.removeAttribute("votp");
					session.removeAttribute("vuser");
					return "verifyEmail";
				}
				else
				{
				return "login";
				}
			}}
			catch(Exception e)
			{
				return "expire";
			}
	}
	
	@RequestMapping("/forgotPassword")
	public String forgotPass()
	{
		return "forgotPass";
	}
	
	@PostMapping("/sendForgotMail")
	public String sendforgotMail(@RequestParam("email") String email,HttpSession session)
	{

		String e_mail=email.trim();
	  User user= userdatabaseServices.findUserByEmail(e_mail);
	  if(user != null)
	  {
		  session.setAttribute("mail", email);
		  session.setAttribute("sendMail", "user");
		 int  otp= (int)(Math.random()*1000000);
		 session.setAttribute("otp", otp);
			String ot="Click Bellow Link To Reset Password \n\n http://localhost:8585/resetPassword/"+otp;
			userdatabaseServices.sendEmail(email,ot , "BM Tools Reset Password Link");
		 
		  return "redirect:/forgotPassword";
	  }
	  else
	  {
		  session.setAttribute("userNotExists", "user");
		  return "redirect:/forgotPassword";
	  }
	}
	
	@RequestMapping("/resetPassword/{id}")
	public String resetPass(@PathVariable("id") int id,Model model,HttpSession session)
	{

		try {
		String mail=(String) session.getAttribute("mail");
		int otp=(Integer) session.getAttribute("otp");
		
			if(otp==id)
			{
				
				return "resetPass";
			}
			else
			{
				return "redirect:/forgotPassword";
			}
		}
		catch(Exception e)
		{
			return "expire";
		}
		
		
	}
	
	@RequestMapping("/resetPassword")
	public String resetPassword(@RequestParam("confirmPassword") String password,HttpSession session)
	{
		
		String mail=(String) session.getAttribute("mail");
		User u=userdatabaseServices.findUserByEmail(mail);
		u.setPassword(password);
		
		userdatabaseServices.saveUser(u);
	
				session.setAttribute("reset", "reset");
		session.removeAttribute("mail");
		session.removeAttribute("otp");
		return "redirect:/login";
	}	//logout
		@RequestMapping("/logout")
		public String logout(HttpSession session)
		{
			session.removeAttribute("ok");
			session.removeAttribute("username");
			session.removeAttribute("homeMenuFound");
			session.removeAttribute("aboutMenuFound");
			session.removeAttribute("serviceMenuFound");
			session.removeAttribute("contactMenuFound");
			session.removeAttribute("clientMenuFound");
			session.removeAttribute("galleryMenuFound");
			session.removeAttribute("teamMenuFound");
			session.removeAttribute("noMenuFound");
			session.removeAttribute("custompage");
			session.removeAttribute("backgroundImagesFound");
			session.removeAttribute("specilaMenuMenuFound");
			session.removeAttribute("ourMenuMenuFound");
			session.removeAttribute("eventMenuFound");
			session.removeAttribute("tableMenuFound");
			
//			session.invalidate();
			session.setAttribute("logout","You are logout");
			return "redirect:/";
		}
}
