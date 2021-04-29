package org.o7planning.sbsecurity.controller;

import java.util.List;

import org.o7planning.sbsecurity.model.AllCountry;
import org.o7planning.sbsecurity.service.AllcountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AllCountryController {
	
	 private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);
	
	
	
	
	@Autowired
	private AllcountryService allser;
	

	  @RequestMapping("/showCountry")
	  public void viewMembers() {

	     List<AllCountry> list = allser.AllCountryname();
	     LOGGER.info("ajmal logger");
	     
	     for(AllCountry dept: list)  {
	            System.out.println("name: "+ dept.getName()+" - iso: "+ dept.getIso());
	        }
	     
	     

	    // model.addAttribute("members", list);

	   //  return "membersPage";
	  }
	  @RequestMapping("/autocamplete")
	  public String autocomplete() {
		  return "autocmplt";
	  }
	  @RequestMapping("/autocmplt")
	  public @ResponseBody List<AllCountry> autocmplt(@RequestParam String term) {
		  
		  List<AllCountry> list = allser.likecontry(term);
		  for(AllCountry dept: list)  {
	            System.out.println("name: "+ dept.getName()+" - iso: "+ dept.getIso());
	        }
		  return list;
	  }
	  
	  
	

}
