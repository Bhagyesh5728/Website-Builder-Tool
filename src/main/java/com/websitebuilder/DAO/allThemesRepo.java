package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.allThemes;



@Repository
public interface allThemesRepo  extends CrudRepository<allThemes,Integer>{

	public void save(allThemesRepo them);
	public allThemes findByName(String name);
	public void deleteByName(String name);
	
}
