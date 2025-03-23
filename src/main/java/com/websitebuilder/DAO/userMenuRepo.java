package com.websitebuilder.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.User;
import com.websitebuilder.entity.userMenu;


@Repository
public interface userMenuRepo extends CrudRepository<userMenu, Integer>  {

	@Procedure(name = "user.addmenu")
	public abstract void saveMenuAndMenuInfo(String pageName,String heading_line,String link,String logoName,String siteTitle,String subHeading);
	
	public List<userMenu> findByLogin(User login);
	
}
