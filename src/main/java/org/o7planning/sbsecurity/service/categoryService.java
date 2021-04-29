package org.o7planning.sbsecurity.service;

import java.util.List;

import org.o7planning.sbsecurity.dao.CategoryDao;
import org.o7planning.sbsecurity.model.CategoryModel;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class categoryService {
	
	@Autowired
	private CategoryDao catdao;
	
	public int insert_category(CategoryModel category)
	{
		return catdao.insert_category(category);
	}
	
	public List<CategoryModel> showcat()
	{
		return catdao.showcat();
	}
	
	public CategoryModel cat_id(int id)
	{
		return catdao.cat_id(id);
	}
	public int update_category(CategoryModel category)
	{
		return catdao.update_category(category);
	}

	public boolean delete_category(int id)
	{
		return catdao.delete_category(id);
	}

	public int insert_subcategory(SubcategoryModel subcat) {
		return catdao.insert_subcategory(subcat);
	}
	
	public List<SubcategoryModel> showsubcat()
	{
		return catdao.showsubcat();
	}
	
	public boolean delete_subcategory(int id)
	{
		return catdao.delete_subcategory(id);
	}
	public int update_subcategory(SubcategoryModel sub)
	{
		return catdao.update_subcategory(sub);
	}
	
	public SubcategoryModel sub_id(int id)
	{
		return catdao.sub_id(id);
	}
	public boolean delete_subcategory_cid(int id) 
	{
		
		return catdao.delete_subcategory_cid(id);
	}
}
