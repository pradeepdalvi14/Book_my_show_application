package com.acciojob.Book_my_show_application.Controllers;

import com.acciojob.Book_my_show_application.Models.User;
import com.acciojob.Book_my_show_application.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("addUser")
    public String addUser(@RequestBody User user){
     String  response = userService.addUser(user);
     return response;
    }
}
