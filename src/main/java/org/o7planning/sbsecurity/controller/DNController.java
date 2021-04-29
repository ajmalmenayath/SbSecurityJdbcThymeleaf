package org.o7planning.sbsecurity.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.o7planning.sbsecurity.service.CustomerService;
import org.o7planning.sbsecurity.service.PdfService;
import org.o7planning.sbsecurity.service.QuotationService;
import org.o7planning.sbsecurity.service.TaxInvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itextpdf.text.DocumentException;

@Controller

public class DNController {
	
	@Autowired
	private PdfService pdfser;
	
	@Autowired
	private TaxInvoiceService Tservice;
	
	@Autowired
	private CustomerService Cservice;	
	
	@Autowired
	private QuotationService QS;
	@RequestMapping(value = "/DNpdfreport/{cid}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
	public  ResponseEntity<InputStreamResource> customerQt_details(@PathVariable("cid") Integer cid) throws MalformedURLException, IOException, DocumentException {
			   //Quotation details
		
		
    QuotationModel qtsDetauls=pdfser.QtsDetails(cid);
    
    //customer details
    QuotationModel CustomerDetails=Tservice.CustomerDetails(qtsDetauls.getCdid());
    

    qtsDetauls.setTrnno(CustomerDetails.getTrnno());
    qtsDetauls.setEmirates(CustomerDetails.getEmirates());
    
    //Notes 
    List<NoteModel> QtsNotes=pdfser.QtsNote(cid);
    

    
    //Quotation Items
    
    List<ItemModel> qtsItems=pdfser.QtsItem(cid);
    
    
    ByteArrayInputStream bis = GenerateDNpdf.QtnItems(qtsDetauls,QtsNotes,qtsItems);
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");  
    LocalDateTime now = LocalDateTime.now(); 

    HttpHeaders headers = new HttpHeaders(); 
    headers.add("Content-Disposition", "inline; filename="+qtsDetauls.getCname()+"_"+dtf.format(now)+".pdf");

    return ResponseEntity
            .ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
	}
	@GetMapping("DN/generate/{cid}/{cdid}")
	public String customerQt_details(@PathVariable("cid") int cid,@PathVariable("cdid") int cdid ,Model model) {
		

		QuotationModel UpdateQDetails=Cservice.UpdateQuotation_Cid(cid);
		model.addAttribute("UpdateQDetails",UpdateQDetails);
		List<NoteModel> qtnnotes=Cservice.updatedNote(cid);
		model.addAttribute("qtnnotes",qtnnotes);
		List<ItemModel> qtitems=Cservice.UpdatedQtsItem(cid);
		model.addAttribute("qtitems",qtitems);
		
		List<SubcategoryModel> allsubcat=QS.allsubcat();
		List<String> catname= QS.select_catneme();
		model.addAttribute("allitem", allsubcat);
		model.addAttribute("catname", catname);
		  
		  
		return "GenerateDN";
		
	}
	@GetMapping("/Insertlpo")
	public @ResponseBody int insert_lpo(@RequestParam Integer cid,String lpo) {
		
			int res=Tservice.update_lpo(cid, lpo);
			if(res!=0) {
				return cid;
			}
			else {
				return 0;

			}
		
	}

}
