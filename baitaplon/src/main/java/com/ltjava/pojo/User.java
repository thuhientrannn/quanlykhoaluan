/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.validation.constraints.NotNull;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "user")
public class User implements Serializable{
    public static final String ADMIN = "ADMIN";
    public static final String MANAGER = "MANAGER";
    public static final String TEACHER = "TEACHER";
    public static final String STUDENT = "STUDENT";
    
    @Id
    private String id;
    private String username;
    private String password;
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name= "user_role", referencedColumnName = "id")
    private UserRole userRole;
    private Boolean active;
    @JsonIgnore
    @OneToMany(mappedBy = "reviewerId")
    private Set<Thesis> thesises;
    @JsonIgnore
    @OneToMany(mappedBy = "instructorId")
    private Set<ThesisInstructor> thesisInstructors;
    @JsonIgnore
    @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
    private Set<CouncilMember> members;
    @JsonIgnore
    @OneToOne(mappedBy = "userId")
    private Student studentId;
    @JsonIgnore
    @OneToOne(mappedBy = "userId")
    private Teacher teacherId;
    @JsonIgnore
    @OneToMany(mappedBy = "userId")
    private Set<UserNotification> userNotifications;

    public User(){
        
    }
    
    public User(String id, String username, String password, UserRole userRole){
        this.id = id;
        this.username = username;
        this.password = password;
        this.userRole = userRole;
        this.active = true;
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
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
    
    /**
     * @return the userRole
     */
    public UserRole getUserRole() {
        return userRole;
    }

    /**
     * @param userRole the userRole to set
     */
    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    /**
     * @return the thesises
     */
    public Set<Thesis> getThesises() {
        return thesises;
    }

    /**
     * @param thesises the thesises to set
     */
    public void setThesises(Set<Thesis> thesises) {
        this.thesises = thesises;
    }

    /**
     * @return the thesisInstructors
     */
    public Set<ThesisInstructor> getThesisInstructors() {
        return thesisInstructors;
    }

    /**
     * @param thesisInstructors the thesisInstructors to set
     */
    public void setThesisInstructors(Set<ThesisInstructor> thesisInstructors) {
        this.thesisInstructors = thesisInstructors;
    }


    @Override
    public String toString() {
        return this.username;
    }


    /**
     * @return the members
     */
    public Set<CouncilMember> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(Set<CouncilMember> members) {
        this.members = members;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the studentId
     */
    public Student getStudentId() {
        return studentId;
    }

    /**
     * @param studentId the studentId to set
     */
    public void setStudentId(Student studentId) {
        this.studentId = studentId;
    }

    /**
     * @return the teacherId
     */
    public Teacher getTeacherId() {
        return teacherId;
    }

    /**
     * @param teacherId the teacherId to set
     */
    public void setTeacherId(Teacher teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * @return the userNotifications
     */
    public Set<UserNotification> getUserNotifications() {
        return userNotifications;
    }

    /**
     * @param userNotifications the userNotifications to set
     */
    public void setUserNotifications(Set<UserNotification> userNotifications) {
        this.userNotifications = userNotifications;
    }


    
    
}
