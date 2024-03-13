/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.baitaplon;

import com.ltjava.pojo.User;
import com.ltjava.service.UserService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 *
 * @author HIEN
 */
public class LoginSuccessHandler implements AuthenticationSuccessHandler{
    @Autowired
    private UserService userService;
    
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        User u = this.userService.getUserByUsername(authentication.getName());
        System.out.println("HISFDI"+ u.getUsername());
        request.getSession().setAttribute("currentUser", u);
        
        response.sendRedirect("/baitaplon/");
    }
    
}
