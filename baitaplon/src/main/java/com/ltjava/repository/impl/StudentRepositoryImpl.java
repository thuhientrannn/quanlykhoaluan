/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.repository.impl;

import com.ltjava.pojo.Major;
import com.ltjava.pojo.MajorYear;
import com.ltjava.pojo.Student;
import com.ltjava.pojo.Thesis;
import com.ltjava.pojo.User;
import com.ltjava.repository.StudentRepository;
import com.ltjava.service.ClassService;
import com.ltjava.service.ThesisService;
import com.ltjava.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class StudentRepositoryImpl implements StudentRepository{
    @Autowired
    private LocalSessionFactoryBean sessionFactory;
    
    @Autowired
    private ClassService classService;
    
    
    
    @Override
    public List<Student> getStudents(Map<String, String> params) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            CriteriaBuilder builder = s.getCriteriaBuilder();
            CriteriaQuery<Object[]> query = builder.createQuery(Object[].class);
            Root root = query.from(Student.class);
                            
            List<Predicate> predicates = new ArrayList<>();
            if(!params.isEmpty()){
                System.out.println("KHÔNG RỖNG");
                if(!params.get("kw").isEmpty() && params.get("kw")!=null){
                    System.out.println(params.get("kw"));
                    Predicate p1 = builder.like(root.get("firstName").as(String.class),params.get("kw"));
                    Predicate p2 = builder.like(root.get("lastName").as(String.class),params.get("kw"));
                    predicates.add(builder.or(p1,p2));
                }
                
                System.out.println(predicates.size());
    
                if(!params.get("yearId").isEmpty() && params.get("yearId")!=null){
                    Root rootMajorYear = query.from(MajorYear.class);
                    Predicate p1 = builder.like(rootMajorYear.get("yearId").get("id").as(String.class), params.get("yearId"));
                    Predicate p2 = builder.like(root.get("classId").get("majorId").get("id"), rootMajorYear.get("majorId").get("id"));
                    predicates.add(builder.and(p1, p2));
                }
                System.out.println(predicates.size());
    
                if(!params.get("majorId").isEmpty() && params.get("majorId")!=null){
                    predicates.add(builder.like(root.get("classId").get("majorId").get("id").as(String.class),params.get("majorId")));
                }
                System.out.println(predicates.size());
    
                if(!params.get("classId").isEmpty() && params.get("classId")!=null){
                    predicates.add(builder.like(root.get("classId").get("id").as(String.class),params.get("classId")));
                }
                System.out.println(predicates.size());
                query.where(builder.and(predicates.toArray(new Predicate[]{})));
            }
            
            query = query.multiselect(root);
            Query q = s.createQuery(query);
            System.out.println(q.getResultList().size());
            return q.getResultList();
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Student getStudentById(String id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Student u = s.get(Student.class,id);
        return u;
    }

    @Override
    public boolean addOrUpdateStudent(Student student) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            s.saveOrUpdate(student);
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
    public boolean addThesis(Student stdnt, Thesis thesis) {
        Session s = sessionFactory.getObject().getCurrentSession();
        try{
            stdnt.setThesisId(thesis);
            s.update(stdnt);
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
    public Object[] getStudentAccount(String id) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Object[] result = new Object[]{};
        Map<String, String> msg = new HashMap<>();
        msg.put("msg", "");
        Map<String, Integer> status = new HashMap<>();
        status.put("status", -1);
        Map<String, Student> object = new HashMap<>();
        object.put("value", new Student());
        try{
            Student student = s.get(Student.class,id);
            User user = s.get(User.class, id);
            if(student==null){
                status.replace("status", 0);
                msg.replace("msg", "Không tìm thấy sinh viên, vui lòng thêm sinh viên trước khi tạo tài khoản !!");
            }
            else{
                if(user==null){
                    status.replace("status", 1);
                    object.replace("value", student);
                }
                else{
                    status.replace("status",2);
                    msg.replace("msg", "Sinh viên này đã có tài khoản đăng nhập");
                }
            }
            result = new Object[]{status.get("status"), object.get("value"),msg.get("msg")};
        }
        catch(Exception e){
            System.out.print("LỖI");
        }
        System.out.println(result[0]);
        System.out.println(result[1]);
        System.out.println(result[2]);
        return result;
    }

    @Override
    public List<Student> getListStudentAccount() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root root = query.from(Student.class);
        
        query = query.select(root);
        
        query.where(builder.isNull(root.get("userId")));
        
        Query q = s.createQuery(query);
        
        System.out.println(q.getResultList().size());
        
        return q.getResultList();
//        List<Student> listResult = new ArrayList<>();
//        CriteriaBuilder builder = s.getCriteriaBuilder();
//        CriteriaQuery<Student> query = builder.createQuery(Student.class);
//        Root root = query.from(Student.class);
//        
//        query = query.select(root);
//        
//        Query q = s.createQuery(query);
//        
//        List<Student> listStudent = q.getResultList();
//        
//        User user = new User();
//        for (Student student : listStudent) {
//            System.out.println(student.getId());
//            user = s.get(User.class, student.getId());
//            System.out.println(user);
//            if(user==null){
//                System.out.println("KHÔNG CÓ TÀI KHOẢN");
//                listResult.add(student);
//            }
//            else{
//                System.out.println("CÓ TÀI KHOẢN");
//            }
//            user = new User();
//        }
//        System.out.println(listResult.size());
//        return listResult;
    }

    @Override
    public boolean updateStudent(Map<String,String> params) {
        Session s = sessionFactory.getObject().getCurrentSession();
        Student studentUpdate = getStudentById(params.get("studentId"));
        try{
            studentUpdate.setId(params.get("id"));
            studentUpdate.setFirstName(params.get("firstName"));
            studentUpdate.setLastName(params.get("lastName"));
            studentUpdate.setEmail(params.get("email"));
            studentUpdate.setPhoneNumber(params.get("phoneNumber"));
            if(!params.get("birthday").isEmpty()&&params.get("birthday")!=null)
                studentUpdate.setBirthday(params.get("birthday"));
            studentUpdate.setClassId(this.classService.getClassById(Integer.parseInt(params.get("classId"))));
            s.update(studentUpdate);
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
    public String loadNewStudentId() {
        Session s = sessionFactory.getObject().getCurrentSession();
        CriteriaBuilder builder = s.getCriteriaBuilder();
        CriteriaQuery<Student> query = builder.createQuery(Student.class);
        Root root = query.from(Student.class);
        
        query = query.select(root).orderBy(builder.asc(root.get("id")));
        
        Query q = s.createQuery(query);
        
        Student lastStudent = (Student)q.getResultList().get(q.getResultList().size()-1);
        System.out.println(lastStudent.getId());
        String lastId = lastStudent.getId();
        String word = "SV";
        String number = String.valueOf(Integer.parseInt(lastId.replace(word, ""))+1);
        String kq = word + number;
        System.out.println(kq);
        return kq;
    }
    
}
