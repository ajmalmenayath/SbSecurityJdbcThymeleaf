package org.o7planning.sbsecurity.dao;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public class CustomerDao extends JdbcDaoSupport{
	@Autowired
    public CustomerDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	 
	 public int customerDetails_insert(QuotationModel qm){
		 
		 String in_sql="insert into qms.customer_details (cdname,trnno,emirates,other) values(?,?,?,?)";
	 	
		    return this.getJdbcTemplate().update(in_sql, new Object[] {
		    
		    		qm.getCname(),
		    		qm.getTrnno(),
		    		qm.getEmirates(),
		    		qm.getOther()
	        
			});
		 
	 }
	 
	 public List<QuotationModel> select_customer() {
		 
		 String sql = "SELECT * FROM qms.customer_details ";

		  
		 return this.getJdbcTemplate().query(
		            sql,
		            (rs, rowNum) ->
		                    new QuotationModel(
		                            
		                    		
		                    		rs.getInt("cdid"),
		                    		rs.getString("cdname"),
		                    		rs.getString("trnno"),
		                    		rs.getString("emirates"),
		                    		rs.getString("other")
		                            
		                    )
		    );
		 
	 }
	 
	 public boolean delete_customer(int cdid) {
		 String sql="DELETE FROM qms.customer_details where cdid=?";
		 Object[] params= {cdid};
		 return this.getJdbcTemplate().update(sql, params) == 1;
	 }
	 
	 public QuotationModel update_customer(int cdid) {

		    String sql = "SELECT * FROM qms.customer_details WHERE cdid=?";

		    return this.getJdbcTemplate().queryForObject(sql, new Object[]{cdid}, (rs, rowNum) ->
		    new QuotationModel(
                 
         		 rs.getInt("cdid"),
                 rs.getString("cdname"),
                 rs.getString("trnno"),
                 rs.getString("emirates"),
                 rs.getString("other")
                 
         ));

	 }
	 
	 public int update_customer(QuotationModel qmodel) {
		 String sql="UPDATE qms.customer_details SET cdname=?,trnno=?,emirates=?,other=? where cdid=?";
		 Object[] params= {qmodel.getCname(),qmodel.getTrnno(),qmodel.getEmirates(),qmodel.getOther(),qmodel.getCdid()};
		 int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

		 return this.getJdbcTemplate().update(sql, params, types);
	 }
	 
	 public List<QuotationModel> Quotation_Cdid(int cdid) {
		 String sql = "SELECT * FROM qms.customer where cdid=? ";
		 return this.getJdbcTemplate().query(
		            sql,new Object[]{cdid},
		            (rs, rowNum) ->
		                    new QuotationModel(
		                            
		                    		
		                    		rs.getInt("cid"),
		                    		rs.getString("cname"),
		                    		rs.getString("cdate"),
		                    		rs.getString("validity"),
		                    		rs.getString("delivery"),
		                    		rs.getString("warranty"),
		                    		rs.getString("payment"),
		                    		rs.getString("cam_type"),
		                    		rs.getInt("cdid"),
		                    		rs.getString("invorse_no"),
		                    		rs.getString("supply_date"),
		                    		rs.getString("lpo")
		                    		


		                            
		                    )
		    );
	 }
	 
	 public QuotationModel UpdateQuotation_Cid(int cid) {
		 String sql = "SELECT * FROM qms.customer where cid=? ";
		 return this.getJdbcTemplate().queryForObject(
		            sql,new Object[]{cid},
		            (rs, rowNum) ->
		                    new QuotationModel(
		                            
		                    		
		                    		rs.getInt("cid"),
		                    		rs.getString("cname"),
		                    		rs.getString("cdate"),
		                    		rs.getString("validity"),
		                    		rs.getString("delivery"),
		                    		rs.getString("warranty"),
		                    		rs.getString("payment"),
		                    		rs.getString("cam_type"),
		                    		rs.getInt("cdid"),
		                    		rs.getString("invorse_no"),
		                    		rs.getString("supply_date"),
		                    		rs.getString("lpo")


		                            
		                    )
		    );
	 }
	 public int update_generetedQtn(QuotationModel qmodel) {
		 String sql="UPDATE qms.customer SET cname=?,validity=?,delivery=?,warranty=?,payment=?,cam_type=? where cid=?";
		 Object[] params= {qmodel.getCname(),qmodel.getValidity(),qmodel.getDelivery(),qmodel.getWarranty(),qmodel.getPayment(),qmodel.getCam_type(),qmodel.getCid()};
		 int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

		 return this.getJdbcTemplate().update(sql, params, types);
	 }
	 
	 public List<NoteModel> updatedNote(int cid) {
		 String sql = "SELECT * FROM qms.notes where cid=? ";
		 return this.getJdbcTemplate().query(
		            sql,new Object[]{cid},
		            (rs, rowNum) ->
		                    new NoteModel(
		                          	
		                    		rs.getInt("nid"),
		                    		rs.getInt("cid"),
		                    		rs.getString("note")
		                    		
		                            
		                    )
		    );
	 }
	 public List<ItemModel> UpdatedQtsItem(int cid) {
			
			String sql="SELECT * FROM qms.qtitems WHERE cid = ?";
			return this.getJdbcTemplate().query(sql, new Object[] {cid},
					(rs, rowNum) ->new ItemModel(
							rs.getInt("itemid"),
							rs.getString("item_name"),
							rs.getInt("count"),
							rs.getInt("price"),
							rs.getInt("total"),
							rs.getInt("cid"),
							rs.getInt("sub_id")
							
							)
					
					);
			
 }
	
	 public int nid()
		{
			
			  String sql = "SELECT * FROM qms.notes ";

			  
			  List<NoteModel> li= this.getJdbcTemplate().query(
			            sql,
			            (rs, rowNum) ->
			                    new NoteModel(
			                            
			                    		rs.getInt("nid")
			                            
			                    )
			    );
			    
			    
			    int id=0;
			    for (NoteModel notemodel : li) {
			    	 id=notemodel.getNid();
				}
			 
			  return id;
		}
	 
	 public List<QuotationModel> All_Quotations() {
		 String sql = "SELECT * FROM qms.customer";
		 return this.getJdbcTemplate().query(
		            sql,
		            (rs, rowNum) ->
		                    new QuotationModel(
		                            
		                    		
		                    		rs.getInt("cid"),
		                    		rs.getString("cname"),
		                    		rs.getString("cdate"),
		                    		rs.getString("validity"),
		                    		rs.getString("delivery"),
		                    		rs.getString("warranty"),
		                    		rs.getString("payment"),
		                    		rs.getString("cam_type"),
		                    		rs.getInt("cdid"),
		                    		rs.getString("invorse_no"),
		                    		rs.getString("supply_date"),
		                    		rs.getString("lpo")


		                            
		                    )
		    );
	 }
	 
			 
	 
}
