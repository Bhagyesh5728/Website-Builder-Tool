package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.adminLogin;



@Repository
public interface adminLoginRepo extends CrudRepository<adminLogin, Integer> {

	adminLogin findByUsernameAndPassword(String username,String password);
}
