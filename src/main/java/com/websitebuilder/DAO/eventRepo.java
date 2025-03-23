package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.events;

@Repository
public interface eventRepo extends CrudRepository<events, Integer> {
public void deleteByImage(String image); 
}
