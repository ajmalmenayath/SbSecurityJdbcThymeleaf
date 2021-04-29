package org.o7planning.sbsecurity.model;

public class CategoryModel {
	

	private int cat_id;
	private String cat_name;
	private String disc;
	private int flag;
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public String getDisc() {
		return disc;
	}

	public void setDisc(String disc) {
		this.disc = disc;
	}

	public CategoryModel() {
		
	}
	
	public CategoryModel(int cat_id,String cat_name,String disc) {
		
		
		this.cat_id=cat_id;
		this.cat_name=cat_name;
		this.disc=disc;
		
	}
	
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getCat_name() {
		return cat_name;
	}
	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

}
