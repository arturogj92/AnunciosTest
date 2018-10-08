package com.adevgar.pisos.service;

import java.util.List;

import com.adevgar.pisos.model.Advert;
import com.adevgar.pisos.model.DAO.AdvertUserDAO;

public interface AdvertService {

	public List<Advert> findAll();
	public List<AdvertUserDAO> getRelevantAndPuntuatedAdverts();
	public List<Advert> setAdvertPuntuation(List<Advert> advertList);
	public List<AdvertUserDAO> getRelevantAdverts(List<Advert> advertList);
	public void defaultValues();
}
