package org.o7planning.sbsecurity.dao;

import java.sql.Types;
import java.util.List;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.model.CategoryModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CategoryDao extends JdbcDaoSupport {
	
	
	@Autowired
    public CategoryDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	
	public String getcat_name(String catname) {
		
		String sql="select cat_name from category where cat_name=?";
		
		Object[] params = new Object[] {catname};
		 try {
			 String name = this.getJdbcTemplate().queryForObject(sql, params,String.class);
	            return name;
	        } catch (EmptyResultDataAccessException e) {
	            return null;
	        }
		
		
	}
	
	 public int insert_category(CategoryModel category)
	    {
	    	
	 	    String in_sql="insert into category (cat_name,disc) values(?,?)";
	    	
	 	    return this.getJdbcTemplate().update(in_sql, new Object[] {
	 	    
	 	    		category.getCat_name(),
	 	    		category.getDisc()
	           
	 		});
	 	 	
	    } 
	
	 
	 public List<CategoryModel> showcat() {

		    String sql = "SELECT * FROM category";

		    return this.getJdbcTemplate().query(
		            sql,
		            (rs, rowNum) ->
		                    new CategoryModel(
		                            
		                    		rs.getInt("cat_id"),
		                            rs.getString("cat_name"),
		                            rs.getString("disc")
		                            
		                    )
		    );
	}
	 public CategoryModel cat_id(int id) {

		    String sql = "SELECT * FROM qms.category WHERE cat_id = ?";

		    return this.getJdbcTemplate().queryForObject(sql, new Object[]{id}, (rs, rowNum) ->
		    new CategoryModel(
                    
            		rs.getInt("cat_id"),
                    rs.getString("cat_name"),
                    rs.getString("disc")
                    
            ));

		}
	 
	 
	 
	 
	 public int update_category(CategoryModel category)
	 {
	    	
		 
	 	    String sql="UPDATE category SET cat_name = ?, disc = ? WHERE cat_id = ?";


	        Object[] params = {category.getCat_name(), category.getDisc(), category.getCat_id()};
	        int[] types = {Types.VARCHAR, Types.VARCHAR, Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
		 
		 
	  } 
	 
	 
	 
	 public boolean delete_category(int id)
	 {
	    
	 	  String sql="DELETE FROM category WHERE cat_id = ?";
          Object[] params = {id};
	 	  return this.getJdbcTemplate().update(sql, params) == 1;

	    
	  } 
	 
	 public int insert_subcategory(SubcategoryModel subcat)
	 {
	    	int catid=Integer.parseInt(subcat.getCat_name());
	    	CategoryModel catm=cat_id(catid);
	    	
	 	    String in_sql="insert into qms.subcategory (sub_name,price,cat_id,disc,cat_name) values(?,?,?,?,?)";
	    	
	 	    return this.getJdbcTemplate().update(in_sql, new Object[] {
	 	    
	 	    		subcat.getSub_name(),
	 	    		subcat.getPrice(),
	 	    		catid,
	 	    		subcat.getDisc(),
	 	    		catm.getCat_name()
	 	    		
	           
	 		});
	 	 	
	   } 
	 
	 public String getsubcat_name(String subname) {
			
			String sql="select sub_name from subcategory where sub_name=?";
			
			Object[] params = new Object[] {subname};
			 try {
				 String name = this.getJdbcTemplate().queryForObject(sql, params,String.class);
		            return name;
		        } catch (EmptyResultDataAccessException e) {
		            return null;
		        }
			
			
		}
	 public List<SubcategoryModel> showsubcat() {

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
	 
	 public boolean delete_subcategory(int id)
	 {
	    
	 	  String sql="DELETE FROM subcategory WHERE sub_id = ?";
          Object[] params = {id};
	 	  return this.getJdbcTemplate().update(sql, params) == 1;

	    
	  } 
	
	 
	 public int update_subcategory(SubcategoryModel subcategory)
	 {
		   int catid=Integer.parseInt(subcategory.getCat_name());
	       CategoryModel catm=cat_id(catid);
		 
	 	    String sql="UPDATE subcategory SET sub_name = ?,price= ?,cat_id= ?, disc = ?, cat_name= ? WHERE sub_id = ?";


	        Object[] params = {subcategory.getSub_name(),subcategory.getPrice(),catid, subcategory.getDisc(),catm.getCat_name(),subcategory.getSub_id()};
	        int[] types = {Types.VARCHAR,Types.INTEGER,Types.INTEGER, Types.VARCHAR,Types.VARCHAR, Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
		 
		 
	  } 
	 
	 
	 public SubcategoryModel sub_id(int id) {

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
	 public boolean delete_subcategory_cid(int id)
	 {
	    
	 	  String sql="DELETE FROM subcategory WHERE cat_id = ?";
          Object[] params = {id};
	 	  return this.getJdbcTemplate().update(sql, params) == 1;

	    
	  } 
	 
	
	
	 
	 }
