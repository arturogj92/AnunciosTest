package com.adevgar.pisos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adevgar.pisos.model.Advert;
import com.adevgar.pisos.model.DAO.AdvertUserDAO;
import com.adevgar.pisos.service.AdvertService;
import com.adevgar.pisos.service.AdvertServiceImpl;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
public class AdvertController {

	AdvertService advertService;

	@GetMapping("/quality")
	public ResponseEntity<?> getPiso() {
		List<Advert> adverts = advertService.findIrrelevant();

		if (adverts.isEmpty()) {
			return new ResponseEntity<>("There is no announcements yet", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(adverts, HttpStatus.OK);
	}

	@PostMapping("/puntuation")
	public ResponseEntity<?> getPuntuations() {

		List<Advert> adverts = advertService.findAll();
		adverts = advertService.setAdvertPuntuation(adverts);

		if (adverts.isEmpty()) {
			return new ResponseEntity<>("There is no announcements yet", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(adverts, HttpStatus.OK);
	}

	@GetMapping("/user")
	public ResponseEntity<?> getPisosUsuario() {
		List<AdvertUserDAO> adverts = advertService.getRelevantAndPuntuatedAdverts();

		if (adverts.isEmpty()) {
			return new ResponseEntity<>("There is no relevant announcements yet", HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(adverts, HttpStatus.OK);
	}

}
