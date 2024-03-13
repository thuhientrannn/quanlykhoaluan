/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Status;
import com.ltjava.repository.StatusRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
public class StatusRepositoryImpl implements StatusRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Status> getStatuses() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Status> query = builder.createQuery(Status.class);
        Root root = query.from(Status.class);
        
        query = query.select(root);
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Status getStatus(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Status u = s.get(Status.class, id);
        return u;
    }
    
}
