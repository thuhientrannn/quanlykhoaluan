/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
import javax.persistence.Table;

/**
 *
 * @author HIEN
 */

@Entity
@Table(name = "thesis_criteria")
public class ThesisCriteria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="thesis_id", referencedColumnName = "id")
    private Thesis thesisId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="criteria_id", referencedColumnName = "id")
    private Criteria criteriaId;
    @Column(name = "max_score")
    private Float maxScore;
    @JsonIgnore
    @OneToMany(mappedBy = "thesisCriteriaId", fetch = FetchType.EAGER)
    private Set<ThesisScore> thesisScores;

    public ThesisCriteria(){
        
    }
    
    public ThesisCriteria(Thesis thesis, Criteria criteria, Float maxScore){
        this.thesisId = thesis;
        this.criteriaId = criteria;
        this.maxScore = maxScore;
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
     * @return the criteriaId
     */
    public Criteria getCriteriaId() {
        return criteriaId;
    }

    /**
     * @param criteriaId the criteriaId to set
     */
    public void setCriteriaId(Criteria criteriaId) {
        this.criteriaId = criteriaId;
    }

    /**
     * @return the thesisScores
     */
    public Set<ThesisScore> getThesisScores() {
        return thesisScores;
    }

    /**
     * @param thesisScores the thesisScores to set
     */
    public void setThesisScores(Set<ThesisScore> thesisScores) {
        this.thesisScores = thesisScores;
    }

    /**
     * @return the maxScore
     */
    public Float getMaxScore() {
        return maxScore;
    }

    /**
     * @param maxScore the maxScore to set
     */
    public void setMaxScore(Float maxScore) {
        this.maxScore = maxScore;
    }
    
}
