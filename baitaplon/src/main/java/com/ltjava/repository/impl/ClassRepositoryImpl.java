/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Class;
import com.ltjava.pojo.Major;
import com.ltjava.repository.ClassRepository;
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
public class ClassRepositoryImpl implements ClassRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Class> getClasses(String kw) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Class> query = builder.createQuery(Class.class);
        Root root = query.from(Class.class);
        
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p1 = builder.like(root.get("name").as(String.class),kw);
            query = query.where(p1);
        }
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Class getClassByName(String name) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Class> query = builder.createQuery(Class.class);
        Root root = query.from(Class.class);
        
        query = query.select(root);
        
        if(!name.isEmpty() && name!=null){
            Predicate p1 = builder.equal(root.get("name").as(String.class),name);
            query = query.where(p1);
        }
        
        Query q = s.createQuery(query);
        
        return (Class) q.getResultList().get(0);
    }

    @Override
    public boolean addClass(Class c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.save(c);
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
    public Class getClassById(Integer intgr) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Class u = s.get(Class.class,intgr);
        return u;
    }

    @Override
    public boolean removeClass(Class c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.remove(c);
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
