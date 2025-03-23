package com.websitebuilder.DAO;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.User;
import java.util.List;


@Repository
public interface getUserInfoRepo  extends CrudRepository<User, Integer>{
	
		
		public User findByEmail(String email);
	
		public List<User> findByEmailAndPassword(String email, String password);
 }
