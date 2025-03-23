package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.backGroundImages;
import java.util.List;


@Repository
public interface backimageRepo extends CrudRepository<backGroundImages, Integer>{

public  List<backGroundImages> findById(int id);
}
