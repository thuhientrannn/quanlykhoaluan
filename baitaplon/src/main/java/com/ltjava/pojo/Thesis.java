/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.Transient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "thesis")
public class Thesis implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String topic;
    private String description;
    @Column(name = "file_url")
    private String fileUrl;
    @Transient
    private MultipartFile file;
    @Column(name = "created_date")
    private String createdDate;
    @ManyToOne
    @JoinColumn(name = "reviewer_id", referencedColumnName = "id")
    private User reviewerId;
    @ManyToOne
    @JoinColumn(name = "council_id", referencedColumnName = "id")
    private Council councilId;
    @JsonIgnore
    @OneToMany(mappedBy = "thesisId", fetch = FetchType.EAGER)
    private Set<Student> students;
    @JsonIgnore
    @OneToMany(mappedBy = "thesisId", fetch = FetchType.EAGER)
    private Set<ThesisCriteria> thesisCriterias;
    @JsonIgnore
    @OneToMany(mappedBy = "thesisId", fetch = FetchType.EAGER)
    private Set<ThesisInstructor> thesisInstructors;

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
     * @return the topic
     */
    public String getTopic() {
        return topic;
    }

    /**
     * @param topic the topic to set
     */
    public void setTopic(String topic) {
        this.topic = topic;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the reviewerId
     */
    public User getReviewerId() {
        return reviewerId;
    }

    /**
     * @param reviewerId the reviewerId to set
     */
    public void setReviewerId(User reviewerId) {
        this.reviewerId = reviewerId;
    }

    /**
     * @return the councilId
     */
    public Council getCouncilId() {
        return councilId;
    }

    /**
     * @param councilId the councilId to set
     */
    public void setCouncilId(Council councilId) {
        this.councilId = councilId;
    }

    /**
     * @return the students
     */
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * @param students the students to set
     */
    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    /**
     * @return the thesisScores
     */
    public Set<ThesisCriteria> getThesisCriterias() {
        return thesisCriterias;
    }

    /**
     * @param thesisScores the thesisScores to set
     */
    public void setThesisScores(Set<ThesisCriteria> thesisCriterias) {
        this.setThesisCriterias(thesisCriterias);
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

    /**
     * @return the fileUrl
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * @param fileUrl the fileUrl to set
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    /**
     * @param thesisCriterias the thesisCriterias to set
     */
    public void setThesisCriterias(Set<ThesisCriteria> thesisCriterias) {
        this.thesisCriterias = thesisCriterias;
    }

    /**
     * @return the createdDate
     */
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the file
     */
    public MultipartFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(MultipartFile file) {
        this.file = file;
    }
    
}
