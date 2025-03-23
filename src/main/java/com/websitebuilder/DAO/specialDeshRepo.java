package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.specialDishes;

@Repository
public interface specialDeshRepo extends  CrudRepository<specialDishes, Integer> {

	public void deleteByImage(String image);
}
