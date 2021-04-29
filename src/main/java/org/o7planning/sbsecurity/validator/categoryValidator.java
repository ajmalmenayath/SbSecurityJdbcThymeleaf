package org.o7planning.sbsecurity.validator;

import org.o7planning.sbsecurity.dao.CategoryDao;
import org.o7planning.sbsecurity.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
 
@Component
public class categoryValidator implements Validator {
	
	 
    @Autowired
    private CategoryDao catDao;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz == CategoryModel.class;
		
	}
	

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		CategoryModel catModel=(CategoryModel) target;
		
		//category validation
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cat_name", "NotEmpty.CategoryModel.cat_name");
	
		System.out.println(catModel.getCat_id());
		
		 if ((!errors.hasFieldErrors("cat_name")) && (catModel.getFlag())!=1) {
			   
			 String cat_name1 = catDao.getcat_name(catModel.getCat_name());
	            if (cat_name1 != null) {
	                // cat_name is not available.
	                errors.rejectValue("cat_name", "Duplicate.CategoryModel.cat_name");
	            }
	        }
       
		 
        
	}
	  

}
