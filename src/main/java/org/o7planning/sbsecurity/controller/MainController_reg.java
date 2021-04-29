package org.o7planning.sbsecurity.controller;




import java.util.List;

import org.o7planning.sbsecurity.dao.AppUser_reg_DAO;
import org.o7planning.sbsecurity.dao.CountryDAO;
import org.o7planning.sbsecurity.formbean.AppUserForm;
import org.o7planning.sbsecurity.model.AppUser_reg;
import org.o7planning.sbsecurity.model.Country;
import org.o7planning.sbsecurity.service.insertService;
import org.o7planning.sbsecurity.validator.AppUserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController_reg {

  @Autowired
  private AppUser_reg_DAO appUserDAO;

  @Autowired
  private CountryDAO countryDAO;

  @Autowired
  private AppUserValidator appUserValidator;
  
  @Autowired
  private insertService insertservice;

  // Set a form validator
  @InitBinder
  protected void initBinder(WebDataBinder dataBinder) {
     // Form target
     Object target = dataBinder.getTarget();
     if (target == null) {
        return;
     }
     System.out.println("Target=" + target);

     if (target.getClass() == AppUserForm.class) {
        dataBinder.setValidator(appUserValidator);
     }
     // ...
  }

  

  @RequestMapping("/members")
  public String viewMembers(Model model) {

     List<AppUser_reg> list = appUserDAO.getAppUsers();

     model.addAttribute("members", list);

     return "membersPage";
  }

  @RequestMapping("/registerSuccessful")
  public String viewRegisterSuccessful(Model model) {

     return "registerSuccessfulPage";
  }

  
  // Show Register page.
  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public String viewRegister(Model model) {

     AppUserForm form = new AppUserForm();
     List<Country> countries = countryDAO.getCountries();

     model.addAttribute("appUserForm", form);
     model.addAttribute("countries", countries);

     return "registerPage";
  }


  
  
  @RequestMapping(value = "/insert", method = RequestMethod.GET)
  public String insertpage(Model model) {

     AppUserForm form = new AppUserForm();
     List<Country> countries = countryDAO.getCountries();

     model.addAttribute("appUserForm", form);
     model.addAttribute("countries", countries);

     return "insertpage";
  
  }
  
  
  // This method is called to save the registration information.
  // @Validated: To ensure that this Form
  // has been Validated before this method is invoked.
  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public String saveRegister(Model model, //
        @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, //
        BindingResult result, //
        final RedirectAttributes redirectAttributes) {

     // Validate result
     if (result.hasErrors()) {
        List<Country> countries = countryDAO.getCountries();
        model.addAttribute("countries", countries);
        return "registerPage";
     }
     AppUser_reg newUser= null;
     try {
        newUser = appUserDAO.createAppUser(appUserForm);
     }
     // Other error!!
     catch (Exception e) {
        List<Country> countries = countryDAO.getCountries();
        model.addAttribute("countries", countries);
        model.addAttribute("errorMessage", "Error: " + e.getMessage());
        return "registerPage";
     }

     redirectAttributes.addFlashAttribute("flashUser", newUser);
      
     return "redirect:/registerSuccessful";
  }

  
  @RequestMapping(value = "/insert_form", method = RequestMethod.POST)
  public String Insert(AppUserForm app ,Model model, @ModelAttribute("appUserForm") @Validated AppUserForm appUserForm, //
	        BindingResult result, //
	        final RedirectAttributes redirectAttributes) {
	  
	  if (result.hasErrors()) {
	        List<Country> countries = countryDAO.getCountries();
	        model.addAttribute("countries", countries);
	        return "registerPage";
	     }
	  
	 // System.out.println(app.getEncrytedPassword());
	  try {
	 int a= insertservice.insert(app);
	 if(a!=0)
	 {
		 System.out.println("inserted------------");
		 model.addAttribute("message", "inserted------------");
	 }
	 else
	 {
		 
		 
		 model.addAttribute("message", "failed------------");

		 System.out.println("failed.................");
	 }
	 
	 return "insertmessage";
	 }
	  catch (Exception e) {
	        List<Country> countries = countryDAO.getCountries();
	        model.addAttribute("countries", countries);
	        model.addAttribute("errorMessage", "Error: " + e.getMessage());
	        return "registerPage";
	     }
     
  }
  
  
}