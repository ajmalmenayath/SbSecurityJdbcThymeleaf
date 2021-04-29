package org.o7planning.sbsecurity.model;


public class QuotationModel {

	
	private int cid;
	private String cname;
	private String cdate;
	private String validity;
	private String delivery;
	private String warranty;
	private String payment;
	private String cam_type;
	private int cdid;
	private String trnno;
	private String emirates;
	private String other;
	private String invorse_no;
	private String supply_date;
	private String lpo;

	
	
	public String getLpo() {
		return lpo;
	}



	public void setLpo(String lpo) {
		this.lpo = lpo;
	}



	public String getSupply_date() {
		return supply_date;
	}



	public void setSupply_date(String supply_date) {
		this.supply_date = supply_date;
	}
	private int flag;
	
	
	
   public String getInvorse_no() {
		return invorse_no;
	}



	public void setInvorse_no(String invorse_no) {
		this.invorse_no = invorse_no;
	}



	public int getFlag() {
		return flag;
	}



	public void setFlag(int flag) {
		this.flag = flag;
	}



public  QuotationModel(int cdid,String cname,String trnno,String emirates,String other) {
		
	this.cdid=cdid;
	this.cname=cname;
	this.trnno=trnno;
	this.emirates=emirates;
	this.other=other;
		
	}
   
   
	
	public int getCdid() {
		return cdid;
	}



	public void setCdid(int cdid) {
		this.cdid = cdid;
	}




	public String getTrnno() {
		return trnno;
	}



	public void setTrnno(String trnno) {
		this.trnno = trnno;
	}



	public String getEmirates() {
		return emirates;
	}



	public void setEmirates(String emirates) {
		this.emirates = emirates;
	}



	public String getOther() {
		return other;
	}



	public void setOther(String other) {
		this.other = other;
	}



	public  QuotationModel(int cid,String cname,String cdate,String validity,String delivery,String warranty,String payment,String cam_type,int cdid,String invorse_no,String supply_date,String lpo) {
		
		this.cid=cid;
		this.cname=cname;
		this.cdate=cdate;
		this.validity=validity;
		this.delivery=delivery;
		this.warranty=warranty;
		this.payment=payment;
		this.cam_type=cam_type;
		this.cdid=cdid;
		this.invorse_no=invorse_no;
		this.supply_date=supply_date;
		this.lpo=lpo;
	}
	

	

	public  QuotationModel(int cid,String cname) {
		
		this.cid=cid;
		this.cname=cname;
		
		
	}
	

	
	
	public String getCam_type() {
		return cam_type;
	}



	public void setCam_type(String cam_type) {
		this.cam_type = cam_type;
	}



	public QuotationModel() {
		// TODO Auto-generated constructor stub
	}
	public QuotationModel(int cid) {
		// TODO Auto-generated constructor stub
		this.cid=cid;
	}
	

	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	public String getValidity() {
		return validity;
	}
	public void setValidity(String validity) {
		this.validity = validity;
	}
	public String getDelivery() {
		return delivery;
	}
	public void setDelivery(String delivery) {
		this.delivery = delivery;
	}
	public String getWarranty() {
		return warranty;
	}
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	public String getPayment() {
		return payment;
	}
	public void setPayment(String payment) {
		this.payment = payment;
	}
	
	
	
	
}
