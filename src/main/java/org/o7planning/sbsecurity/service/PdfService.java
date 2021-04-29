package org.o7planning.sbsecurity.service;

import java.util.List;

import org.o7planning.sbsecurity.dao.PdfDao;
import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class PdfService {

	@Autowired
	private PdfDao pdf;
	
	public QuotationModel QtsDetails(int cid) {
		return pdf.QtsDetails(cid);
	}
	public List<NoteModel> QtsNote(int cid){
		return pdf.QtsNote(cid);
	}
	public List<ItemModel> QtsItem(int cid){
		return pdf.QtsItem(cid);
	}
	
}
