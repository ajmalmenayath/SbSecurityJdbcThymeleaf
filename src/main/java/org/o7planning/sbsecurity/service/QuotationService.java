package org.o7planning.sbsecurity.service;

import java.util.List;

import org.o7planning.sbsecurity.dao.QuotationDao;
import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuotationService {
	@Autowired
	private QuotationDao qd;
	
	public int insertQuotation(QuotationModel Qmodel)
	{
		return qd.insert_quotation(Qmodel);
	}
	public int cid()
	{
		return qd.cid();
	}
	public int insertNotes(NoteModel Nmodel) 
	{
		return qd.insert_notes(Nmodel);
	}
	public int nid()
	{
		return qd.nid();
	}
	
	public boolean del_note(int id,int cid)
	{
		return qd.delete_note(id,cid);
	}
	public List<SubcategoryModel> allsubcat() {
		return qd.allsubcat();
		
	}
	
	public List<String> select_catneme() {
		return qd.select_catneme();
		
	}
	public int updateQTdetails(QuotationModel QM) {
		return qd.updateQTdetails(QM);
		
	}
	public int updateQTdetails_cdid(QuotationModel QM) {
		return qd.updateQTdetails_cdid(QM);
		
	}
	public List<SubcategoryModel> subcat_cat_name(String cat_name) {
		return qd.subcat_cat_name(cat_name);
		
	}
	public SubcategoryModel subcat_subid(int id) {
		
		return qd.subcat_subid(id);
		
	}
	public boolean delete_discard(int cid) {
		return qd.delete_discard(cid);
				
	}
	public List<QuotationModel> likecname(String name){
		return qd.likecname(name);
	}
	public int customerDetails_insert(QuotationModel qm) {
		return qd.customerDetails_insert(qm);
	}
	public int CD_select_cname(String cname,String trn){
		return qd.CD_select_cname(cname, trn);
	}
	public int cdid()
	{
		return qd.cdid();
	}
	
	////////////////////////
	 public int CountOfmanual() {
		 return qd.CountOfmanual();
	 }
	 
	 public int insert_Manualcat() {
		 return qd.insert_Manualcat();
		  
	 }
	 public int selectManualId() {
		 return qd.selectManualId();
	 }
	 public int insertManual_subcategory(SubcategoryModel submodel) {
		 return qd.insertManual_subcategory(submodel);
	 }
	 public int selectManualSubid(String subname) {
		 return qd.selectManualSubid(subname);
	 }
	 public int insertManual_Item(ItemModel Imodel) {
		 return qd.insertManual_Item(Imodel);
	 }
	 public List<SubcategoryModel> likemname(String name){
		return qd.likemname(name);
	 }
	 public int insertinvorse_no(String invorse_no,int cid) {
		 return qd.insertinvorse_no(invorse_no, cid);
	 }

}
