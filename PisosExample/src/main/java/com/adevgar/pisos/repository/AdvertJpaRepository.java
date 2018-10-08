package com.adevgar.pisos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adevgar.pisos.model.Advert;

@Repository
public interface AdvertJpaRepository extends JpaRepository<Advert, Integer>{

	public List<Advert> findAll();
	
	
}
