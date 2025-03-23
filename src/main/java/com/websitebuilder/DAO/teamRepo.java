package com.websitebuilder.DAO;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.websitebuilder.entity.menuTeam;

@Repository
public interface teamRepo extends CrudRepository<menuTeam, Integer> {
public void deleteByMemberImage(String memberImage);
}
