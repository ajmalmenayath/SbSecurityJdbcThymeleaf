package org.o7planning.sbsecurity.dao;



import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import org.o7planning.sbsecurity.formbean.AppUserForm;
//import org.o7planning.sbformvalidation.model.AppUser;
import org.o7planning.sbsecurity.model.Gender;
import org.o7planning.sbsecurity.model.AppUser_reg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
 
@Repository
public class AppUser_reg_DAO {
 
    // Config in WebSecurityConfig
    @Autowired
    private PasswordEncoder passwordEncoder;
 
    private static final Map<Long, AppUser_reg> USERS_MAP = new HashMap<>();
 
    static {
        initDATA();
    }
 
    private static void initDATA() {
        String encrytedPassword = "";
 
        AppUser_reg tom = new AppUser_reg(1L, "tom", "Tom", "Tom", //
                true, Gender.MALE, "tom@waltdisney.com", encrytedPassword, "US");
 
        AppUser_reg jerry = new AppUser_reg(2L, "jerry", "Jerry", "Jerry", //
                true, Gender.MALE, "jerry@waltdisney.com", encrytedPassword, "US");
 
        USERS_MAP.put(tom.getUserId(), tom);
        USERS_MAP.put(jerry.getUserId(), jerry);
    }
 
    public Long getMaxUserId() {
        long max = 0;
        for (Long id : USERS_MAP.keySet()) {
            if (id > max) {
                max = id;
            }
        }
        return max;
    }
 
    //
 
    public AppUser_reg findAppUserByUserName(String userName) {
        Collection<AppUser_reg> appUsers = USERS_MAP.values();
        for (AppUser_reg u : appUsers) {
            if (u.getUserName().equals(userName)) {
                return u;
            }
        }
        return null;
    }
 
    public AppUser_reg findAppUserByEmail(String email) {
        Collection<AppUser_reg> appUsers = USERS_MAP.values();
        for (AppUser_reg u : appUsers) {
            if (u.getEmail().equals(email)) {
                return u;
            }
        }
        return null;
    }
 
    public List<AppUser_reg> getAppUsers() {
        List<AppUser_reg> list = new ArrayList<>();
 
        list.addAll(USERS_MAP.values());
        return list;
    }
 
    public AppUser_reg createAppUser(AppUserForm form) {
        Long userId = this.getMaxUserId() + 1;
        String encrytedPassword = this.passwordEncoder.encode(form.getPassword());
 
        AppUser_reg user = new AppUser_reg(userId, form.getUserName(), //
                form.getFirstName(), form.getLastName(), false, //
                form.getGender(), form.getEmail(), form.getCountryCode(), //
                encrytedPassword);
 
        USERS_MAP.put(userId, user);
        return user;
    }
 
}