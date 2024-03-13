/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisScore;
import com.ltjava.pojo.User;
import com.ltjava.repository.StatsRepository;
import java.util.ArrayList;
import java.util.Date;
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
public class StatsRepositoryImpl implements StatsRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> scoreStats() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root root = query.from(ThesisScore.class);
        
        query.multiselect(builder.substring(root.get("createdDate"), 1, 4).as(String.class), builder.avg(root.get("score")));
        query.groupBy(builder.substring(root.get("createdDate"), 1, 4).as(String.class));
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
        
    }

    @Override
    public List<Object[]> thesisStats() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root studentRoot = query.from(Student.class);
        Root thesisRoot = query.from(Thesis.class);
        Root classRoot = query.from(com.ltjava.pojo.Class.class);
        Root majorRoot = query.from(Major.class);
        
        List<Predicate> predicates = new ArrayList<>();
        
        predicates.add(builder.equal(studentRoot.get("thesisId"), thesisRoot.get("id")));
        predicates.add(builder.equal(studentRoot.get("classId"), classRoot.get("id")));
        predicates.add(builder.equal(classRoot.get("majorId"), majorRoot.get("id")));
        
        query.multiselect(majorRoot.get("id"),majorRoot.get("name"),builder.count(studentRoot.get("thesisId")));
        
        query.where(predicates.toArray(new Predicate[]{}));
        query.groupBy(majorRoot.get("id"));
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public List<Object[]> countUserByUserRole() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root root = query.from(User.class);
        Predicate p1 = builder.equal(root.get("active"), true);
        query.multiselect(root.get("userRole").get("name"), builder.count(root.get("id")));
        query.groupBy(root.get("userRole"));
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }
    
    
}
