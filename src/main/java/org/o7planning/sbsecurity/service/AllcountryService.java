package org.o7planning.sbsecurity.service;


import java.util.List;

import org.o7planning.sbsecurity.model.AllCountry;
import org.o7planning.sbsecurity.dao.AllCountryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AllcountryService {
	
	@Autowired
	private AllCountryDao Acd;
	
	public List<AllCountry> AllCountryname() {
		return Acd.countryName();
		
	}

	public List<AllCountry>  likecontry(String name){
		return Acd.likecontry(name);
	}
}
