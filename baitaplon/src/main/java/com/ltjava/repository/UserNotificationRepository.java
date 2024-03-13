/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.User;
import com.ltjava.pojo.UserNotification;
import java.util.List;

/**
 *
 * @author HIEN
 */

public interface UserNotificationRepository {
    List<UserNotification> getUserNotifications(User user);
    UserNotification getUserNotificationById(Integer id);
    boolean addUserNotification(UserNotification noti);
}
