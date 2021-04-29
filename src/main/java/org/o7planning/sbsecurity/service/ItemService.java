package org.o7planning.sbsecurity.service;

import org.o7planning.sbsecurity.dao.ItemDAO;
import org.o7planning.sbsecurity.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class ItemService {
	
	@Autowired
	private ItemDAO dao;
	
	public int insert_Item(ItemModel Imodel)
	{
		return dao.insert_Item(Imodel);
	}
	public ItemModel select_subid_item(int subid,int cid)
	{
		return dao.select_subid_item(subid,cid);
	}
	public int count_subid(int subid,int cid)
	{
		return dao.count_subid(subid,cid);
	}
	public int updateCount(ItemModel imodel)
	{
		return dao.updateCount(imodel);
	}
	public boolean delete_item(int subid,int cid)
	{
		return dao.delete_item(subid, cid);
	}
	public int update_Item(ItemModel Imodel)
	{
		return dao.update_Item(Imodel);
	}
	public int sumofprice(int cid)
	{
		return dao.sumofprice(cid);
	}
	 public int updateManualCount(ItemModel imodel) {
		 return dao.updateManualCount(imodel);
	 }
	 public int manual_itemid(int subid,int cid) {
		 return dao.manual_itemid(subid, cid);
	 }

}
