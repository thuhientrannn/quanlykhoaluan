/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisInstructor;
import com.ltjava.pojo.User;
import com.ltjava.repository.ThesisInstructorRepository;
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
public class ThesisInstructorRepositoryImpl implements ThesisInstructorRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<ThesisInstructor> getThesisInstructors(Thesis thesis, User user) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<ThesisInstructor> query = builder.createQuery(ThesisInstructor.class);
        Root root = query.from(ThesisInstructor.class);
        
        query = query.select(root);
        
        if(thesis!=null){
            Predicate p1 = builder.equal(root.get("thesisId").get("id").as(Integer.class),thesis.getId());
            query = query.where(p1);
        }
        if(user!=null){
            Predicate p2 = builder.like(root.get("instructorId").get("id").as(String.class),user.getId());
            query = query.where(p2);
        }
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public boolean addOrUpdateThesisInstructor(ThesisInstructor ts) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(ts);
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
    public boolean removeThesisInstructors(Thesis thesis) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<ThesisInstructor> query = builder.createQuery(ThesisInstructor.class);
        Root root = query.from(ThesisInstructor.class);
        
        try{
            query = query.select(root);
        
            if(thesis!=null){
                Predicate p1 = builder.equal(root.get("thesisId").get("id").as(Integer.class),thesis.getId());
                query = query.where(p1);
            }

            Query q = s.createQuery(query);

            List<ThesisInstructor> list = q.getResultList();
            
            for(ThesisInstructor ti : list){
                s.remove(ti);
            }
            System.out.println("Xóa danh sách người hướng dẫn thành công!");
            return true;
        }catch(Exception e){
            System.out.println("SOMETHING WRONG !!");
            System.out.println(e.getMessage());
        }
        return false;
    }
    
}
