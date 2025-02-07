package com.lasa.home.store.user.entity;


import com.lasa.home.store.user.model.UserModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "VerizonUser")
@Data
@NoArgsConstructor
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String uname;
    private String email;
    private String gender;
    private String phNumber;
    private String password;

    public User(UserModel userModel) {
        this.name = userModel.getName();
        this.email = userModel.getEmail();
        this.gender = userModel.getGender();
        this.uname = userModel.getUname();
        this.phNumber = userModel.getPhNumber();
        this.password = userModel.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", uname='" + uname + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                ", phNumber='" + phNumber + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
