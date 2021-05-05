package org.o7planning.sbsecurity.dao;


import javax.sql.DataSource;
//import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.o7planning.sbsecurity.formbean.AppUserForm;
import org.springframework.beans.factory.annotation.Autowired;


@Repository
@Transactional
public class insertDao extends JdbcDaoSupport {
	
	
	
	// Config in WebSecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;
	
	

    @Autowired
    public insertDao(DataSource dataSource) {
        this.setDataSource(dataSource);
    }
 
    public int count() {
    	
    	String sql1 = "SELECT COUNT(*) FROM App_Role";
		int numOf = this.getJdbcTemplate().queryForObject(sql1, Integer.class);
		System.out.println("----------------/////////"+numOf);
		return numOf;
		
	}
    
    public int insert(AppUserForm app)
    {
    	//Long userId = this.getMaxUserId() + 1;
    	String encrytedPassword = this.passwordEncoder.encode(app.getPassword());
    	
 	     
 	    String in_sql="insert into app_user_reg (userId, userName,firstName,lastName,enabled,gender,email,encrytedPassword,countryCode) values(?,?,?,?,?,?,?,?,?)";
    	app.setEnabled(true);
 	    
 	    return this.getJdbcTemplate().update(in_sql, new Object[] { 
 	    		app.getUserId(),
                app.getUserName(),
                app.getFirstName(),
                app.getLastName(),
                app.isEnabled(),
                app.getGender(),
                app.getEmail(),
                encrytedPassword,
                app.getCountryCode()
               
		
			 
 		});
 	    
    	 
    	
    
    }
    
    
    /*
     * 
     * 
     * update(sql, new Object[] { customer.getCustId(),
			customer.getName(),customer.getAge()
	})
    
    public int insert(AppUser_reg app)
    {
    	
    	
 	     
 	    String in_sql="insert into app_user_reg (userId, userName,firstName,lastName,enabled,gender,email,encrytedPassword,countryCode) values(?,?,?,?,?,?,?,?,?)";
    	
    	 return this.getJdbcTemplate().update(
	                in_sql,
	                app.getUserId(),
	                app.getUserName(),
	                app.getFirstName(),
	                app.getLastName(),
	                "1",
	                app.getGender(),
	                app.getEmail(),
	                app.getEncrytedPassword(),
	                app.getCountryCode()
	                
    			 
    			 
    			 
    			 
    			 
    			 );
    	
    
    }
    */
    
    /*
    public List<String> getRoleNames(Long userId) {
    	
    	String sql1 = "SELECT COUNT(*) FROM App_Role";
		int numOfCars = this.getJdbcTemplate().queryForObject(sql1, Integer.class);
		System.out.println("----------------/////////"+numOfCars);
    	
    	
        String sql = "Select r.Role_Name " //
                + " from User_Role ur, App_Role r " //
                + " where ur.Role_Id = r.Role_Id and ur.User_Id = ? ";
 
        Object[] params = new Object[] { userId };
 
        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);
 
        return roles;
    }
    */
 
}
