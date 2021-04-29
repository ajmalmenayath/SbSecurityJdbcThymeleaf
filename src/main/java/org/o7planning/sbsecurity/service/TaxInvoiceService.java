package org.o7planning.sbsecurity.service;

import org.o7planning.sbsecurity.dao.TaxInvoiceDao;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class TaxInvoiceService {
	
	@Autowired
	private TaxInvoiceDao Dao;
	
	public QuotationModel CustomerDetails(int cdid) {
		return Dao.CustomerDetails(cdid);
	}
	public int update_supplydate(int cid,String supplydt) {
		return Dao.update_supplydate(cid, supplydt);
	}

	public int update_lpo(int cid,String lpo) {
		return Dao.update_lpo(cid, lpo);
	}

}
