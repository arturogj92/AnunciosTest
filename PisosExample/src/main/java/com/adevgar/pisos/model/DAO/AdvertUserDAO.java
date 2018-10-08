package com.adevgar.pisos.model.DAO;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AdvertUserDAO {

	
	private int id;
	private String description;
	private List<String> images;
	
}
