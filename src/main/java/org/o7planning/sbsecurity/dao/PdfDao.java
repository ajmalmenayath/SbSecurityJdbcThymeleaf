package org.o7planning.sbsecurity.dao;

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

public class PdfDao extends JdbcDaoSupport{
	
	@Autowired
    public PdfDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
	
	public QuotationModel QtsDetails(int cid)
	{
		String sql="select * from qms.customer where cid=?";
		
		return this.getJdbcTemplate().queryForObject(sql, new Object[]{cid},
				(rs, rowNum) ->new QuotationModel(
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

					
						));
	}
	
	public List<NoteModel> QtsNote(int cid){
		String sql="SELECT * FROM qms.notes WHERE cid = ?";
		return this.getJdbcTemplate().query(sql,new Object[] {cid},
				(rs, rowNum) ->new NoteModel(
						rs.getInt("nid"),
						rs.getInt("cid"),
						rs.getString("note")
						
						)
				);
	}
	
	public List<ItemModel> QtsItem(int cid) {
		
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
	
	 
	

}
