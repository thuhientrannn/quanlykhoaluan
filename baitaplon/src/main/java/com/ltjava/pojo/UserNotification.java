/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

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
@Table(name = "user_notification")
public class UserNotification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "created_date")
    private Date createdDate;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User userId;
    @ManyToOne
    @JoinColumn(name = "noti_id", referencedColumnName = "id")
    private Notification notificationId;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status statusId;
    
    public UserNotification()
    {
        
    }
    
    public UserNotification(User user, Notification noti, Status s){
        this.userId = user;
        this.notificationId = noti;
        this.createdDate = Date.from(Instant.now());
        this.statusId = s;
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
     * @return the notification
     */
    public Notification getNotificationId() {
        return notificationId;
    }

    /**
     * @param notification the notification to set
     */
    public void setNotificationId(Notification notification) {
        this.notificationId = notification;
    }

    /**
     * @return the statusId
     */
    public Status getStatusId() {
        return statusId;
    }

    /**
     * @param statusId the statusId to set
     */
    public void setStatusId(Status statusId) {
        this.statusId = statusId;
    }
}
