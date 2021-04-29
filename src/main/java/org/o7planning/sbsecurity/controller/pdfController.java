package org.o7planning.sbsecurity.controller;


import org.o7planning.sbsecurity.controller.GeneratePdfReport;
import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.o7planning.sbsecurity.service.PdfService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.itextpdf.text.DocumentException;


@Controller
public class pdfController {
	
	
	@Autowired
	private PdfService pdfser;
	
	
	@RequestMapping(value = "/pdfreport/{cid}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> pdfreport(@PathVariable("cid") String cid) throws IOException, DocumentException {

		System.out.println("cid=============="+cid);
        int cid1=Integer.parseInt(cid);
        
        //Quotation details
        QuotationModel qtsDetauls=pdfser.QtsDetails(cid1);
        
        //Notes 
        List<NoteModel> QtsNotes=pdfser.QtsNote(cid1);
        
        //Quotation Items
        
        List<ItemModel> qtsItems=pdfser.QtsItem(cid1);
        
        
        ByteArrayInputStream bis = GeneratePdfReport.QtnItems(qtsDetauls,QtsNotes,qtsItems);
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
	

}
