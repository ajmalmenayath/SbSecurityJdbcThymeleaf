package org.o7planning.sbsecurity.validator;

import org.o7planning.sbsecurity.dao.QuotationDao;
import org.o7planning.sbsecurity.model.QuotationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class QuotationValidator implements Validator {
	
	@Autowired
	private QuotationDao QDao;

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return clazz == QuotationModel.class;
		
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		QuotationModel custmodel=(QuotationModel) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cname", "NotEmpty.QuotationModel.cname");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "emirates", "NotEmpty.QuotationModel.emirates");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "trnno", "NotEmpty.QuotationModel.trnno");
		if ((!errors.hasFieldErrors("cname")) && custmodel.getFlag()!=1) {
			   
			 String cat_name1 = QDao.getcustomer_name(custmodel.getCname());
	            if (cat_name1 != null) {
	                // cat_name is not available.
	                errors.rejectValue("cname", "Duplicate.QuotationModel.cname");
	            }
	     }
		if ((!errors.hasFieldErrors("trn")) && custmodel.getFlag()!=1) {
			   
			 String cat_name1 = QDao.getcustomer_trno(custmodel.getTrnno());
	            if (cat_name1 != null) {
	                //cname is not available.
	                errors.rejectValue("trnno", "Duplicate.QuotationModel.trnno");
	            }
	     }
		if(custmodel.getCdid()!=0) {
			String cat_name1 = QDao.getNotCname(custmodel.getCdid(),custmodel.getCname());
            if (cat_name1 != null) {
                //cname is not available.
                errors.rejectValue("cname", "Duplicate.QuotationModel.cname");
            }
		}
		
		
	}

}
