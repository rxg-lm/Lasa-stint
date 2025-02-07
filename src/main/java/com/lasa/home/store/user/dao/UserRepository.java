package com.lasa.home.store.user.dao;


import com.lasa.home.store.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUname(String uname);
}
