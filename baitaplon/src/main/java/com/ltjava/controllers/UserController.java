/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ltjava.controllers;

import com.ltjava.pojo.Student;
import com.ltjava.pojo.Teacher;
import com.ltjava.pojo.User;
import com.ltjava.pojo.UserRole;
import com.ltjava.pojo.Word;
import com.ltjava.service.MajorService;
import com.ltjava.service.StatsService;
import com.ltjava.service.StudentService;
import com.ltjava.service.TeacherService;
import com.ltjava.service.UserRoleService;
import com.ltjava.service.UserService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HIEN
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleService userRoleService;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @Autowired
    private MajorService majorService;
    
    @Autowired
    private StatsService statsService;
    
    @Autowired
    private StudentService studentService;
    
    @Autowired
    private TeacherService teacherService;
    
    
    @ModelAttribute
    public void commonAttr(Model model){
        model.addAttribute("listUserRole", this.userRoleService.getUserRoles());
        model.addAttribute("listMajor", this.majorService.getMajors(""));
        model.addAttribute("userStats", this.statsService.countUserByUserRole());
    }
    
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    
    @RequestMapping("/user")
    public String user(Model model, @ModelAttribute("userInfo") User userInfo,
            @RequestParam(value = "kw", required = false, defaultValue = "") String kw,
            @RequestParam(value = "userRoleName", required = false, defaultValue = "") String userRoleName){
        model.addAttribute("user",userService.getUsers(kw, userRoleName));
        System.out.println(kw+ userRoleName);
        return "user";
    }
    
//    @PostMapping(value = "/user")
//    public String addUser(@ModelAttribute(value = "userInfo") @Valid User userInfo, 
//            BindingResult result){
//        if(!result.hasErrors()){
//            this.userService.addOrUpdate(userInfo);
//            return "redirect:/user";
//        }
//        return "user";
//    }
    
    @RequestMapping("/user/{userId}/password")
    public String password(Model model, 
            @PathVariable(value = "userId") String userId){
        return "password";
    }
    
    @PostMapping("/user/{userId}/password")
    public String changePassword(Model model,@PathVariable(value = "userId") String userId, 
            @RequestParam(value = "oldPassword", required = false, defaultValue = "") String oldPassword, 
            @RequestParam(value="newPassword", required = false, defaultValue = "") String newPassword){
        String msg = "";
        if(passwordEncoder.matches(oldPassword, userService.getUserById(userId).getPassword())){
            userService.changePassword(userService.getUserById(userId), newPassword);
            msg = "ĐỔI MẬT KHẨU THÀNH CÔNG";
        }
        else
            msg = "MẬT KHẨU CŨ KHÔNG KHỚP";
        System.out.println(msg);
        model.addAttribute("msg", msg);
        return "password";
    }
    
    @RequestMapping("/user/account/{userId}")
    public String account(Model model, @PathVariable(value = "userId") String userId){
        model.addAttribute("userPage", userService.getUserById(userId));
        return "account";
    }
    
    @GetMapping("/user/account/edit/{userId}")
    public String userEdit(Model model, @PathVariable(value = "userId") String id, @ModelAttribute(value = "userInfo") User u){
        User user = this.userService.getUserById(id);
        model.addAttribute("userEdit", user );
        return "useritem";
    }
    
    @PostMapping("/user/account/edit/{userId}")
    public String edit(Model model, @PathVariable(value = "userId") String id, @RequestParam Map<String, String> params){
        try{
            String firstName = params.get("urFirstName");
            String lastName = params.get("urLastName");
            String email = params.get("urEmail");
            String phoneNumber = params.get("urPhoneNumber");
            UserRole userRole = this.userRoleService.getUserRole(Integer.parseInt(params.get("urUserRoleId")));
            String username = params.get("urUsername");
            User user = this.userService.getUserById(id);
            if(user.getUserRole().getId()<4){
                Teacher teacher = user.getTeacherId();
                teacher.setFirstName(firstName);
                teacher.setLastName(lastName);
                teacher.setPhoneNumber(phoneNumber);
                teacher.setEmail(email);
                teacher.setMajorId(this.majorService.getMajorById(Integer.parseInt(params.get("urMajorId"))));
                this.teacherService.addOrUpdateTeacher(teacher);
            }
            else{
                Student student = user.getStudentId();
                student.setFirstName(firstName);
                student.setLastName(lastName);
                student.setEmail(email);
                student.setPhoneNumber(phoneNumber);
                this.studentService.addOrUpdateStudent(student);
            }
            user.setUserRole(userRole);
            user.setUsername(username);
            if(this.userService.addOrUpdate(user)){
                model.addAttribute("msgSuccess", "CAP NHAT THONG TIN THANH CONG");
            }
        }catch(Exception e){
            System.out.print(e.getMessage());
            model.addAttribute("msgErr", "CAP NHAT THAT BAII");
        }
        return "redirect:/user/account/edit/"+id;
    }
    
    @GetMapping("/user/account/remove/{userId}")
    public String edit(Model model, @PathVariable(value = "userId") String id){
        try{
            userService.removeUser(userService.getUserById(id));
        }catch(Exception e){
            System.out.print(e.getMessage());
        }
        return "redirect:/user";
    }
    
    @GetMapping("/user/lock/{userId}")
    public String lockUser(Model model, @PathVariable(value = "userId") String id){
        try{
            User user = this.userService.getUserById(id);
            user.setActive(false);
            if(this.userService.addOrUpdate(user)){
                model.addAttribute("msgSuccess", "Khoa tai khoan thanh cong");
            }
        }catch(Exception e){
            System.out.print(e.getMessage());
            model.addAttribute("msgErr", "Khoa tai khoan that bai");
        }
        return "redirect:/user/account/edit/"+id;
    }
    
    @GetMapping("/user/unlock/{userId}")
    public String unlockUser(Model model, @PathVariable(value = "userId") String id){
        try{
            User user = this.userService.getUserById(id);
            user.setActive(true);
            if(this.userService.addOrUpdate(user)){
                model.addAttribute("msgSuccess", "Mo khoa tai khoan thanh cong");
            }
        }catch(Exception e){
            System.out.print(e.getMessage());
            model.addAttribute("msgSuccess", "Mo khoa tai khoan that bai");
        }
        return "redirect:/user/account/edit/"+id;
    }
    
    @GetMapping(value = "/user/resetPassword/{userId}")
    public String resetPassword(Model model, @PathVariable(value = "userId") String userId){
        try{
            User user = this.userService.getUserById(userId);
            user.setPassword("00000000");
            if(this.userService.addOrUpdate(user)){
                System.out.println("RESET MK THÀNH CÔNG");
                model.addAttribute("msgSuccess", "Password is reseted! Please check your email ~~");
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("RESET MK THẤT BẠI");
            model.addAttribute("msgErr", "Error! Something went wrong ~~");
        }
        return "redirect:/user/account/edit/"+userId;
    }
}
