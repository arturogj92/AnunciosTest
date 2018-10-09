package com.adevgar.pisos.service;

import java.sql.Date;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.adevgar.pisos.model.Advert;
import com.adevgar.pisos.model.DAO.AdvertUserDAO;
import com.adevgar.pisos.repository.AdvertJpaRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Service
@AllArgsConstructor
public class AdvertServiceImpl implements AdvertService {

	public static final int IRRELEVANT_POINTS = 40;
	public static final int IMAGE_POINTS = 50;
	public static final int SPECIAL_WORD_POINTS = 5;
	public static final int DESCRIPTION_POINTS = 30;
	
	
	AdvertJpaRepository advertJpaRepository;
	
	
	@Override
	public List<Advert> findAll() {

		List<Advert> advertList = advertJpaRepository.findAll();

		if (!advertList.isEmpty()) {
			return advertList;
		}

		return null;

	}
	
	@Override
	public List<Advert> findIrrelevant(){
		
		List<Advert> advertList = advertJpaRepository.findAll();
		List<Advert> auxList = new ArrayList<Advert>();
		
		
		advertList.sort(Comparator.comparing(Advert::getPoints).reversed());

		for (Advert p : advertList) {

			if (p.getPoints() < IRRELEVANT_POINTS ) {
				auxList.add(p);
			}

		}

		return auxList;
	}

	@Override
	public List<AdvertUserDAO> getRelevantAndPuntuatedAdverts() {

		List<Advert> advertList = advertJpaRepository.findAll();

		List<AdvertUserDAO> advertListUser = getRelevantAdverts(advertList);

		return advertListUser;

	}

	@Override
	public List<Advert> setAdvertPuntuation(List<Advert> advertList) {

		String[] specialWords = { "luminoso", "nuevo", "centrico", "reformado", "atico" };

		for (Advert p : advertList) {
			int puntuation = 0;

			if (!p.getDescription().isEmpty()) {
				puntuation += DESCRIPTION_POINTS;

				for (String w : specialWords) {

					String description = Normalizer.normalize(p.getDescription(), Normalizer.Form.NFD)
							.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
					;

					if (description.toLowerCase().contains(w)) {
						puntuation += SPECIAL_WORD_POINTS;
					}
				}

			}

			if (p.getImages().size() > 0) {
				puntuation += IMAGE_POINTS;
			}

			puntuation = puntuation > 100 ? 100 : puntuation;
			p.setPoints(puntuation);
			advertJpaRepository.save(p);
		}

		return advertList;

	}

	@Override
	public List<AdvertUserDAO> getRelevantAdverts(List<Advert> advertList) {

		List<AdvertUserDAO> auxList = new ArrayList<>();

		advertList.sort(Comparator.comparing(Advert::getPoints).reversed());

		for (Advert p : advertList) {

			if (p.getPoints() >= IRRELEVANT_POINTS) {
				AdvertUserDAO userAdvert = new AdvertUserDAO(p.getId(), p.getDescription(), p.getImages());
				auxList.add(userAdvert);

			}

		}

		return auxList;
	}

	@Override
	public void defaultValues() {

		List<String> advertList1 = new ArrayList<String>();
		Advert piso1 = new Advert(1, "Este piso es una ganga, compra, compra, COMPRA!!!!!", advertList1, 0,
				Date.valueOf("2018-03-25"));

		List<String> advertList2 = new ArrayList<String>();
		advertList2.add("http://www.idealista.com/pictures/2.jpg");
		Advert piso2 = new Advert(2,
				"Nuevo ático céntrico recién reformado. No deje pasar la oportunidad y adquiera este ático de lujo",
				advertList2, 0, Date.valueOf("2018-04-25"));

		List<String> advertList3 = new ArrayList<String>();
		advertList3.add("http://www.idealista.com/pictures/3.jpg");
		Advert piso3 = new Advert(3, "", advertList3, 0, Date.valueOf("2018-06-21"));

		List<String> advertList4 = new ArrayList<String>();
		advertList4.add("http://www.idealista.com/pictures/5.jpg");
		Advert piso4 = new Advert(4, "Ático céntrico muy luminoso y recién reformado, parece nuevo", advertList4, 0,
				Date.valueOf("2018-11-21"));

		List<String> advertList5 = new ArrayList<String>();
		advertList5.add("http://www.idealista.com/pictures/3.jpg");
		advertList5.add("http://www.idealista.com/pictures/4.jpg");
		Advert piso5 = new Advert(5, "Pisazo", advertList5, 0, Date.valueOf("2018-02-25"));

		advertJpaRepository.save(piso1);
		advertJpaRepository.save(piso2);
		advertJpaRepository.save(piso3);
		advertJpaRepository.save(piso4);
		advertJpaRepository.save(piso5);

	}

}
