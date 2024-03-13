/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.ThesisCriteria;
import com.ltjava.pojo.ThesisScore;
import com.ltjava.pojo.User;
import com.ltjava.repository.ThesisScoreRepository;
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
public class ThesisScoreRepositoryImpl implements ThesisScoreRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;

    @Override
    public List<Object[]> getThesisScores(Integer councilId) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root scoreRoot = query.from(ThesisScore.class);
        Root thesisRoot = query.from(Thesis.class);
        Root councilRoot = query.from(Council.class);
        
        query = query.where(builder.equal(thesisRoot.get("councilId"),
                councilRoot.get("id")), 
                builder.equal(scoreRoot.get("thesisId"), thesisRoot.get("id")));
        
        if(councilId!=null){
            Predicate p1 = builder.equal(thesisRoot.get("councilId"), councilId);
            query = query.where(p1);
        }
        
        query.multiselect(thesisRoot.get("id"), 
                thesisRoot.get("topic"), 
                scoreRoot.get("criteriaId"), 
                scoreRoot.get("score"),
                scoreRoot.get("userId"), 
                scoreRoot.get("createdDate"));
//        
//        query.groupBy(thesisRoot.get("id"));
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public ThesisScore getThesisScoreById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        ThesisScore u = s.get(ThesisScore.class,id);
        return u;
    }

    @Override
    public boolean addOrUpdate(ThesisScore c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(c);
            System.out.println("THÀNH CÔNGGG");
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
    public ThesisScore getThesisScoreByUTC(Integer thesisCriteriaId, String userId) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<ThesisScore> query = builder.createQuery(ThesisScore.class);
        Root scoreRoot = query.from(ThesisScore.class);
        
        query = query.select(scoreRoot);
        
        Predicate p1 = builder.equal(scoreRoot.get("thesisCriteriaId"), thesisCriteriaId);
        Predicate p2 = builder.equal(scoreRoot.get("userId").get("id"), userId);
        query = query.where(builder.and(p1,p2));
        
        
        Query q = s.createQuery(query);
        
        return (ThesisScore) q.getSingleResult();
    }

    @Override
    public List<Object[]> getListAvgScoreOfCriteria(Integer thesisId) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
        Root scoreRoot = query.from(ThesisScore.class);
        Root criteriaRoot = query.from(ThesisCriteria.class);
        Root thesisRoot = query.from(Thesis.class);
        
        Predicate p2 = builder.equal(criteriaRoot.get("thesisId").get("id"), thesisId);
        Predicate p3 = builder.equal(scoreRoot.get("thesisCriteriaId"), criteriaRoot.get("id"));
        query = query.where(builder.and(p2,p3));
        
        query.multiselect(thesisRoot.get("id"),criteriaRoot.get("criteriaId").get("name"), criteriaRoot.get("maxScore"),builder.avg(scoreRoot.get("score")));
        query.groupBy(criteriaRoot.get("criteriaId").get("name")).orderBy(builder.asc(thesisRoot.get("id")));
        
        Query q = s.createQuery(query);
        return q.getResultList();
    }

}
