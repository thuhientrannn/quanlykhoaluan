/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Student;
import com.ltjava.pojo.Teacher;
import com.ltjava.pojo.User;
import com.ltjava.pojo.UserRole;
import com.ltjava.repository.UserRepository;
import java.util.ArrayList;
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
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Override
    public List<User> getUsers(String kw, String userRoleName) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(User.class);
            Root root = query.from(User.class);

            query = query.select(root);
            List<Predicate> predicates = new ArrayList<>();
            if(!kw.isEmpty() && kw!=null){
                predicates.add(builder.like(root.get("username").as(String.class),kw));
            }
            if(userRoleName!=null && !userRoleName.isEmpty()){
                predicates.add(builder.like(root.get("userRole").get("name").as(String.class),userRoleName));
            }
            query.where(predicates.toArray(new Predicate[]{}));

            Query q = s.createQuery(query);

            return q.getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public User getUserById(String id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        User u = s.get(User.class,id);
        return u;
    }

    @Override
    public boolean addOrUpdateUser(User user) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(user);
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
    public boolean changePassword(User user, String pass) {
        Session s = sessionFactory.getObject().getCurrentSession();
        user.setPassword(pass);
        try{
            s.update(user);
            System.out.println("ĐỔI MẬT KHẨU THÀNH CÔNGGG");
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
    public User getUserByUsername(String string) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        
        query = query.select(root);
        
        query = query.where(builder.equal(root.get("username"), string));
        
        Query q = s.createQuery(query);
        
        return (User)q.getSingleResult();
    }

    @Override
    public boolean updateUser(User oldUser, User newUser) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
//            oldUser.setFirstName(newUser.getFirstName());
//            oldUser.setLastName(newUser.getLastName());
            oldUser.setUserRole(newUser.getUserRole());
//            oldUser.setPhoneNumber(newUser.getPhoneNumber());
            oldUser.setUsername(newUser.getUsername());
            oldUser.setPassword(newUser.getPassword());
            s.update(oldUser);
            System.out.println("CẬP NHẬT THÀNH CÔNGGG");
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
    public boolean removeUser(User u) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.remove(u);
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
    public String loadNewUserId(Integer u) {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root root = query.from(User.class);
        
        query = query.select(root);
        
        Predicate p1 = builder.equal(root.get("userRole").get("id").as(Integer.class),u);
        query = query.where(builder.or(p1)).orderBy(builder.asc(root.get("id")));
        
        Query q = s.createQuery(query);
        
        User lastUser = (User)q.getResultList().get(q.getResultList().size()-1);
        System.out.println(lastUser.getId());
        String lastId = lastUser.getId();
        String word ="";
        String number = "";
        if(u==1){
            word = "AD";
            number = String.valueOf(Integer.parseInt(lastId.replace(word, ""))+1);
        }
        else{
            if(u==2){
                word = "MA";
                number = String.valueOf(Integer.parseInt(lastId.replace(word, ""))+1);
            }
            else{
                if(u==3){
                    word = "GV";
                    number = String.valueOf(Integer.parseInt(lastId.replace(word, ""))+1);
                }
                else{
                    if(u==4)
                    word = "SV";
                    number = String.valueOf(Integer.parseInt(lastId.replace(word, ""))+1);
                }
            }
        }
        String kq = word + number;
        System.out.println(kq);
        return kq;
    }

    @Override
    public boolean updateUserId(User user) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            if(user.getUserRole().getId()==4){
                Student student = s.get(Student.class, user.getId());
                student.setUserId(user);
                s.update(student);
                System.out.println("THÊM TÀI KHOẢN HỌC SINH");
            }
            else{
                Teacher teacher = s.get(Teacher.class, user.getId());
                teacher.setUserId(user);
                s.update(teacher);
                System.out.println("THÊM TÀI KHOẢN GIÁO VIÊN");
            }
            return true;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("UPDATE THÊM USER ID THÀNH CÔNG");
        }
        return false;
    }
    
}
