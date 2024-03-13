/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;
// Importing required classes
import com.ltjava.pojo.EmailDetails;
import com.ltjava.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
/**
 *
 * @author HIEN
 */
//@RestController
//public class EmailController {
//    @Autowired 
//    private EmailService emailService;
// 
//    // Sending a simple Email
//    @PostMapping("/sendMail")
//    public String sendMail(@RequestBody EmailDetails details)
//    {
//        String status
//            = emailService.sendSimpleMail(details);
// 
//        return status;
//    }
// 
//    // Sending email with attachment
//    @PostMapping("/sendMailWithAttachment")
//    public String sendMailWithAttachment(
//        @RequestBody EmailDetails details)
//    {
//        String status
//            = emailService.sendMailWithAttachment(details);
// 
//        return status;
//    }
//}
