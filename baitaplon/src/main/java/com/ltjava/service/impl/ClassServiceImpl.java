/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Class;
import com.ltjava.pojo.Major;
import com.ltjava.repository.ClassRepository;
import com.ltjava.service.ClassService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class ClassServiceImpl implements ClassService{
    @Autowired
    private ClassRepository classRepository;
    
    
    @Override
    public List<Class> getClasses(String kw) {
        return this.classRepository.getClasses(kw);
    }

    @Override
    public Class getClassByName(String name) {
        return this.classRepository.getClassByName(name);
    }

    @Override
    public boolean addClass(Class c) {
        return this.classRepository.addClass(c);
    }

    @Override
    public Class getClassById(Integer intgr) {
        return this.classRepository.getClassById(intgr);
    }

    @Override
    public boolean removeClass(Class type) {
        return this.classRepository.removeClass(type);
    }
    
}
