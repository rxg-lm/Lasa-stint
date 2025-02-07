package com.lasa.home.store.user.model;


import com.lasa.home.store.user.entity.User;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@RequiredArgsConstructor
public class UserModel implements Serializable {
    @NotEmpty(message = "Name is Required!")
    private String name;
    @NotEmpty(message = "Email is Required!")
    private String email;
    @NotEmpty(message = "Gender selection Required!")
    private String gender;
    @NotEmpty(message = "Username Required!")
    private String uname;
    @NotEmpty(message = "phone number required Required!")
    private String phNumber;
    @NotEmpty(message = "password Required!")
    private String password;

    public UserModel(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.gender = user.getGender();
        this.uname = user.getUname();
        this.phNumber = user.getPhNumber();
        this.password = user.getPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", uname='" + uname + '\'' +
                ", phNumber='" + phNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
