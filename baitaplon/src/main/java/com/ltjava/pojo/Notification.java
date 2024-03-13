/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author HIEN
 */
@Entity
@Table(name = "notification")
public class Notification implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    @JsonIgnore
    @OneToMany(mappedBy = "notificationId", fetch = FetchType.EAGER)
    private Set<UserNotification> userNotifications;

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
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
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
