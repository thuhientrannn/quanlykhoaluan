/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.ThesisCriteria;
import com.ltjava.repository.ThesisCriteriaRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HIEN
 */
@Repository
@Transactional
public class ThesisCriteriaRepositoryImpl implements ThesisCriteriaRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public ThesisCriteria getThesisCriteriaById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        ThesisCriteria t = s.get(ThesisCriteria.class,id);
        return t;
    }

    @Override
    public boolean addOrUpdate(ThesisCriteria c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(c);
            System.out.println("THÊM TIÊU CHÍ CHẤM ĐIỂM THÀNH CÔNGGG");
            return true;
        }
        catch(Exception ex){
            System.out.println("LỖI RỒIIIII");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
        return false;
    }
    
}
