package org.o7planning.sbsecurity.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public class ItemDAO  extends JdbcDaoSupport{
	
	@Autowired
    public ItemDAO(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	 public int insert_Item(ItemModel Imodel)
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
	 public ItemModel select_subid_item(int subid, int cid)
	 {
		 String sql="select * from qms.qtitems where sub_id=? and cid=?";
		 Object[] param= {subid,cid};
		 return this.getJdbcTemplate().queryForObject(
		            sql,param,
		            (rs, rowNum) ->
		                    new ItemModel(
		                            
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
	 public int updateCount(ItemModel imodel)
	 {
		 
		 String sql="UPDATE qms.qtitems SET count = ?, total = ? WHERE sub_id = ? and cid=?";


	        Object[] params = {imodel.getCount(),imodel.getTotal(),imodel.getSub_id(),imodel.getCid()};
	        int[] types = {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
	 }
	 
	 public int updateManualCount(ItemModel imodel)
	 {
		 
		 String sql="UPDATE qms.qtitems SET count = ?, price=?, total = ? WHERE sub_id = ? and cid=?";


	        Object[] params = {imodel.getCount(),imodel.getPrice(),imodel.getTotal(),imodel.getSub_id(),imodel.getCid()};
	        int[] types = {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
	 }
	 
	 
	 
	 public int count_subid(int subid,int cid) {
	    	
	    	String sql1 = "SELECT COUNT(*) FROM qms.qtitems WHERE sub_id = ? and cid=?";
	    	Object[] params = {subid,cid};
			int numOf = this.getJdbcTemplate().queryForObject(sql1,params,Integer.class);
			System.out.println("----------------/////////"+numOf);
			return numOf;
			
		}
	 
	 public boolean delete_item(int subid,int cid)
	 {
	    
	 	  String sql="DELETE FROM qms.qtitems WHERE sub_id=? and cid = ?";
          Object[] params = {subid,cid};
	 	  return this.getJdbcTemplate().update(sql, params) == 1;

	    
	  } 
	 
	 public int update_Item(ItemModel Imodel)
	 {
		 
		  
	 	   String sql="UPDATE qms.qtitems SET price=?, count = ?, total = ? WHERE sub_id = ? and cid=?";
	 	   

	       Object[] params = {Imodel.getPrice(),Imodel.getCount(),Imodel.getTotal(),Imodel.getSub_id(),Imodel.getCid()};
	       int[] types = {Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.INTEGER};
	    	
	       return this.getJdbcTemplate().update(sql, params, types);
	 	 	
	 } 
	 
	 
	 public int sumofprice(int cid)
	 {
		 String sql1 = "SELECT COUNT(*) FROM qms.qtitems WHERE cid=?";
	    	Object[] params = {cid};
			int numOf = this.getJdbcTemplate().queryForObject(sql1,params,Integer.class);
		 
			if(numOf >0) {
				String sql="select SUM(total) from qms.qtitems where cid=?";
				 Object[] param= {cid};
				 return this.getJdbcTemplate().queryForObject(sql,param,Integer.class);
				
			}
			else
			{
				return 0;
			}
		 
	 }
	 
	 public int count_cid(int cid) {
	    	
	    	String sql1 = "SELECT COUNT(*) FROM qms.qtitems WHERE cid=?";
	    	Object[] params = {cid};
			int numOf = this.getJdbcTemplate().queryForObject(sql1,params,Integer.class);
			return numOf;
			
		}
	 
	 public int manual_itemid(int subid,int cid) {
			
			String sql="select itemid from qms.qtitems where sub_id=? and cid=?";
			
			 Object[] params = {subid,cid};
			 try {
				 int itemid = this.getJdbcTemplate().queryForObject(sql, params,Integer.class);
		            return itemid;
		        } catch (EmptyResultDataAccessException e) {
		            return 0;
		        }
			
			
		}
	    
	 
	

}
