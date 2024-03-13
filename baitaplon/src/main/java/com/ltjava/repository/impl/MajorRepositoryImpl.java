/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Major;
import com.ltjava.repository.MajorRepository;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.query.Query;
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
public class MajorRepositoryImpl implements MajorRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Major> getMajors(String kw) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Major> query = builder.createQuery(Major.class);
        Root root = query.from(Major.class);
        
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p1 = builder.like(root.get("name").as(String.class),kw);
            query = query.where(p1);
        }
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Major getMajorById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Major u = s.get(Major.class,id);
        return u;
    }

    @Override
    public boolean addMajor(Major m) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.save(m);
            System.out.println("THÊM THÀNH CÔNGGG");
            return true;
        }
        catch(Exception ex){
            System.out.println("LỖI RỒIIIII");
            System.out.println(ex.getMessage());
            System.out.println(ex.getStackTrace());
        }
        return false;
    }

    @Override
    public boolean removeMajor(Major m) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.remove(m);
            System.out.println("XÓA THÀNH CÔNGGG");
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
