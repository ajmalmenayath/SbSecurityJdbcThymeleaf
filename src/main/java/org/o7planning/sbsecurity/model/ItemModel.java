package org.o7planning.sbsecurity.model;

public class ItemModel {

	
	private int itemid;
	private String item_name;
	private int count;
	private int price;
	private int total;
	private int cid;
	private int sub_id;
	private int sum;
	
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getSub_id() {
		return sub_id;
	}
	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}
	public ItemModel() 
	{
		//default
	}
	public ItemModel(int itemid,String item_name,int count,int price,int total,int cid,int sub_id) 
	{
		this.itemid=itemid;
		this.item_name=item_name;
		this.count=count;
		this.price=price;
		this.total=total;
		this.cid=cid;
		this.sub_id=sub_id;
	}
	
	
	
	public int getItemid() {
		return itemid;
	}
	public void setItemid(int itemid) {
		this.itemid = itemid;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	
}
