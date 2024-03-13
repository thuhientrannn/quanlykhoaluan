/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Criteria;
import com.ltjava.repository.CriteriaRepository;
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
public class CriteriaRepositoryImpl implements CriteriaRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Criteria> getCriterias(String kw) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Criteria> query = builder.createQuery(Criteria.class);
        Root root = query.from(Criteria.class);
        
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p1 = builder.like(root.get("name").as(String.class),kw);
            query = query.where(p1);
        }
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Criteria getCriteriaById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Criteria u = s.get(Criteria.class,id);
        return u;
    }

    @Override
    public boolean addCriteria(Criteria crtr) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.save(crtr);
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
    public Criteria getCriteriaByName(String name) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Criteria> query = builder.createQuery(Criteria.class);
        Root root = query.from(Criteria.class);
        
        query = query.select(root);
        
        if(!name.isEmpty() && name!=null){
            Predicate p1 = builder.like(root.get("name").as(String.class),name);
            query = query.where(p1);
        }
        
        Query q = s.createQuery(query);
        
        if(q.getResultList().size()>0){
            return (Criteria)q.getSingleResult();
        }
        else{
            return null;
        }
    }
}
