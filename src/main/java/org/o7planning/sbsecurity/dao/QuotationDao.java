package org.o7planning.sbsecurity.dao;

import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.model.ItemModel;
import org.o7planning.sbsecurity.model.NoteModel;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class QuotationDao extends JdbcDaoSupport {

	@Autowired
    public QuotationDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	 public int insert_quotation(QuotationModel Qmodel)
	    {
		 
		  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		 Date date = new Date();
		 
		 Qmodel.setSupply_date(" ");
		 Qmodel.setLpo(" ");
	 	    String in_sql="insert into customer (cname,cdate,validity,delivery,warranty,payment,cam_type,cdid,supply_date,lpo) values(?,?,?,?,?,?,?,?,?,?)";
	    	
	 	    return this.getJdbcTemplate().update(in_sql, new Object[] {
	 	    
	 	    		Qmodel.getCname(),
	 	    		dateFormat.format(date),
	 	    		Qmodel.getValidity(),
	 	    		Qmodel.getDelivery(),
	 	    		Qmodel.getWarranty(),
	 	    		Qmodel.getPayment(),
	 	    		Qmodel.getCam_type(),
	 	    		Qmodel.getCdid(),
	 	    		Qmodel.getSupply_date(),
	 	    		Qmodel.getLpo()
	 	    		
	           
	 		});
	 	 	
	    } 
	 
	
	public int cid()
	{
		
		  String sql = "SELECT cid FROM qms.customer ";

		  
		  List<QuotationModel> li= this.getJdbcTemplate().query(
		            sql,
		            (rs, rowNum) ->
		                    new QuotationModel(
		                            
		                    		rs.getInt("cid")
		                            
		                    )
		    );
		    
		    
		    int id=0;
		    for (QuotationModel quotationModel : li) {
		    	 id=quotationModel.getCid();
			}
		 
		  return id;
	}
	
	public int insert_notes(NoteModel Nmodel)
    {
	 
	 
	 
 	    String in_sql="insert into qms.notes (cid,note) values(?,?)";
    	
 	    return this.getJdbcTemplate().update(in_sql, new Object[] {
 	    
 	    		Nmodel.getCid(),
 	    		Nmodel.getNote()
           
 		});
 	 	
    } 
	public int nid()
	{
		
		  String sql = "SELECT nid FROM qms.notes ";

		  
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
	
	 public boolean delete_note(int id,int cid)
	 {
	    
	 	  String sql="DELETE FROM notes WHERE nid = ? and cid=?";
          Object[] params = {id,cid};
	 	  return this.getJdbcTemplate().update(sql, params) == 1;

	    
	  } 
	 public List<SubcategoryModel> allsubcat() {

		    String sql = "SELECT * FROM subcategory";

		    return this.getJdbcTemplate().query(
		            sql,
		            (rs, rowNum) ->
		                    new SubcategoryModel(
		                            
		                    		rs.getInt("sub_id"),
		                            rs.getString("sub_name"),
		                            rs.getInt("price"),
		                            rs.getInt("cat_id"),
		                            rs.getString("disc"),
		                            rs.getString("cat_name")
		                            
		                    )
		    );
	}
	 public List<SubcategoryModel> subcat_cat_name(String cat_name) {

		    String sql = "SELECT * FROM subcategory where cat_name=?";

		    return this.getJdbcTemplate().query(
		            sql,new Object[]{cat_name},
		            (rs, rowNum) ->
		                    new SubcategoryModel(
		                            
		                    		rs.getInt("sub_id"),
		                            rs.getString("sub_name"),
		                            rs.getInt("price"),
		                            rs.getInt("cat_id"),
		                            rs.getString("disc"),
		                            rs.getString("cat_name")
		                            
		                    )
		    );
	}
	 public List<String> select_catneme() {

		    String sql = "SELECT cat_name FROM category";

		    return  this.getJdbcTemplate().queryForList(sql, String.class);
	}
	 
	 public int updateQTdetails(QuotationModel QM)
	 {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		 Date date = new Date();
		 String sql="UPDATE qms.customer SET cname = ?, cdate = ?, validity = ?, delivery = ?, warranty = ?, payment= ?,cam_type=?   WHERE cid = ?";


	        Object[] params = {QM.getCname(),dateFormat.format(date),QM.getValidity(),QM.getDelivery(),QM.getWarranty(),QM.getPayment(),QM.getCam_type(),QM.getCid() };
	        int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
	 }
	 
	 public int updateQTdetails_cdid(QuotationModel QM)
	 {
		 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		 Date date = new Date();
		 String sql="UPDATE qms.customer SET cname = ?, cdate = ?, validity = ?, delivery = ?, warranty = ?, payment= ?,cam_type=?,cdid=?   WHERE cid = ?";


	        Object[] params = {QM.getCname(),dateFormat.format(date),QM.getValidity(),QM.getDelivery(),QM.getWarranty(),QM.getPayment(),QM.getCam_type(),QM.getCid(),QM.getCdid() };
	        int[] types = {Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR,Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER, Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
	 }
	 
	
	 public SubcategoryModel subcat_subid(int id) {

		    String sql = "SELECT * FROM qms.subcategory WHERE sub_id = ?";

		    return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
		    new SubcategoryModel(
              
      		 rs.getInt("sub_id"),
              rs.getString("sub_name"),
              rs.getInt("price"),
              rs.getInt("cat_id"),
              rs.getString("disc"),
              rs.getString("cat_name")

              
      ));

	}
	 
	 public boolean delete_discard(int cid)
	 {
		 String sql="DELETE FROM qms.notes WHERE cid=?";
		 boolean b3=false;
	    
	 	 // String sql="DELETE FROM qms.notes,qms.qtitems,qms.customer WHERE cid=?";
         Object[] params = {cid};
	 	 this.getJdbcTemplate().update(sql, params);
	 	 
	 		 String sql2="DELETE FROM qms.qtitems WHERE cid=?";
	 	    
		 	 // String sql="DELETE FROM qms.notes,qms.qtitems,qms.customer WHERE cid=?";
	         Object[] params1 = {cid};
		 	 this.getJdbcTemplate().update(sql2, params1);
		 	
		 		 String sql3="DELETE FROM qms.customer WHERE cid=?";
			 	    
			 	 // String sql="DELETE FROM qms.notes,qms.qtitems,qms.customer WHERE cid=?";
		         Object[] params2= {cid};
			 	 b3=this.getJdbcTemplate().update(sql3, params2) == 1;
		 		  
		 
	 	 
	 	 return b3;
	    
	  }  
	 
 public List<QuotationModel> likecname(String name){
		 
		 
		 String sql = "select * from qms.customer_details where cdname like ?";
		 return this.getJdbcTemplate().query(
		            sql, new Object[]{ "%" + name + "%"},
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
 
 
 public int customerDetails_insert(QuotationModel qm){
	 
	 String in_sql="insert into qms.customer_details (cdname,trnno,emirates) values(?,?,?)";
 	
	    return this.getJdbcTemplate().update(in_sql, new Object[] {
	    
	    		qm.getCname(),
	    		qm.getTrnno(),
	    		qm.getEmirates()
        
		});
	 
 }
 public int CD_select_cname(String cname,String trn){
	 String sql = "SELECT cdid FROM qms.customer_details WHERE cdname = ? or trnno= ?";
	 Object[] params = new Object[] {cname,trn};
	 
	 try {
		 int cdid = this.getJdbcTemplate().queryForObject(sql, params,Integer.class);
            return cdid;
        } catch (EmptyResultDataAccessException e) {
            return 0;
        }
	
	 
	 
	 
 }
	 
 public int cdid(){
		
		  String sql = "SELECT * FROM qms.customer_details ";

		  
		  List<QuotationModel> li= this.getJdbcTemplate().query(
		            sql,
		            (rs, rowNum) ->
		                    new QuotationModel(
		                            
		                    		
		                    		rs.getInt("cdid")
		                            
		                    )
		    );
		    
		    
		    int id=0;
		    for (QuotationModel quotationModel : li) {
		    	 id=quotationModel.getCid();
			}
		 
		  return id;
	}
 
    public String getcustomer_name(String cname) {
		
		String sql="select cdname from qms.customer_details where cdname=?";
		
		Object[] params = new Object[] {cname};
		 try {
			 String name = this.getJdbcTemplate().queryForObject(sql, params,String.class);
	            return name;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
		
		
	}
    
    public String getcustomer_trno(String trnno) {
		
		String sql="select trnno from qms.customer_details where trnno=?";
		
		Object[] params = new Object[] {trnno};
		 try {
			 String name = this.getJdbcTemplate().queryForObject(sql, params,String.class);
	            return name;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
		
		
	}
    public String getNotCname(int cdid,String cdname) {
        String sql="select cdname from qms.customer_details where cdname=? and NOT cdid=?";
		
		Object[] params = new Object[] {cdname,cdid};
		 try {
			 String name = this.getJdbcTemplate().queryForObject(sql, params,String.class);
	            return name;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
    	
    }
    
    ////////////////////////
    
    public int CountOfmanual() {
    	
    	String sql = "SELECT COUNT(*) FROM qms.category WHERE cat_name='manual'";

	    return this.getJdbcTemplate().queryForObject(sql, Integer.class);
    	
    }
    
    public int insert_Manualcat() {
    	
 	    String in_sql="insert into qms.category (cat_name) values('manual')";
    	
 	    return this.getJdbcTemplate().update(in_sql);
 	 	
    } 
    public int selectManualId() {
    	String sql="select cat_id from qms.category where cat_name='manual'";
    	int cat_id= this.getJdbcTemplate().queryForObject(sql,Integer.class);
        return cat_id;
    }
    
    public int insertManual_subcategory(SubcategoryModel subcat)
	{
	    	
	 	    String in_sql="insert into qms.subcategory (sub_name,price,cat_id,disc,cat_name) values(?,?,?,?,?)";
	    	
	 	    return this.getJdbcTemplate().update(in_sql, new Object[] {
	 	    
	 	    		subcat.getSub_name(),
	 	    		subcat.getPrice(),
	 	    		subcat.getCat_id(),
	 	    		subcat.getDisc(),
	 	    		"manual"
	 	    		
	           
	 		});
	 	 	
	  } 
    public int selectManualSubid(String subname) {
    	String sql="select sub_id from qms.subcategory where sub_name=?";
    	Object[] params = new Object[] {subname};
    	int cat_id= this.getJdbcTemplate().queryForObject(sql,params,Integer.class);
        return cat_id;
    }
    
    public int insertManual_Item(ItemModel Imodel)
	 {
		 
		  
	 	   String in_sql="insert into  qms.qtitems (item_name,count,price,total,cid,sub_id) values(?,?,?,?,?,?)";
	    	
	 	   return this.getJdbcTemplate().update(in_sql, new Object[] {
	 	    
	 	    	Imodel.getItem_name(),
	 	    	Imodel.getCount(),
	 	    	Imodel.getPrice(),
	 	    	Imodel.getTotal(),
	 	    	Imodel.getCid(),
	 	    	Imodel.getSub_id()
	           
	 	});
	 	 	
	 } 
	 
    public List<SubcategoryModel> likemname(String name){
		 
		 
		 String sql = "select * from qms.subcategory where sub_name like ?";
		 return this.getJdbcTemplate().query(
		            sql, new Object[]{ "%" + name + "%"},
		            (rs, rowNum) ->
		                    new SubcategoryModel(
		                    		 rs.getInt("sub_id"),
		                    		 rs.getString("sub_name"),
			                         rs.getInt("price"),
			                         rs.getInt("cat_id"),
			                         rs.getString("disc"),
			                         rs.getString("cat_name")
		                           
			                        
		                    )
		    );
	 }
    
    public int insertinvorse_no(String invorse_no,int cid) {
		
		 String sql="UPDATE qms.customer SET invorse_no = ? WHERE cid = ?";


	        Object[] params = {invorse_no,cid};
	        int[] types = {Types.VARCHAR,Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
	 }


	
	 
	 
	
}
