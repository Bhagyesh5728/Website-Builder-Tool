package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.menuGallery;

@Repository
public interface fetchImages extends CrudRepository<menuGallery, Integer> {
public void deleteByImg(String img);

}
