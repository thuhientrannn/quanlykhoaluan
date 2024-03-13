/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ltjava.service;

import com.ltjava.pojo.User;
import com.ltjava.pojo.UserRole;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author HIEN
 */
public interface UserService extends UserDetailsService{
    List<User> getUsers(String kw, String userRole);
    User getUserById(String id);
    User getUserByUsername(String name);
    boolean addOrUpdate(User user);
    boolean changePassword(User user, String pass);
    boolean updateUser(User oldUser, User newUser);
    boolean removeUser(User u);
    String loadNewUserId(Integer u);
}
