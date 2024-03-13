/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "thesis_instructor")
public class ThesisInstructor implements Serializable{
    @Id
    @ManyToOne
    @JoinColumn(name = "thesis_id", referencedColumnName = "id")
    private Thesis thesisId;
    
    @Id
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="instructor_id", referencedColumnName = "id")
    private User instructorId;

    public ThesisInstructor(){
        
    }
    
    public ThesisInstructor(Thesis thesis, User user){
        this.thesisId = thesis;
        this.instructorId = user;
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
     * @return the instructorId
     */
    public User getInstructorId() {
        return instructorId;
    }

    /**
     * @param instructorId the instructorId to set
     */
    public void setInstructorId(User instructorId) {
        this.instructorId = instructorId;
    }
}
