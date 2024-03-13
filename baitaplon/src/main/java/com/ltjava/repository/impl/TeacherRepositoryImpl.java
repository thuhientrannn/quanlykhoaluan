/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Teacher;
import com.ltjava.repository.TeacherRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
public class TeacherRepositoryImpl implements TeacherRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    
    @Override
    public List<Teacher> getTeachers(String kw) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Teacher> query = builder.createQuery(Teacher.class);
        Root root = query.from(Teacher.class);
        
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p1 = builder.like(root.get("id"),"%"+kw+"%");
            query = query.where(p1);
        }
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Teacher getTeacherById(String id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Teacher u = s.get(Teacher.class,id);
        return u;
    }

    @Override
    public boolean addOrUpdateTeacher(Teacher t) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(t);
            System.out.println("THÊM GIÁO VIÊN THÀNH CÔNGGG");
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
