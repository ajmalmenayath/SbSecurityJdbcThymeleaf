package org.o7planning.sbsecurity.controller;

import java.util.List;

import org.o7planning.sbsecurity.model.CategoryModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.o7planning.sbsecurity.service.categoryService;
import org.o7planning.sbsecurity.validator.categoryValidator;
import org.o7planning.sbsecurity.validator.subcategoryValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CategoryController {


	
	
	@Autowired
	private categoryValidator catvalidator;
	
	@Autowired
	private subcategoryValidator subcatvalidator;
	
	
	@Autowired
	private categoryService catservice;

	
	// Set a form validator
	  @InitBinder
	  protected void initBinder(WebDataBinder dataBinder) {
	     // Form target
	     Object target = dataBinder.getTarget();
	     if (target == null) {
	        return;
	     }
	     System.out.println("Target=" + target);

	     if (target.getClass() == CategoryModel.class) {
	        dataBinder.setValidator(catvalidator);
	       
	     }

	     if (target.getClass() == SubcategoryModel.class) {
		     dataBinder.setValidator(subcatvalidator);
	       
	     }
	     
	     // ...
	     
	   
	  }
	
	
	
	@RequestMapping(value = "/addcat", method = RequestMethod.GET)
	public String item(Model model) {

	     CategoryModel form = new CategoryModel();
	     //List<Country> countries = countryDAO.getCountries();

	     model.addAttribute("catForm", form);
	    // model.addAttribute("countries", countries);

	     return "addcategory";
	  
	  }
	
	@RequestMapping(value = "/searchcat", method = RequestMethod.GET)
	public String showcategory(Model model) {

	     
		List<CategoryModel> showcat=catservice.showcat();
		
		model.addAttribute("showcat",showcat);
		for (CategoryModel categoryModel : showcat) {
			System.out.println(categoryModel.getCat_name());
			System.out.println(categoryModel.getDisc());
		}
		
		
		
	     return "showcategory";
	  
	  }
	
	
	
	
    
    @RequestMapping(value = "/addcat_form", method = RequestMethod.POST)
    public String Insertcat(Model model, @ModelAttribute("catForm")  @Validated CategoryModel catmodl, //
  	        BindingResult result, //
  	        final RedirectAttributes redirectAttributes) {
  	  
    	 if (result.hasErrors()) {
    	        
    	        return "addcategory";
    	     }
  	 
  	  try {
     int flag= catservice.insert_category(catmodl);
  	 if(flag!=0)
  	 {
  		 
  		 System.out.println("inserted------------");
  		 model.addAttribute("message", "New Category inserted");
  		 redirectAttributes.addFlashAttribute("flashUser", catmodl.getCat_name());
        
  	     return "redirect:/addcat";
  	 }
  	 else
  	 {
  		 model.addAttribute("message", "failed------------");
  		 System.out.println("failed.................");
  		 return "addcategory";
  		 
  	 }
  	     
  	 		
  	 }
  	  catch (Exception e) {
  	       
  	        model.addAttribute("errorMessage", "Error: " + e.getMessage());
  	        return "addcategory";
  	     }
       
    }
	
	
	
    @RequestMapping(value = "/catupdate", method = RequestMethod.POST)
	public String updatecategory(Model model,CategoryModel catmodel) {
    	int id=catmodel.getCat_id();
    	
    	CategoryModel result=catservice.cat_id(id);
    	result.setFlag(1);
    	System.out.println("================="+result.getCat_name()+result.getDisc());
    	model.addAttribute("catForm1",result);
		
		
	    return "categoryupdate";
	  
	  }

    
    @RequestMapping(value = "/catupdate_form", method = RequestMethod.POST)
  	public String updatecategory_form(Model model, @ModelAttribute("catForm1")  @Validated CategoryModel catmodl, //
	        BindingResult result, //
	        final RedirectAttributes redirectAttributes) {
	  // catmodl.setFlag(1);


    	
	 if (result.hasErrors()) {
	        
	        return "categoryupdate";
	     }
	 
	 
	 try {
	     int flag= catservice.update_category(catmodl);
	  	 if(flag!=0)
	  	 {
	  		 
	  		 System.out.println("inserted------------");
	  		 model.addAttribute("message", "New Category updated sucessfully");
	  		redirectAttributes.addFlashAttribute("flashUser", catmodl.getCat_name());
	        
	  	     return "redirect:/registerSuccessful";
	  	 }
	  	 else
	  	 {
	  		model.addAttribute("message", "failed------------");
	  		 System.out.println("failed.................");
	  		 return "categoryupdate";
	  		 
	  	 }
	  	     
	  	 		
	  	 }
	  	  catch (Exception e) {
	  	       
	  	        model.addAttribute("errorMessage", "Error: " + e.getMessage());
	  	        return "categoryupdate";
	  	     }
	 
	 
  	  
  	  }
    
    
  
    
 	    
     @GetMapping("/catupdate/{cat_id}")
     public String categoryupdate(@PathVariable("cat_id") int id,Model model) {
        
    		
    	
      		System.out.println("--------------id="+id); 
    	 
     	
     	CategoryModel result=catservice.cat_id(id);
     	result.setFlag(1);
     	System.out.println("================="+result.getCat_name()+result.getDisc());
     	model.addAttribute("catForm1",result);
 		
 		
 	    return "categoryupdate";
    	 
    	 
       
     }  
     
     
     @GetMapping("/catview/{cat_id}")
     public String categoryview(@PathVariable("cat_id") int id,Model model) {
        
    		
    	
      		System.out.println("--------------id="+id); 
    	 
     	
     	CategoryModel result=catservice.cat_id(id);
     	result.setFlag(1);
     	System.out.println("================="+result.getCat_name()+result.getDisc());
     	model.addAttribute("catForm1",result);
 		
 		
 	    return "categoryupdate";
    	 
    	 
       
     }  
     
     
     
 	    
     @GetMapping("/cat/delete/{cat_id}")
     public String deletecat(@PathVariable("cat_id") Integer cid){
      //   logger.info("Delete supplier pid="+pid);
    	 System.out.println(cid);
      //   providerDao.delete(pid);
    	     catservice.delete_subcategory_cid(cid);
    	  	 
    		 boolean b=catservice.delete_category(cid);
        	 if(b) {
             return "redirect:/searchcat";
        	 }
        	 else
        	 {
        		 System.out.println("delete failed");
        		 return "redirect:/searchcat";
        	 }
    	
    	 
     }
    
     @RequestMapping(value = "/add_subcat", method = RequestMethod.GET)
     public String add_subcat(Model model)
     {
    	 SubcategoryModel subform = new SubcategoryModel();
	     //List<Country> countries = countryDAO.getCountries();
    	 CategoryModel form=new CategoryModel();
    	 List<CategoryModel> cat_list=catservice.showcat();
    	 
    	 model.addAttribute("catForm",form);
	     model.addAttribute("sub_catForm", subform);
	     model.addAttribute("cat_list",cat_list);
	     
	     
	    
	    // model.addAttribute("countries", countries);

	    
    	 
    	 return "addsubcategory";
     }
     @RequestMapping(value = "/add_subcat_form", method = RequestMethod.POST)
   	public String addsubcategory_form(Model model, @ModelAttribute("sub_catForm")  @Validated SubcategoryModel catmodl, //
 	        BindingResult result, //
 	        final RedirectAttributes redirectAttributes) {
 	  // catmodl.setFlag(1);

      
     	
 	 if (result.hasErrors()) {
 		 
	 		List<CategoryModel> cat_list=catservice.showcat();
	   	    model.addAttribute("cat_list",cat_list);
	 	    return "addsubcategory";
 	     }
 	 
 	 
 	 try {
 		 
 		 	int f=catservice.insert_subcategory(catmodl);
 		 	if(f==0)
 		 	{
 		 		System.out.println("insertion failed");
 		 		return "addsubcategory";
 		 	}
 		 	else
 		 	{
 		 		/* System.out.println("inserted------------");
 		  		 model.addAttribute("message", "New Category updated sucessfully");
 		  		 redirectAttributes.addFlashAttribute("flashUser", catmodl.getCat_name());
 		       */ 
 		  	     return "redirect:/add_subcat";
 		 	}
 		 
 		 	
 	  	 		
 	  	 }
 	  catch (Exception e) {
 	  	       
 	  	       // model.addAttribute("errorMessage", "Error: " + e.getMessage());
 	  	        return "addsubcategory";
 	  	 }
 	 
 	 
   	  
   	  }
     
     @RequestMapping(value = "/searchsubcat",  method = RequestMethod.GET)
     public String showall_subcategory(Model model)
     {
    	 
    	
    	 List<SubcategoryModel> sublist=catservice.showsubcat();
    	 model.addAttribute("sublist",sublist);
    	 return "showsubcategory";
     }
     
     
     
     @GetMapping("/subcat/delete/{sub_id}")
     public String delete_subcat(@PathVariable("sub_id") Integer cid){
      //   logger.info("Delete supplier pid="+pid);
    	 System.out.println(cid);
      //   providerDao.delete(pid);
    	 
    	 boolean b=catservice.delete_subcategory(cid);
    	 if(b) {
         return "redirect:/searchsubcat";
    	 }
    	 else
    	 {
    		 System.out.println("delete failed");
    		 return "redirect:/searchsubcat";
    	 }
     }
     
     @GetMapping("/subcat/update/{sub_id}")
     public String update_subcat(@PathVariable("sub_id") Integer cid,Model model){
    	 
    	// SubcategoryModel subform = new SubcategoryModel();
	     //List<Country> countries = countryDAO.getCountries();
    	 //CategoryModel form=new CategoryModel();
    	 List<CategoryModel> cat_list=catservice.showcat();
    	 SubcategoryModel sub_id=catservice.sub_id(cid);
    	 sub_id.setFlag(1);
    	 
    	// model.addAttribute("catForm",form);
	     model.addAttribute("sub_catForm", sub_id);
	     model.addAttribute("cat_list",cat_list);
       
    	 return "updatesubcategory";
     }
     
     @RequestMapping(value = "/update_subcat_form", method = RequestMethod.POST)
     public String updatesubcategory_form(Model model, @ModelAttribute("sub_catForm")  @Validated SubcategoryModel catmodl, //
  	        BindingResult result, //
  	        final RedirectAttributes redirectAttributes) {
  	  // catmodl.setFlag(1);

       
      	
  	 if (result.hasErrors()) {
  		 
 	 		List<CategoryModel> cat_list=catservice.showcat();
 	   	    model.addAttribute("cat_list",cat_list);
 	 	    return "updatesubcategory";
  	     }
  	 
  	 
  	 try {
  		 
  		 	System.out.println(catmodl.getCat_id());
  		 	int f=catservice.update_subcategory(catmodl);
  		 	System.out.println(f);
  		 	System.out.println(catmodl.getCat_id());
  		 	if(f==0)
  		 	{
  		 		System.out.println("insertion failed");
  		 		return "updatesubcategory";
  		 	}
  		 	else
  		 	{
  		 		 System.out.println("inserted------------");
  		  		 model.addAttribute("message", "New Category updated sucessfully");
  		  		 redirectAttributes.addFlashAttribute("flashUser", catmodl.getCat_name());
  		        
  		  	     return "redirect:/searchsubcat";
  		 	}
  		 
  		 	
  	  	 		
  	  	 }
  	  catch (Exception e) {
  	  	       
  	  	       // model.addAttribute("errorMessage", "Error: " + e.getMessage());
  	  	        return "addsubcategory";
  	  	 }
  	 
  	 
    	  
    	  }
     
     
}
