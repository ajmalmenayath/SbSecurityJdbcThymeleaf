package org.o7planning.sbsecurity.dao;

import java.sql.Types;

import javax.sql.DataSource;

import org.o7planning.sbsecurity.model.QuotationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional

public class TaxInvoiceDao extends JdbcDaoSupport {
	@Autowired
    public TaxInvoiceDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	public QuotationModel CustomerDetails(int cdid) {
			 
			 
			 String sql = "select * from qms.customer_details where cdid=?";
			 return this.getJdbcTemplate().queryForObject(
			            sql, new Object[]{cdid},
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
	
	public int update_supplydate(int cid,String supplydt) {
		 String sql="UPDATE qms.customer SET supply_date = ? WHERE cid = ?";
		 	
	        Object[] params = {supplydt,cid};
	        int[] types = {Types.VARCHAR,Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
	}
	
	public int update_lpo(int cid,String lpo) {
		 String sql="UPDATE qms.customer SET lpo = ? WHERE cid = ?";
		 	

	        Object[] params = {lpo,cid};
	        int[] types = {Types.VARCHAR,Types.INTEGER};

	        return this.getJdbcTemplate().update(sql, params, types);
	}
	

}
