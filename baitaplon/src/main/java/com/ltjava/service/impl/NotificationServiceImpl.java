/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Notification;
import com.ltjava.repository.NotificationRepository;
import com.ltjava.service.NotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public List<Notification> getNotifications() {
        return this.notificationRepository.getNotifications();
    }

    @Override
    public Notification getNotificationById(Integer id) {
        return this.notificationRepository.getNotificationById(id);
    }

    @Override
    public boolean addNotification(Notification ntfctn) {
        return this.notificationRepository.addNotification(ntfctn);
    }
    
}
