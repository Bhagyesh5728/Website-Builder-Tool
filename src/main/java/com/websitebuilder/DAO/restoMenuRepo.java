package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.restoMenu;

@Repository
public interface restoMenuRepo extends CrudRepository<restoMenu, Integer> {

}
