/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.MemberRole;
import com.ltjava.repository.MemberRoleRepository;
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
public class MemberRoleRepositoryImpl implements MemberRoleRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<MemberRole> getMemberRoles() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<MemberRole> query = builder.createQuery(MemberRole.class);
        Root root = query.from(MemberRole.class);
        
        query = query.select(root);
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public MemberRole getMemberRoleById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        MemberRole u = s.get(MemberRole.class, id);
        return u;
    }
    
}
