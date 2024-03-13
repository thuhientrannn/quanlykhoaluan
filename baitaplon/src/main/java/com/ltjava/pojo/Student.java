/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "student")
public class Student implements Serializable{
    @Id
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String birthday;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "class_id", referencedColumnName = "id")
    private Class classId;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    private Thesis thesisId;
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    
    
    /**
     * @return the classId
     */
    public Class getClassId() {
        return classId;
    }

    /**
     * @param classId the classId to set
     */
    public void setClassId(Class classId) {
        this.classId = classId;
    }

    /**
     * @return the thesisId
     */
    public Thesis getThesisId() {
        return thesisId;
    }

    /**
     * @param thesisId the thesisId to set
     */
    public void setThesisId(Thesis thesisId) {
        this.thesisId = thesisId;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the phoneNumber
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phoneNumber to set
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the birthday
     */
    public String getBirthday() {
//        return LocalDateTime.parse(birthday, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")).format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return birthday;
    }

    /**
     * @param birthday the birthday to set
     */
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the userId
     */
    public User getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    
}
