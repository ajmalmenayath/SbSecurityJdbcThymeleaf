package org.o7planning.sbsecurity.validator;

import org.o7planning.sbsecurity.dao.CategoryDao;
import org.o7planning.sbsecurity.model.SubcategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class subcategoryValidator implements Validator {
	
	
	 
    @Autowired
    private CategoryDao catDao;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub 
		return clazz == SubcategoryModel.class;
	
	}

	@Override
	public void validate(Object target1, Errors errors) {
		// TODO Auto-generated method stub
		SubcategoryModel submodel=(SubcategoryModel) target1;
		
		//subcategory validation
		 
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "sub_name", "NotEmpty.SubcategoryModel.sub_name");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "price", "NotEmpty.SubcategoryModel.price");
		 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cat_name", "NotEmpty.SubcategoryModel.cat_name");
		 
		 if ( (submodel.getPrice())== 0) {
			   
			
	            errors.rejectValue("price", "NotEmpty.SubcategoryModel.price");
	          
	        }
		
		  
	       if(!errors.hasErrors() && submodel.getPrice()!=(int)submodel.getPrice()){
	       	    errors.rejectValue("price","intonly.SubcategoryModel.price");
	       }
	       
	       if ((!errors.hasFieldErrors("sub_name"))&&submodel.getFlag()!=1) {
			   
				 String cat_name1 = catDao.getsubcat_name(submodel.getSub_name());
		            if (cat_name1 != null) {
		                // cat_name is not available.
		                errors.rejectValue("sub_name", "Duplicate.SubcategoryModel.sub_name");
		            }
		        }
	       
	       
		
	}

}
