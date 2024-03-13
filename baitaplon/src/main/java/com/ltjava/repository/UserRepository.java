/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.repository;

import com.ltjava.pojo.User;
import com.ltjava.pojo.UserRole;
import java.util.List;

/**
 *
 * @author HIEN
 */
public interface UserRepository {
    List<User> getUsers(String kw, String userRole);
    User getUserById(String id);
    User getUserByUsername(String name);
    boolean addOrUpdateUser(User user);
    boolean changePassword(User user, String pass);
    boolean updateUser(User oldUser, User newUser);
    boolean removeUser(User u);
    String loadNewUserId(Integer userRoleId);
    boolean updateUserId(User u);
}
