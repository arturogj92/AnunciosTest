package com.adevgar.pisos.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Table(name = "PISOS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Advert {

	@Column(name = "ID")
	@Id
	private int id;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "IMAGES")
	@ElementCollection(targetClass = String.class)
	private List<String> images;

	@Column(name = "POINTS")
	private int points;

	@Column(name = "DATE")
	private Date date;
}
