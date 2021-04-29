package org.o7planning.sbsecurity.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.o7planning.sbsecurity.service.CustomerService;
import org.o7planning.sbsecurity.service.ItemService;
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

public class TaxInvoiceController {
	
	@Autowired
	private TaxInvoiceService Tservice;
	

	@Autowired
	private PdfService pdfser;
	
	@Autowired
	private CustomerService Cservice;
	
	@Autowired
	private QuotationService QS;
	
	@Autowired
	private ItemService Iservice;
	
	
	@RequestMapping(value = "/TaxInvoicepdfreport/{cid}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfreport(@PathVariable("cid") Integer cid) throws IOException, DocumentException, ParseException {

		
        
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
        
        
        ByteArrayInputStream bis = GenerateTaxInversePDF.QtnItems(qtsDetauls,QtsNotes,qtsItems);
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
	
	@GetMapping("taxinvoice/generate/{cid}/{cdid}")
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
		  
		return "GenereteTaxInvoice";
		
	}
	@GetMapping("/Insertsupply_dt")
	public @ResponseBody int insert_supplydate(@RequestParam Integer cid,String supply_dt) {
		
			int res=Tservice.update_supplydate(cid, supply_dt);
			if(res!=0) {
				return cid;
			}
			else {
				return 0;

			}
		
	}
	
	
	

}
