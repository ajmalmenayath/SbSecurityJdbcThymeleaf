package org.o7planning.sbsecurity.service;

import org.o7planning.sbsecurity.dao.insertDao;
import org.o7planning.sbsecurity.formbean.AppUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class insertService {

	
	@Autowired
	
	private insertDao indao;
	
	
	public int insert(AppUserForm app) {
		
		return indao.insert(app);
		
		
	}
	
	
}
