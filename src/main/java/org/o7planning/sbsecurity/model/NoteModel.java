package org.o7planning.sbsecurity.model;

public class NoteModel {
	
	private int cid;
	private int nid;
	private String note;
	
	
	
	public NoteModel(){}
	
	public NoteModel(int nid){
		
		this.nid=nid;
		
	}
	public NoteModel(int nid,int cid,String note){
		this.nid=nid;
		this.note=note;
		this.cid=cid;
		
	}
	
	
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public int getNid() {
		return nid;
	}
	public void setNid(int nid) {
		this.nid = nid;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}

}
