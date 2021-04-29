package org.o7planning.sbsecurity.controller;

import org.springframework.http.MediaType;
import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.o7planning.sbsecurity.service.ItemService;
import org.o7planning.sbsecurity.service.QuotationService;
import org.o7planning.sbsecurity.validator.QuotationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class QuotationContoller {
	
	@Autowired
	private QuotationValidator qvalidator;
	
	@Autowired
	private QuotationService QS;
	
	@Autowired
	private ItemService Iservice;
	
	// Set a form validator
		  @InitBinder
		  protected void initBinder(WebDataBinder dataBinder) {
		     // Form target
		     Object target = dataBinder.getTarget();
		     if (target == null) {
		        return;
		     }
		     System.out.println("Target=" + target);

		     if (target.getClass() == QuotationModel.class) {
		        dataBinder.setValidator(qvalidator);
		       
		     }

		    
		     // ...
		     
		   
		  }
	
	
	@RequestMapping(value = "/newQuotation",method = RequestMethod.GET )
	public String generateQuotation(Model model)
	{
		List<SubcategoryModel> allsubcat=QS.allsubcat();
		QuotationModel Qform=new QuotationModel();
		List<String> catname= QS.select_catneme();
		model.addAttribute("allitem", allsubcat);
		model.addAttribute("catname", catname);
		model.addAttribute("quotation_Form", Qform);
		return "GenerateQaotation";
	}
	
	@RequestMapping(value = "/add_quotation_form",method = RequestMethod.POST)
	public String insert_Quotation(Model model, @ModelAttribute("quotation_Form")  @Validated QuotationModel Qmodl, //
  	        BindingResult result, //
  	        final RedirectAttributes redirectAttributes)
	{
		 if (result.hasErrors()) {
 	        
 	        return "GenerateQaotation";
 	     }
		
		return "GenerateQaotation";
	}
	
	@PostMapping("/addUser")
	public void addUser(@RequestBody QuotationModel user) {
		System.out.println(user.getCname());
		System.out.println(user.getPayment());
		System.out.println("hiiiiiiiiiiiiiiiiiiii");
		
	}
	 @RequestMapping(value = "/addqtdetails", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody QuotationModel addproperty(@RequestBody QuotationModel qm) {
		
		 String cname=qm.getCname();
		
		 if(cname.isEmpty()){
			 qm.setCname(null);
			 
			 return qm;
		 }
		 else {
			 if(qm.getCid()==0) {
				 
				 int CDdetails=QS.CD_select_cname(qm.getCname(), qm.getTrnno());
				 if(CDdetails==0) {
					
					 
					 int cdetails=QS.customerDetails_insert(qm);
					 if(cdetails!=0) {
						 System.out.println("cdid---------------="+QS.cdid());
						 qm.setCdid(QS.cdid());
						 int res= QS.insertQuotation(qm);
							if(res !=0)
							{
								Date date = new Date();
								SimpleDateFormat formatter = new SimpleDateFormat("yy");
								String inno=QS.cid()+"_"+formatter.format(date);
								//insert invorse no
								QS.insertinvorse_no(inno, QS.cid());
								
									QuotationModel QModel=new QuotationModel();
									
									QModel.setCid(QS.cid());
									QModel.setTrnno(qm.getTrnno());
									QModel.setEmirates(qm.getEmirates());
									QModel.setValidity(qm.getValidity());
									QModel.setWarranty(qm.getWarranty());
									QModel.setDelivery(qm.getDelivery());
									QModel.setPayment(qm.getPayment());
									QModel.setCname(qm.getCname());
									QModel.setCam_type(qm.getCam_type());
									
								 
									return QModel;
									
							}
							else {
								return qm;
							}
			
					 }
					 else {
						 return qm;
					}

					 
				 }
				 else {
					    qm.setCdid(CDdetails);
					 
						int res= QS.insertQuotation(qm);
						if(res !=0)
						{
							
							Date date = new Date();
							SimpleDateFormat formatter = new SimpleDateFormat("yy");
							
							String inno=QS.cid()+"_"+formatter.format(date);
							System.out.println("inno======"+inno);
							//insert invorse no
							QS.insertinvorse_no(inno, QS.cid());
								
								QuotationModel QModel=new QuotationModel();
									
								QModel.setCid(QS.cid());
								QModel.setTrnno(qm.getTrnno());
								QModel.setEmirates(qm.getEmirates());
								QModel.setValidity(qm.getValidity());
								QModel.setWarranty(qm.getWarranty());
								QModel.setDelivery(qm.getDelivery());
								QModel.setPayment(qm.getPayment());
								QModel.setCname(qm.getCname());
								QModel.setCam_type(qm.getCam_type());
									
								 
								return QModel;
									
						}
						else {
							return qm;
						}
			
				 }
				
			 }
			 else
			 {
				 
				  int CDdetails=QS.CD_select_cname(qm.getCname(), qm.getTrnno());
				  if(CDdetails==0) {
					  int cdetails=QS.customerDetails_insert(qm);
					  if(cdetails!=0) {	
						  
							int qtupdate=QS.updateQTdetails(qm);
							  if(qtupdate==0) {
								  return qm;
							  }
							  else {
								    QuotationModel QModel=new QuotationModel();
									
									QModel.setCid(QS.cid());
									QModel.setTrnno(qm.getTrnno());
									QModel.setEmirates(qm.getEmirates());
									QModel.setValidity(qm.getValidity());
									QModel.setWarranty(qm.getWarranty());
									QModel.setDelivery(qm.getDelivery());
									QModel.setPayment(qm.getPayment());
									QModel.setCname(qm.getCname());
									QModel.setCam_type(qm.getCam_type());
																		
									return QModel;
							  }
						  
					  }
					  else
					  {
						  return qm;
					  }
					  
				  }
				  else {
					  qm.setCdid(QS.cdid());
					  int qtupdate=QS.updateQTdetails(qm);
					  if(qtupdate==0) {
						  return qm;
					  }
					  else {
						    QuotationModel QModel=new QuotationModel();
							
							QModel.setCid(QS.cid());
							QModel.setTrnno(qm.getTrnno());
							QModel.setEmirates(qm.getEmirates());
							QModel.setValidity(qm.getValidity());
							QModel.setWarranty(qm.getWarranty());
							QModel.setDelivery(qm.getDelivery());
							QModel.setPayment(qm.getPayment());
							QModel.setCname(qm.getCname());
							QModel.setCam_type(qm.getCam_type());
							
							return QModel;
					  }
					  
				  }
				 
			 }
			
		 }
		  
	 }
	
	 @RequestMapping(value = "/addnote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody NoteModel addnote(@RequestBody NoteModel qm) {
		 
		 if(qm.getNote().isEmpty())
		 {
			 return null;
		 }
		 int res=QS.insertNotes(qm);
		 if(res!=0)
		 {
			 
			 NoteModel Nmodel=new NoteModel();
			 Nmodel.setNote(qm.getNote());
			 Nmodel.setCid(qm.getCid());
			 Nmodel.setNid(QS.nid());
			 return Nmodel;
		 }
		 else
		 {
			 return null;
		 }
	 
	 }
	
	 @RequestMapping(value = "/delete_note", method = RequestMethod.GET)
	 public @ResponseBody int  delete_note(@RequestParam Integer id,Integer cid)
	 {
	   // System.out.println("get is got");
	    boolean b=QS.del_note(id,cid);
	    if(b)
	    	 return id;
	    else
	    	return 0;
	   
	 }
	
	 @RequestMapping(value = "/listcat_name", method = RequestMethod.GET)
	 public @ResponseBody List<SubcategoryModel>  list_catname(@RequestParam String cat_name)
	 {
		 
		 if(cat_name.equals("all"))
		 {
			 List<SubcategoryModel> allsubcat=QS.allsubcat();
			 return allsubcat;
		 }
		 else
		 {
			 List<SubcategoryModel> subcat_cat_name=QS.subcat_cat_name(cat_name);
			 return subcat_cat_name;
		 }
		
	 }
	 
	 @RequestMapping(value = "/listitems", method = RequestMethod.GET)
	 public @ResponseBody ItemModel list_subcat(@RequestParam Integer subid,Integer cid)
	 {
		 
		 int count=Iservice.count_subid(subid,cid);
		 if(count!=0)
		 {
			 ItemModel itemcount=Iservice.select_subid_item(subid,cid);
			 int cnt=itemcount.getCount()+1;
			 itemcount.setCount(cnt);
			 int total=cnt*itemcount.getPrice();
			 itemcount.setTotal(total);
			 int addcount=Iservice.updateCount(itemcount);
			 System.out.println(addcount);
			 int sum=Iservice.sumofprice(cid);
			 itemcount.setSum(sum);
			 return itemcount;
		 }
		 else
		 {
			 ItemModel itemmodel=new ItemModel();
			 SubcategoryModel items= QS.subcat_subid(subid);
			 itemmodel.setItem_name(items.getSub_name());
			 itemmodel.setCount(1);
			 itemmodel.setPrice(items.getPrice());
			 int total=itemmodel.getCount()*items.getPrice();
			 itemmodel.setTotal(total);
			 itemmodel.setCid(cid);
			 itemmodel.setSub_id(subid);
			 int res=Iservice.insert_Item(itemmodel);
			 if(res!=0)
			 {
				 int sum=Iservice.sumofprice(cid);
				 itemmodel.setSum(sum);
				 return itemmodel;
			 }
			 else
			 {
				 return null;
			 }
		 }
		
	 }
	 
	 @RequestMapping(value = "/delete_item", method = RequestMethod.GET)
	 public @ResponseBody ItemModel  delete_item(@RequestParam String btid,Integer cid)
	 {
	  
		 int subid=Integer.parseInt(btid.substring(0,btid.length()-3));
		 boolean b=Iservice.delete_item(subid, cid);
		 if(b)
		 {
			 ItemModel imodel=new ItemModel();
			 int sum=Iservice.sumofprice(cid);
			 imodel.setSub_id(subid);
			 imodel.setSum(sum);
			 return imodel;
		 }
		 else {
			return null;
		}
		     
	 }
	 
	 @RequestMapping(value = "/update_item", method = RequestMethod.GET)
	 public @ResponseBody ItemModel update_item(@RequestParam Integer trid,Integer cid,Integer price,Integer total,Integer count)
	 {
		 
		 ItemModel imodel=new ItemModel();
		 imodel.setCid(cid);
		 imodel.setSub_id(trid);
		 imodel.setPrice(price);
		 imodel.setCount(count);
		 imodel.setTotal(price*count);
		
		 int res=Iservice.update_Item(imodel);
		 if(res!=0) {
			 int sum=Iservice.sumofprice(cid);
			 imodel.setSum(sum);
			 return imodel;
			 
		 }
		 else {
			 return null;
		 }
		     
	 }
	 
	@RequestMapping(value = "/discard/{cid}", method = RequestMethod.GET)
	public String Dicsard(@PathVariable("cid") Integer cid) {
		
		//int cid1=Integer.parseInt(cid);
		boolean b=QS.delete_discard(cid);
		if(b==true)
		{
		   return "redirect:/admin";
		}
		else
		{
			return "redirect:/newQuotation";
		}
	 }
	
	 @RequestMapping("/autocmplt_cname")
	 public @ResponseBody List<QuotationModel> autocmplt_cname(@RequestParam String term) {
		  
		  List<QuotationModel> list = QS.likecname(term);
		  
		  return list;
	 }
	 ////////////////////
	 @RequestMapping(value = "/manualItemAdd", method = RequestMethod.GET)
	 public @ResponseBody ItemModel  ManualAdd_item(@RequestParam Integer item_id,Integer cid,String item_name,Integer price,Integer count) {
		
		 ItemModel imodel=new ItemModel();
		
		 
		 if(item_id==null) {
			 System.out.println("in 0");
			 int catmodel=QS.CountOfmanual();
			 if(catmodel==0) {
				 System.out.println("in 1");
				 
				 int res=QS.insert_Manualcat();
				 if(res!=0) {
					 int cat_id=QS.selectManualId();
					 if(cat_id!=0) {
						 SubcategoryModel submodel=new SubcategoryModel();
						 submodel.setCat_id(cat_id);
						 submodel.setPrice(price);
						 submodel.setSub_name(item_name);
						 
						 
						 int subcatadd=QS.insertManual_subcategory(submodel);
						 if(subcatadd!=0) {
							 System.out.println("in 2");
							 int sub_id=QS.selectManualSubid(item_name);
							 if(sub_id!=0) {
								 System.out.println("in 3");
								 imodel.setCid(cid);
								 imodel.setItem_name(item_name);
								 imodel.setSub_id(sub_id);
								 imodel.setCount(count);
								 imodel.setPrice(price);
								 int tot=count*price;
								 imodel.setTotal(tot);
								 
								 int additem=QS.insertManual_Item(imodel);
								 if(additem!=0) {
									 int sum=Iservice.sumofprice(cid);
									 imodel.setSum(sum);
									 
									 return imodel;
								 }
								 else {
									 return null;
								 }
								 
							 }
							 else {
								 return null;
							 }
							 
						 }
						 else {
							 System.out.println("subcat add faied");
							 return null;
							
						 }
					 }
					 else {
						 System.out.println("cat_id failed ");
						 return null;

					 }
				 }
				 else {
					 System.out.println("addcategory failed");
					 return null;

				 }
				 
			 }
			 else {
				 
				 int cat_id=QS.selectManualId();
				 if(cat_id!=0) {
					 SubcategoryModel submodel=new SubcategoryModel();
					 submodel.setCat_id(cat_id);
					 submodel.setPrice(price);
					 submodel.setSub_name(item_name);
					 
					 
					 int subcatadd=QS.insertManual_subcategory(submodel);
					 if(subcatadd!=0) {
						 
						 int sub_id=QS.selectManualSubid(item_name);
						 if(sub_id!=0) {
							 
							 imodel.setCid(cid);
							 imodel.setItem_name(item_name);
							 imodel.setSub_id(sub_id);
							 imodel.setCount(count);
							 imodel.setPrice(price);
							 int tot=count*price;
							 imodel.setTotal(tot);
							 
							 int additem=QS.insertManual_Item(imodel);
							 if(additem!=0) {
								 int sum=Iservice.sumofprice(cid);
								 imodel.setSum(sum);
								 return imodel;
							 }
							 else {
								 return null;
							 }
							 
						 }
						 else {
							 return null;
						 }
						 
					 }
					 else {
						 System.out.println("subcat add faied");
						 return null;
						
					 }
				 }
				 else {
					 System.out.println("cat_id failed ");
					 return null;

				 }

			 }
		 }
		 else {
			 
			 int numof=Iservice.count_subid(item_id, cid);
			 if(numof!=0) {
				 ItemModel itemcount=Iservice.select_subid_item(item_id,cid);
				// ItemModel itemcount=new ItemModel();
				 int cnt=itemcount.getCount()+count;
				 itemcount.setSub_id(item_id);
				 itemcount.setCid(cid);
				 itemcount.setCount(cnt);
				 itemcount.setItemid(Iservice.manual_itemid(item_id, cid));
				 itemcount.setPrice(price);
				 itemcount.setItem_name(item_name);
				 int total=cnt*price;
				 itemcount.setTotal(total);
				 int addcount=Iservice.updateManualCount(itemcount);
				 System.out.println(addcount);
				 int sum=Iservice.sumofprice(cid);
				 itemcount.setSum(sum);
				 return itemcount;
				 
			 }
			 else {
				 ItemModel itemmodel=new ItemModel();
				 
				 itemmodel.setItem_name(item_name);
				 itemmodel.setCount(count);
				 itemmodel.setPrice(price);
				 int total=count*price;
				 itemmodel.setTotal(total);
				 itemmodel.setCid(cid);
				 itemmodel.setSub_id(item_id);
				 int res=Iservice.insert_Item(itemmodel);
				 if(res!=0)
				 {
					 int sum=Iservice.sumofprice(cid);
					 itemmodel.setSum(sum);
					 return itemmodel;
				 }
				 else
				 {
					 return null;
				 }
			 }
				 
			 
		}
		 
	 }
	 
	 @RequestMapping("/autocmplt_mname")
	 public @ResponseBody List<SubcategoryModel> autocmplt_mname(@RequestParam String term) {
		  
		  List<SubcategoryModel> list = QS.likemname(term);
		  
		  return list;
	 }
	
}
