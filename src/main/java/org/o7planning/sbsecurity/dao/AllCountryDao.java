package org.o7planning.sbsecurity.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.model.AllCountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.jdbc.core.ResultSetExtractor;

@Repository
@Transactional
public class AllCountryDao extends JdbcDaoSupport{
	
	
	  @Autowired
	    public AllCountryDao(DataSource dataSource) {
	        this.setDataSource(dataSource);
	    }
	  
	  
	 /* 
	  class CountryRowMapper implements RowMapper<AllCountry> {
		  
	        @Override
	        public AllCountry mapRow(ResultSet rs, int rowNum) throws SQLException {
	            String name=rs.getString("name");
	            String iso = rs.getString("iso");
	            
	            return new AllCountry(iso, name);
	        }
	 
	    }*/
	  class AllCountryListResultSetExtractor implements ResultSetExtractor<List<AllCountry>> {
		  
	        @Override
	        public List<AllCountry> extractData(ResultSet rs) throws SQLException, DataAccessException {
	            List<AllCountry> list = new ArrayList<AllCountry>();
	            while (rs.next()) {
	            	String name=rs.getString("name");
		            String iso = rs.getString("iso");
	                list.add(new AllCountry(iso, name));
	            }
	            return list;
	        }
	 
	    }
	  
	

	 public  List<AllCountry> countryName() {
	    	
	    	//String sql1 = "SELECT COUNT(*) FROM App_Role";
			//int numOfCars = this.getJdbcTemplate().queryForObject(sql1, Integer.class);
		//	System.out.println("----------------/////////"+numOfCars);
	    	
	    	AllCountryListResultSetExtractor all=new AllCountryListResultSetExtractor();
	        String sql = "Select name,iso " //
	                + " from country "; //
	               
	 
	        List<AllCountry> list = this.getJdbcTemplate().query(sql,all);
	        return list;
	      //  Object[] params = new Object[] { userId };
	 
	 //       return this.getJdbcTemplate().queryForList(sql,String.class);
	 
	       
	    }
	 public List<AllCountry> likecontry(String name){
		 
		 
		 String sql = "select * from country where name like ?";
		 return this.getJdbcTemplate().query(
		            sql, new Object[]{ "%" + name + "%"},
		            (rs, rowNum) ->
		                    new AllCountry(
		                            
		                    		
		                            rs.getString("iso"),
		                            rs.getString("name")
		                            
		                    )
		    );
	 }
	
	
}
