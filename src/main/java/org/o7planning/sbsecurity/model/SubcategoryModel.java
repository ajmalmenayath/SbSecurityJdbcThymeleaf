package org.o7planning.sbsecurity.model;



public class SubcategoryModel {
	
	
	
	private int sub_id;
	private String sub_name;
	private int price;
	private int cat_id;
	private String disc;
	private String cat_name;
	private int flag;
	
	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public SubcategoryModel() {
		
	}
	
	public SubcategoryModel(int sub_id,String sub_name,int price,int cat_id,String disc,String catname)
	{
		super();
		this.cat_id=cat_id;
		this.sub_id=sub_id;
		this.sub_name=sub_name;
		this.price=price;
		this.disc=disc;
		this.cat_name=catname;
		
	}
	
	
	
	public String getCat_name() {
		return cat_name;
	}

	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}

	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public String getSub_name() {
		return sub_name;
	}
	public void setSub_name(String sub_name) {
		this.sub_name = sub_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}
	public String getDisc() {
		return disc;
	}
	public void setDisc(String disc) {
		this.disc = disc;
	}
	
	

}
