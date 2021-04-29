package org.o7planning.sbsecurity.controller;




import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.springframework.beans.factory.annotation.Autowired;


//header and footer
class HeaderAndFooterPdfPageEventHelper2 extends PdfPageEventHelper {
  public void onStartPage(PdfWriter pdfWriter, Document document) {
      System.out.println("onStartPage() method > Writing header in file");
      Rectangle rect = pdfWriter.getBoxSize("rectangle");
     
      Font font1 = null;
      Font font2=null;
	try {
		font1 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
		font1 =  FontFactory.getFont(FontFactory.TIMES, 16f, Font.BOLDITALIC,BaseColor.RED);
		font2 = new Font(BaseFont.createFont(BaseFont.COURIER_BOLD, BaseFont.CP1250, true));
	} catch (DocumentException | IOException e) { 
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
		font2.setColor(BaseColor.BLUE);
		font2.setSize(12);
		//font2.isBold();
		
		

		String text1 = "ALPHA BETA TECHNICAL SOLUTIONS ";
		String text2 = "A True company for the complete IT Solution ";
		Paragraph para1 = new Paragraph(text1, font1);
		Paragraph para2 = new Paragraph(text2, font2);
		
      
      // TOP LEFT
      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
                0, new Phrase(para1), rect.getLeft()-10,
               rect.getTop(), 0);
      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
              0, new Phrase(para2), rect.getLeft()-10,
             rect.getTop()-15, 0);
     
      /*
 
      // TOP MEDIUM
      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
               Element.ALIGN_CENTER, new Phrase("TOP MEDIUM"),
               rect.getRight() / 2, rect.getTop(), 0);
 
      // TOP RIGHT
      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
               Element.ALIGN_CENTER, new Phrase("TOP RIGHT"), rect.getRight(),
               rect.getTop(), 0);*/
  }
 
  public void onEndPage(PdfWriter pdfWriter, Document document) {
	  
	  
	  /*
	  String foot="Mob: + 971 553631768 , UAE E-mail: info@alphabeta.com Software & Web development, VOIP & PABX systems, fiber & Data structured cabling and " + 
	  		"Wireless network systems, CCTV & Security system, Access control & Time Attendance solutions, Vehicle Monitoring systems, Public Address System and " + 
	  		"Queue system . ";*/
	  
	  String foot1="Mob: + 971 553631768 , UAE E-mail: ";
	  String foot2="info@alphabeta.com ";
	  String foot3="Software & Web development, VOIP & PABX systems, fiber & Data structured cabling and" + 
	  		"Wireless network systems, CCTV & Security system, Access control & Time Attendance solutions, Vehicle Monitoring systems, Public Address System and " + 
	  		"Queue system . ";
	  
	  Font dataYELLOWFont = FontFactory.getFont("Garamond", 10, BaseColor.YELLOW);
	  dataYELLOWFont =  FontFactory.getFont(FontFactory.TIMES,10,Font.BOLDITALIC|Font.UNDERLINE,BaseColor.YELLOW);
	//  Font dataBlackFont = FontFactory.getFont("Garamond", 10, BaseColor.BLACK);
	  
	  
	 
	  
	  Font font1 = null;
	  font1 =  FontFactory.getFont(FontFactory.TIMES, 9f,BaseColor.RED);
	 //Paragraph para1 = new Paragraph(foot, font1);
	 //para1.add(new Chunk("info@alphabeta.com", dataYELLOWFont));
	  
	  
	  Chunk parag1 = new Chunk(foot1,font1);	
	  Chunk parag2 = new Chunk(foot2, dataYELLOWFont);
	  Chunk parag3 = new Chunk(foot3,font1);
	  Paragraph para1 = new Paragraph();
	  para1.add(new Chunk(parag1));
	  para1.add(new Chunk(parag2));
	  para1.add(new Chunk(parag3));
	 
	  
	 
	  
	  
	  Rectangle rect = pdfWriter.getBoxSize("rectangle");
      System.out.println("onEndPage() method > Writing footer in file");
     // Rectangle rect1 = pdfWriter.getBoxSize("rectangle");
      // BOTTOM LEFT
  //    ColumnText.showTextAligned(pdfWriter.getDirectContent(),10, new Phrase(para1), 20, rect.getBottom(), 0);
      ColumnText ct = new ColumnText(pdfWriter.getDirectContent());
      ct.setSimpleColumn(new Phrase(para1), 7, 20, rect.getRight()+40, 50, 10, 20);
    
     
      try {
		ct.go();
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      
      Font font2=null;

      String text2 = "A True company for the complete IT Solution ";
      try {
		font2 = new Font(BaseFont.createFont(BaseFont.COURIER_BOLD, BaseFont.CP1250, true));
	} catch (DocumentException | IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
      font2.setColor(BaseColor.BLUE);
	  font2.setSize(10);
	  Paragraph para2 = new Paragraph(text2, font2);
	  /*
      // BOTTOM MEDIUM
      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
               Element.ALIGN_CENTER, new Phrase("BOTTOM CENTER"),
               rect.getRight() / 2, rect.getBottom(), 0);*/
     
 
      // BOTTOM RIGHT
      ColumnText.showTextAligned(pdfWriter.getDirectContent(),
               Element.ALIGN_CENTER, new Phrase(para2),
               rect.getRight()-115, rect.getBottom()-20, 0);  
  }
}





public class GenerateDNpdf extends PdfPageEventHelper{

    public static ByteArrayInputStream QtnItems(QuotationModel qtsDetauls,List<NoteModel> QtsNotes,List<ItemModel> qtsItems) throws MalformedURLException, IOException, DocumentException {

    	Document document = new Document(PageSize.A4, 15, 5, 90, 36);
    	
    	//String imageFile = "E:/1.jpg";
    	
    	String str1="Delivery Note";
    	
    	Font font1 = FontFactory.getFont(FontFactory.TIMES);
    	
    	font1 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
		font1 =  FontFactory.getFont(FontFactory.TIMES, 18f,Font.BOLD,new BaseColor(0,0,128));
		
		Paragraph para2 = new Paragraph(str1, font1);
		
		para2.setAlignment(Element.ALIGN_CENTER);
		para2.setSpacingAfter(25f);
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM dd, yyyy");  
	    Date date = new Date();  
	    	
		String str3="Our Ref; #"+qtsDetauls.getInvorse_no()+", LPO NO "+qtsDetauls.getLpo()+", Dated: "+formatter.format(date);
		Font font3 = FontFactory.getFont(FontFactory.TIMES, Font.BOLD);
    	font3 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
    	font3 =  FontFactory.getFont(FontFactory.TIMES, 11f, Font.BOLDITALIC|Font.UNDERLINE,new BaseColor(0,0,128));
		Paragraph para4 = new Paragraph(str3, font3);
		para4.setSpacingAfter(5f);
		

		String str4="ATTENTION";
		String str5=": "+qtsDetauls.getCname();
		Font font4 = FontFactory.getFont(FontFactory.TIMES, Font.BOLDITALIC);
    	font4 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
    	font4 =  FontFactory.getFont(FontFactory.TIMES, 10f, Font.BOLD,new BaseColor(0,0,128));
		//Paragraph para5 = new Paragraph(str4, font4);
		//Paragraph para6 = new Paragraph(str5, font2);
		Chunk parag1 = new Chunk(str4, font4);
		
		Font font2 = FontFactory.getFont(FontFactory.TIMES, Font.BOLDITALIC);
    	font2 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
    	font2 =  FontFactory.getFont(FontFactory.TIMES, 10f, Font.BOLD|Font.UNDERLINE,new BaseColor(0,0,128));
		Chunk parag2_1 = new Chunk(str5, font2);
		Paragraph comb = new Paragraph();
        comb.add(new Chunk(parag1));
        comb.add("       ");
        comb.add(new Chunk(parag2_1));
        comb.setSpacingAfter(7f);
        
        
        String str6="SUBJECT";
		String str7=": "+qtsDetauls.getCam_type();
		Font font5 = FontFactory.getFont(FontFactory.TIMES,Font.BOLDITALIC);
    	font5 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
    	font5 =  FontFactory.getFont(FontFactory.TIMES, 10f, Font.BOLD,new BaseColor(0,0,128));
		//Paragraph para5 = new Paragraph(str4, font4);
		//Paragraph para6 = new Paragraph(str5, font2);
		
    	Chunk parag3 = new Chunk(str6, font5);
		Chunk parag4 = new Chunk(str7, font2);
		Paragraph comb1 = new Paragraph();
        comb1.add(new Chunk(parag3));
        comb1.add("            ");
        comb1.add(new Chunk(parag4));
        comb1.setSpacingAfter(12f);
       
		
    
		
		
        
      
		String strDateFormat = "dd/MM/yyyy";
		DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
		String formattedDate= dateFormat.format(date);
        
       
        
        
        
       
        
        String str20="Description";
        Font font20 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
    	font20 =  FontFactory.getFont(FontFactory.TIMES, 15f, Font.BOLD,new BaseColor(0,0,128));
		Paragraph para20 = new Paragraph(str20, font20);
		para20.setAlignment(Element.ALIGN_CENTER);
		
		para20.setSpacingBefore(12f);
		
		Font font9 = new Font(BaseFont.createFont(BaseFont.COURIER_OBLIQUE, BaseFont.CP1250, true));
		font9 =  FontFactory.getFont(FontFactory.TIMES,11f);
		
		Paragraph para6 = new Paragraph("Received the above goods in good condition",font9);
	    para6.setSpacingAfter(30f);
	        
	   
        
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        

        try {

        	
            
             	
          //  PdfWriter.getInstance(document, out);
         //   PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, out);
            
            Rectangle rectangle = new Rectangle(30, 30, 550, 800);
            pdfWriter.setBoxSize("rectangle", rectangle);
            HeaderAndFooterPdfPageEventHelper1 headerAndFooter = 
                                     new HeaderAndFooterPdfPageEventHelper1();
            
            pdfWriter.setPageEvent(headerAndFooter);
            pdfWriter.setPageEvent(new MyPdfPageEventHelper1());
           // PdfWriter.getInstance(document, out);
           // writer.open();
            
            
            // add header and footer
            
            document.open();
        	document.add(para2);
        	document.add(para4);
        	document.add(comb);
            document.add(comb1);
            document.add(para20);
            
        	PdfPTable table = new PdfPTable(3);
            table.setWidthPercentage(80);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(80f);
                       
            table.setWidths(new int[]{3, 15, 8});
            
            

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE,10);

            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("SL\r\n" + 
            		"NO", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("ITEM / DESCRIPTION", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            hcell = new PdfPCell(new Phrase("QTY/\r\n" + 
            		"UNIT", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

           

            int i=1;
           
            for (ItemModel items : qtsItems) {

                PdfPCell cell;

                cell = new PdfPCell(new Phrase(Integer.toString(i)));
                cell.setMinimumHeight(12f);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                i++;
                
                cell = new PdfPCell(new Phrase(items.getItem_name()));
                cell.setMinimumHeight(12f);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                table.addCell(cell);

                cell = new PdfPCell(new Phrase(Integer.toString(items.getCount())));
                cell.setMinimumHeight(12f);
                cell.setPaddingLeft(8);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
              
                //sets the number of rows per page
                if (i % 20 == 0)
                {
                    document.add(table);
                    table.deleteBodyRows();
                    document.newPage();
                    
                    hcell = new PdfPCell(new Phrase("SL\r\n" + 
                    		"NO", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);

                    hcell = new PdfPCell(new Phrase("ITEM / DESCRIPTION", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);
                    
                    hcell = new PdfPCell(new Phrase("QTY/\r\n" + 
                    		"UNIT", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);

                    
                }
               
            }
            
            
            
           
            
            
        	document.add(table);
        	        	
        	
            document.add(para6);
            
            PdfPTable table4 = new PdfPTable(2);
        	table4.setWidthPercentage(100);
            table4.setSpacingBefore(3);
                       
            table4.setWidths(new int[]{3,5});
            PdfPCell hcell1;
            Font font21 = FontFactory.getFont(FontFactory.TIMES);
            font21 =  FontFactory.getFont(FontFactory.TIMES, 11f);
            hcell1 = new PdfPCell(new Phrase("Received by ……………………………",font21));
            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell1.setMinimumHeight(30f);
            table4.addCell(hcell1).setBorder(0);
        	
          
            hcell1 = new PdfPCell(new Phrase("Signature ……………………………",font21));
            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell1.setMinimumHeight(30f);
            table4.addCell(hcell1).setBorder(0);
            
            hcell1 = new PdfPCell(new Phrase("Designation ……………………………",font21));
            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell1.setMinimumHeight(30f);
            table4.addCell(hcell1).setBorder(0);
            
            hcell1 = new PdfPCell(new Phrase("Date ……………………………",font21));
            hcell1.setHorizontalAlignment(Element.ALIGN_LEFT);
            hcell1.setMinimumHeight(30f);
            table4.addCell(hcell1).setBorder(0);
            document.add(table4);
            
          
            document.addTitle(qtsDetauls.getCname()+"_DN_"+formattedDate);

            
            document.close();
            //writer.close();
        } catch (DocumentException ex) {
        
            Logger.getLogger(GeneratePdfReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
    
    
    
    
    
}



//watermark image

class MyPdfPageEventHelper2 extends PdfPageEventHelper {
	 
    @Autowired
	@Override
    public void onEndPage(PdfWriter pdfWriter, Document document) {
 
           System.out.println("Creating Waterwark Image in PDF");
           
           try {
                  
                  //Use this method if you want to get image from your Local system
                  //Image waterMarkImage = Image.getInstance("E:/tiger.jpg");
                  
                  String urlOfWaterMarKImage = "E:/abt.PNG";
                  
                  //Get waterMarkImage from some URL
                  Image waterMarkImage = Image.getInstance(urlOfWaterMarKImage);
                  // waterMarkImage.setTransparency(new int[] {});
                  
                  //System.out.println("//////////////////++++++++watermark"+waterMarkImage.getColorTransform());
                  
                  //Get width and height of whole page
                 // float pdfPageWidth = document.getPageSize().getWidth();
                 // float pdfPageHeight = document.getPageSize().getHeight();
 
                  //Set waterMarkImage on whole page
                  float width = document.getPageSize().getWidth();
                  float height = document.getPageSize().getHeight();
                  System.out.println(width);
                  System.out.println(height);

                  pdfWriter.getDirectContentUnder().addImage(waterMarkImage,
                		  width+100, 0, 0, height-50, 200, 370);
 
           }catch(Exception e){
                  e.printStackTrace();
           }
    }
}