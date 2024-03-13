/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.formatter;

import com.ltjava.pojo.Class;
import com.ltjava.service.ClassService;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;

/**
 *
 * @author HIEN
 */
public class ClassFomatter implements Formatter<com.ltjava.pojo.Class>{

    @Autowired
    ClassService classService;
    
    @Override
    public String print(Class t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Class parse(String string, Locale locale) throws ParseException {
        Class u = new Class();
        u.setId(Integer.parseInt(string));
        return u;
    }
    
}
