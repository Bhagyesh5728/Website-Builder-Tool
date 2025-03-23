package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.menuClients;
@Repository
public interface clientsImageRepo extends CrudRepository<menuClients, Integer>{

	public void deleteByClientImage(String img);

}
