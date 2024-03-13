/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.User;
import com.ltjava.pojo.UserNotification;
import com.ltjava.repository.UserNotificationRepository;
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
public class UserNotificationRepositoryImpl implements UserNotificationRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<UserNotification> getUserNotifications(User user) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<UserNotification> query = builder.createQuery(UserNotification.class);
        Root root = query.from(UserNotification.class);
        
        query = query.select(root);
        
        if(user!=null){
            query= query.where(builder.equal(root.get("userId"), user));
        }
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public UserNotification getUserNotificationById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        UserNotification u = s.get(UserNotification.class, id);
        return u;
    }

    @Override
    public boolean addUserNotification(UserNotification un) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(un);
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
