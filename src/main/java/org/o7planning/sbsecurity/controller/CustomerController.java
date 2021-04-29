package org.o7planning.sbsecurity.controller;

import java.util.List;

import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.o7planning.sbsecurity.service.CustomerService;
import org.o7planning.sbsecurity.service.ItemService;
import org.o7planning.sbsecurity.service.QuotationService;
import org.o7planning.sbsecurity.validator.QuotationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
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

@Controller

public class CustomerController {
	
	@Autowired
	private QuotationValidator Qvalidate;
	
	// Set a form validator
	  @InitBinder
	  protected void initBinder(WebDataBinder dataBinder) {
	     // Form target
	     Object target = dataBinder.getTarget();
	     if (target == null) {
	        return;
	     }
	     System.out.println("Target    =" + target);

	     if (target.getClass() == QuotationModel.class) {
	        dataBinder.setValidator(Qvalidate);
	       
	     }

	    
	     // ...
	     
	   
	}
	
	  @Autowired
	  private CustomerService Cservice;
	  
	  @Autowired 
	  private QuotationService QS;
	  
	  @Autowired
	  private ItemService Iservice;
	  
	@RequestMapping("/addcustomer")
	public String AddCustomer(Model model) {
		QuotationModel Qform=new QuotationModel();
		model.addAttribute("addcustomerobj", Qform);
		return "addCustomer";
	}
	
	@RequestMapping(value="/add_customer_form",method = RequestMethod.POST)
	public String AddCustomer_form(Model model, @ModelAttribute("addcustomerobj")  @Validated QuotationModel Qmodl, //
  	        BindingResult result, //
  	        final RedirectAttributes redirectAttributes) {
		
		 if (result.hasErrors()) {
		        
		        return "addCustomer";
		 }
		
		int res=Cservice.customerDetails_insert(Qmodl);
		if(res!=0) {
			return "redirect:/addcustomer";
		}
		else {
			return "addCustomer";
		}
	
		
	}
	
	@RequestMapping("/showCustomer")
	public String searchCustomer(Model model) {
		
		List<QuotationModel> select_customer=Cservice.select_customer();
		model.addAttribute("select_customer",select_customer);
		return "ShowCustomer";
		
	}
	
	@GetMapping("/delete_customer/{cdid}")
	public String delete_customer(@PathVariable("cdid") int cdid) {
		
		boolean b=Cservice.delete_customer(cdid);
		if(b) {
			return "redirect:/showCustomer";
		}
		else {
			return "ShowCustomer";
		}
		
	}
	
	@GetMapping("/customer_update/{cdid}")
	public String update_Costomer(@PathVariable("cdid") int cdid,Model model) { 
		
		QuotationModel updateDetails=Cservice.update_customer(cdid);
		updateDetails.setFlag(1);
		model.addAttribute("updateDetails", updateDetails);
		return "UpdateCustomerDetails";
	}

	@PostMapping("/update_Costomer_form") 
	public String updateCustomerDetails(Model model, @ModelAttribute("updateDetails")  @Validated QuotationModel Qmodl, //
  	        BindingResult result, //
  	        final RedirectAttributes redirectAttributes) {
		
		 if (result.hasErrors()) {
		        
		        return "UpdateCustomerDetails";
		 }
			 
		 int res=Cservice.update_customer(Qmodl);
		 if(res!=0) {
			 return "redirect:/showCustomer";
		 }
		 else {
			 return "UpdateCustomerDetails";
		 }
		
	}
	@GetMapping("/show_qtdetails/{cdid}")
	public String show_customer_quotation(@PathVariable("cdid") int cdid,Model model) {
		
		List<QuotationModel> allQuotation=Cservice.Quotation_Cdid(cdid);
		model.addAttribute("allQuotation",allQuotation);
		return "Quotataton_each_Customer";
		
	}
	
	@GetMapping("/Qouotaton/view/{cid}/{cdid}")
	public String customerQt_details(@PathVariable("cid") int cid,@PathVariable("cdid") int cdid ,Model model) {
		
		QuotationModel UpdateQDetails=Cservice.UpdateQuotation_Cid(cid);
		model.addAttribute("UpdateQDetails",UpdateQDetails);
		List<NoteModel> qtnnotes=Cservice.updatedNote(cid);
		model.addAttribute("qtnnotes",qtnnotes);
		List<ItemModel> qtitems=Cservice.UpdatedQtsItem(cid);
		int sum=Iservice.sumofprice(cid);
		int vat=sum*5/100;
		int vattotal=sum+vat;
		model.addAttribute("sum",sum);
		model.addAttribute("vat",vat);
		model.addAttribute("vattotal",vattotal);
		model.addAttribute("qtitems",qtitems);
		
		List<SubcategoryModel> allsubcat=QS.allsubcat();
		List<String> catname= QS.select_catneme();
		model.addAttribute("allitem", allsubcat);
		model.addAttribute("catname", catname);
		  
		return "UpdateGeneretedQuotation";
		
	}
	
	@RequestMapping(value = "/GeneretedUpdate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody QuotationModel UpdateGeneretedQtn(@RequestBody QuotationModel qm) {
		
		int res=Cservice.update_generetedQtn(qm);
		if(res!=0) {
			QuotationModel qmodel=new QuotationModel();
			qmodel.setValidity(qm.getValidity());
			qmodel.setDelivery(qm.getDelivery());
			qmodel.setWarranty(qm.getWarranty());
			qmodel.setPayment(qm.getPayment());
			qmodel.setCam_type(qm.getCam_type());
			return qmodel;
		}
		else {
			return null;
		}
		
	
	}
	
	 @RequestMapping(value = "/delete_updatedQtn", method = RequestMethod.GET)
	 public @ResponseBody int  deleteUpdated_note(@RequestParam Integer id,Integer cid)
	 {
	   // System.out.println("get is got");
		 System.out.println("-------------"+id+"-------------"+cid);
	    boolean b=QS.del_note(id,cid);
	    if(b)
	    	 return id;
	    else
	    	return 0;
	   
	 }
	
	 
	
	 
	
	 @RequestMapping(value = "/addUpdatednote", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	 public @ResponseBody NoteModel addUpdatednote(@RequestBody NoteModel qm) {
		 
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
			 Nmodel.setNid(Cservice.nid());
			 System.out.println("aaaaaa"+Cservice.nid());
			 return Nmodel;
		 }
		 else
		 {
			 return null;
		 }
	 
	 }
	 
	 @RequestMapping(value="CustomerQuotations",method=RequestMethod.GET)
	 public String allQuotations(Model model) {
		 
		 List<QuotationModel> allQuotation=Cservice.All_Quotations();
		 model.addAttribute("allQuotation",allQuotation);
		 return "Quotataton_each_Customer"; 
	 }

}
