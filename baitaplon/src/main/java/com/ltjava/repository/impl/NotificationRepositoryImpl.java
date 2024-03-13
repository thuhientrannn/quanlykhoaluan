/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Notification;
import com.ltjava.repository.NotificationRepository;
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
public class NotificationRepositoryImpl implements NotificationRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Notification> getNotifications() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Notification> query = builder.createQuery(Notification.class);
        Root root = query.from(Notification.class);
        
        query = query.select(root);
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public Notification getNotificationById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Notification u = s.get(Notification.class, id);
        return u;
    }

    @Override
    public boolean addNotification(Notification ntfctn) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(ntfctn);
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
    
}
