/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "thesis_score")
public class ThesisScore implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Float score;
    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "thesis_criteria_id", referencedColumnName = "id")
    private ThesisCriteria thesisCriteriaId;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;

    public ThesisScore(){
        
    }
    
    public ThesisScore(Float score, ThesisCriteria thesisCriteria, User user){
        this.score = score;
        this.createdDate = Date.from(Instant.now());
        this.thesisCriteriaId = thesisCriteria;
        this.userId = user;
    }
    
    /**
     * @return the score
     */
    public Float getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(Float score) {
        this.score = score;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the thesisCriteriaId
     */
    public ThesisCriteria getThesisCriteriaId() {
        return thesisCriteriaId;
    }

    /**
     * @param thesisCriteriaId the thesisCriteriaId to set
     */
    public void setThesisCriteriaId(ThesisCriteria thesisCriteriaId) {
        this.thesisCriteriaId = thesisCriteriaId;
    }
    
}
