/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Council;
import com.ltjava.pojo.CouncilMember;
import com.ltjava.pojo.MemberRole;
import com.ltjava.pojo.User;
import com.ltjava.repository.CouncilMemberRepository;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
public class CouncilMemberRepositoryImpl implements CouncilMemberRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<CouncilMember> getMemberByCouncil(Council c) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<CouncilMember> query = builder.createQuery(CouncilMember.class);
        Root root = query.from(CouncilMember.class);
        
        query = query.select(root).orderBy(builder.asc(root.get("memberRole")));
        
        Predicate p1 = builder.equal(root.get("councilId"),c.getId());
        query = query.where(p1);
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

    @Override
    public List<CouncilMember> getCouncilByMember(User u) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<CouncilMember> query = builder.createQuery(CouncilMember.class);
        Root root = query.from(CouncilMember.class);
        
        query = query.select(root);
        
        Predicate p1 = builder.equal(root.get("userId"),u.getId());
        query = query.where(p1);
        
        Query q = s.createQuery(query);
        
        return q.getResultList();
    }

//    @Override
//    public List<Object[]> getListMember() {
//        System.out.println("fgkjhidrjgkdngj");
//        List<Object[]> listResult = new ArrayList<>();
//        Session s = sessionFactory.getObject().getCurrentSession();
//        CriteriaBuilder builder = s.getCriteriaBuilder();
//        CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
//        Root rCouncil = query.from(Council.class);
//        Root rCouncilMember = query.from(CouncilMember.class);
//        Root rUser = query.from(User.class);
//        Root rMemberRole = query.from(MemberRole.class);
//        
//        Predicate p1 = builder.equal(rCouncilMember.get("userId"), rUser.get("id"));
//        Predicate p2 = builder.equal(rCouncilMember.get("councilId"), rCouncil.get("id"));
//        Predicate p3 = builder.equal(rCouncilMember.get("memberRole"), rMemberRole.get("id"));
//        
//        Predicate p4 = builder.equal(rCouncilMember.get("memberRole"), 1);
//        Predicate p5 = builder.equal(rCouncilMember.get("memberRole"), 2);
//        Predicate p6 = builder.equal(rCouncilMember.get("memberRole"), 3);
//        Predicate p7 = builder.equal(rCouncilMember.get("memberRole"), 4);
//        
//        query = query.where(builder.and(p1,p2, p3));
//        
//        query.multiselect(rCouncil.get("id"), 
//                rMemberRole.get("role"), 
//                rUser.get("firstName"), 
//                rUser.get("lastName"))
//                .orderBy(builder.asc(rCouncil.get("id")),builder.asc(rMemberRole.get("id")));
//        Query q = s.createQuery(query);
//        List<Object[]> listO = q.getResultList();
//        Object[] obj = new Object[]{"-1"};
//        for (int i =0; i<listO.size(); i++) {
//            System.out.println(String.format("%s %s %s %s", listO.get(i)[0], listO.get(i)[1], listO.get(i)[2], listO.get(i)[3]));
//            try{
//                if(listO.get(i)[0].toString().matches(obj[0].toString())){
//                    System.out.print("trùng id");
//                    Object[] no = new Object[]{Arrays.asList(obj), String.format("%s %s", listO.get(i)[2], listO.get(i)[3])};
//                    obj = new Object[]{no};
//                    if(i==listO.size()-1){
//                        listResult.add(obj);
//                    }
//                }
//                else{
//                    System.out.print("không trùng id");
//                    if(i>=1){
//                        System.out.print("i>=1");
//                        listResult.add(obj);
//                    }
//                    else{
//                        System.out.print("i=0");
//                    }
//                    obj = new Object[]{};
//                    obj[0] = listO.get(i)[0];
//                    obj[1] = String.format("%s %s", listO.get(i)[2], listO.get(i)[3]);
//                }
//                System.out.print(obj[0]);
//            }catch(Exception e){
//                System.out.print(e.getMessage());
//            }
//            
//        }
//        return listResult;
//    }

    @Override
    public List<Object[]> getListCouncilMember(List<Council> list) {
        List<Object[]> listResult = new ArrayList<>();
        try{
            for (Council c: list){
                Object[] obj = new Object[]{c, this.getMemberByCouncil(c)};
                listResult.add(obj);
            }
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        
        return listResult;
    }

    @Override
    public boolean addCouncilMember(CouncilMember cm) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.save(cm);
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
