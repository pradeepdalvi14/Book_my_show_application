package com.acciojob.Book_my_show_application.Service;

import com.acciojob.Book_my_show_application.Models.User;
import com.acciojob.Book_my_show_application.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender javaMailSender;
    public String addUser(User user){
         user = userRepository.save(user);


        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Welcome to book your show application build by PRADEEP DALVI");
        message.setFrom("acciojob368@gmail.com");
        message.setTo(user.getEmailId());
        String body = "Hi "+user.getName()+"! "+"\n"+
                "Welcome to Book your show Application !! , Feel free " +
                "to browse the movies and use Coupon START100 for an instant discount";

        message.setText(body);
        javaMailSender.send(message);



        return "User has been saved to the DB with userId "+user.getUserId();
    }

}
