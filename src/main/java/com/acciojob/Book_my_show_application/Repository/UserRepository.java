package com.acciojob.Book_my_show_application.Repository;

import com.acciojob.Book_my_show_application.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    public User findUserByMobNo(String mobNo);
}
