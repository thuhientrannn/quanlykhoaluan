/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.formatter;

import com.ltjava.pojo.Major;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author HIEN
 */
public class MajorFomatter implements Formatter<Major>{

    @Override
    public String print(Major t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Major parse(String string, Locale locale) throws ParseException {
        Major u = new Major();
        u.setId(Integer.parseInt(string));
        return u;
    }
   
}
