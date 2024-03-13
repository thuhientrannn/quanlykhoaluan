/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.Notification;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface NotificationRepository {
    List<Notification> getNotifications();
    Notification getNotificationById(Integer id);
    boolean addNotification(Notification noti);
}
