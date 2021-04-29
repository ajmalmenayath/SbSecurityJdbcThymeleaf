package org.o7planning.sbsecurity.service;

import java.util.List;

import org.o7planning.sbsecurity.dao.CustomerDao;
import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CustomerService {
	@Autowired
	private CustomerDao Cdao;
	
	public int customerDetails_insert(QuotationModel Qmodel) {
		return Cdao.customerDetails_insert(Qmodel);
	}
	
	public List<QuotationModel> select_customer() {
		 return Cdao.select_customer();
	}
	public boolean delete_customer(int cdid) {
		return Cdao.delete_customer(cdid);
	}
	public QuotationModel update_customer(int cdid) {
		return Cdao.update_customer(cdid);
	}
	public int update_customer(QuotationModel qmodel) {
		return Cdao.update_customer(qmodel);
	}
	public List<QuotationModel> Quotation_Cdid(int cdid) {
		return Cdao.Quotation_Cdid(cdid);
	}
	 public QuotationModel UpdateQuotation_Cid(int cid) {
		 return Cdao.UpdateQuotation_Cid(cid);
	 }
	 public int update_generetedQtn(QuotationModel qmodel) {
		 return Cdao.update_generetedQtn(qmodel);
	 }
	 public List<NoteModel> updatedNote(int cid) {
		 return Cdao.updatedNote(cid);
	 }
	 public List<ItemModel> UpdatedQtsItem(int cid) {
		 return Cdao.UpdatedQtsItem(cid);
	 }
	 public int nid() {
		 return Cdao.nid();
	 }
	 public List<QuotationModel> All_Quotations() {
		 return Cdao.All_Quotations();
	 }
	

}
