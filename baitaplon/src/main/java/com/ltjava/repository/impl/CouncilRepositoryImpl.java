/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.CouncilMember;
import com.ltjava.pojo.Thesis;
import com.ltjava.repository.CouncilRepository;
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
public class CouncilRepositoryImpl implements CouncilRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<Council> getCouncils(String kw) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Council> query = builder.createQuery(Council.class);
        Root root = query.from(Council.class);
        
        query = query.select(root);
        
        if(!kw.isEmpty() && kw!=null){
            Predicate p1 = builder.like(root.get("id").as(String.class),kw);
            query = query.where(p1);
        }
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public boolean addCouncil(Council c) {
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
    public Council getCouncilById(Integer id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Council u = s.get(Council.class,id);
        return u;
    }

    @Override
    public boolean lockCouncil(Council c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        c.setActive(false);
        try{
            s.update(c);
            System.out.println("KHÓA THÀNH CÔNGGG");
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
    public boolean removeCouncil(Council c) {
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

    @Override
    public boolean removeCouncilMembers(Council c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            if(c.getMembers().size()>0){
                System.out.println("CÓ THÀNH VIÊN");
                for(CouncilMember cmb : c.getMembers()){
                    s.remove(cmb);
                    System.out.println(c.getMembers().size());
                }
                s.update(c);
                System.out.println(c.getMembers().size());
                System.out.println("XÓA DS THÀNH VIÊN THÀNH CÔNG");
            }
            System.out.print("DANH SÁCH RỖNG");
            return true;
        }catch(Exception e){
            System.out.println("XÓA DS THÀNH VIÊN THẤT BẠII");
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean removeThesises(Council c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            if(c.getThesises().size()>0){
                System.out.println("CÓ KHOÁ LUẬN");
                System.out.println(c.getThesises().size());
                for(Thesis thesis:c.getThesises()){
                    thesis.setCouncilId(null);
                    s.update(thesis);
                    System.out.println(c.getThesises().size());
                }
                s.update(c);
                System.out.println(c.getThesises().size());
                System.out.println("XÓA DS KHÓA LUẬN THÀNH CÔNG");
            }
            System.out.print("DANH SÁCH RỖNG");
            return true;
        }catch(Exception e){
            System.out.println("XÓA DS KHÓA LUẬN THẤT BẠII");
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean unlockCouncil(Council c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        c.setActive(true);
        try{
            s.update(c);
            System.out.println("MỞ KHÓA THÀNH CÔNGGG");
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
