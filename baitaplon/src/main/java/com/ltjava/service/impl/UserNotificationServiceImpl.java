/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.User;
import com.ltjava.pojo.UserNotification;
import com.ltjava.repository.UserNotificationRepository;
import com.ltjava.service.UserNotificationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author HIEN
 */
@Service
public class UserNotificationServiceImpl implements UserNotificationService{
    @Autowired
    private UserNotificationRepository userNotificationRepository;
    
    @Override
    public List<UserNotification> getUserNotifications(User user) {
        return this.userNotificationRepository.getUserNotifications(user);
    }

    @Override
    public UserNotification getUserNotificationById(Integer id) {
        return this.userNotificationRepository.getUserNotificationById(id);
    }

    @Override
    public boolean addUserNotification(UserNotification un) {
        return this.userNotificationRepository.addUserNotification(un);
    }
    
}
