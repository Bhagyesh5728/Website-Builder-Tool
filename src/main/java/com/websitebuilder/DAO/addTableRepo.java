package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.menuTable;

@Repository
public interface addTableRepo extends CrudRepository<menuTable, Integer> {

}
