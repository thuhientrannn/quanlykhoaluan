/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.formatter;

import com.ltjava.pojo.User;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author HIEN
 */
public class UserFomatter implements Formatter<User>{

    @Override
    public String print(User t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public User parse(String string, Locale locale) throws ParseException {
        User u = new User();
        u.setId(string);
        return u;    
    }
    
}
