/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.service.impl;

import com.ltjava.pojo.Student;
import com.ltjava.pojo.User;
import com.ltjava.pojo.UserRole;
import com.ltjava.repository.StudentRepository;
import com.ltjava.repository.UserRepository;
import com.ltjava.service.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author HIEN
 */
@Service("userDetailsService")
@Transactional
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Override
    public List<User> getUsers(String kw, String userRole) {
        return this.userRepository.getUsers(kw, userRole);
    }

    @Override
    public User getUserById(String id) {
        return this.userRepository.getUserById(id);
    }

    @Override
    public boolean addOrUpdate(User user) {
        String pass = user.getPassword();
        user.setPassword(this.passwordEncoder.encode(pass));
        if(this.userRepository.addOrUpdateUser(user)){
            return this.userRepository.updateUserId(user);
        }
        else{
            return false;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = this.getUsers(username, "");
        
        if(users.isEmpty()){
            throw new UsernameNotFoundException("Không tìm thấy username");
        }
        User u = users.get(0);
        System.out.println(u.getUsername());
        
        Set<GrantedAuthority> auth = new HashSet<>();
        auth.add(new SimpleGrantedAuthority(u.getUserRole().getName()));
        System.out.println("CHECK ROLE");
        System.out.println(auth.size());
        System.out.println(auth.iterator());
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(), u.getPassword(), auth);
    }

    @Override
    public boolean changePassword(User user, String string) {
        return this.userRepository.changePassword(user, this.passwordEncoder.encode(string));
    }

    @Override
    public User getUserByUsername(String string) {
        return this.userRepository.getUserByUsername(string);
    }

    @Override
    public boolean updateUser(User oldUser, User newUser) {
        return this.userRepository.updateUser(oldUser, newUser);
    }

    @Override
    public boolean removeUser(User user) {
        return this.userRepository.removeUser(user);
    }

    @Override
    public String loadNewUserId(Integer ur) {
        return this.userRepository.loadNewUserId(ur);
    }
    
}
