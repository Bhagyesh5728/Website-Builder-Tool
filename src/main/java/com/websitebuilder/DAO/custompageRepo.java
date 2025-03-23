package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.customPage;
@Repository
public interface custompageRepo extends CrudRepository<customPage, Integer>{

}
