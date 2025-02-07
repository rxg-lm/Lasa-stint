package com.lasa.home.store.user.service;

import com.lasa.home.store.user.dao.UserRepository;
import com.lasa.home.store.user.entity.User;
import com.lasa.home.store.user.model.UserModel;
import com.lasa.home.store.user.util.AppUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository repository;

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }

    public UserModel loginByName(String username, String password) {
        User user = repository.findByUname(username);
        UserModel userModel = null;
         if(user != null) {
             userModel = (UserModel) AppUtil.createInstanceBySourceAndTargetType(user, UserModel.class);
         }
        return userModel;
    }

    public String registerUser(UserModel userModel) {
        User user = repository.findByUname(userModel.getUname());
        if (user!=null) {
            return "exist";
        }else {
            user = repository.save((User) AppUtil.createInstanceBySourceAndTargetType(userModel, User.class));
            return "success";
        }
    }

    /*
    public List<UserModel> viewRequest() {
        List<User> users = repository.findAll();
        List<UserModel> usersList = new ArrayList<>();

        users.stream().forEach(user -> {
            if(!user.getName().isEmpty() && !user.getEmail().isEmpty()) {
                usersList.add((UserModel) AppUtil.createInstanceBySourceAndTargetType(user, UserModel.class));
            }});

        return usersList;
    }*/
}
