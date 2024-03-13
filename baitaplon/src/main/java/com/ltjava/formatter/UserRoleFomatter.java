/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.formatter;

import com.ltjava.pojo.UserRole;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;


/**
 *
 * @author HIEN
 */
public class UserRoleFomatter implements Formatter<UserRole>{

    @Override
    public String print(UserRole t, Locale locale) {
         return String.valueOf(t.getId());
    }

    @Override
    public UserRole parse(String string, Locale locale) throws ParseException {
        UserRole u = new UserRole();
        u.setId(Integer.parseInt(string));
        return u;
    }
    
}
