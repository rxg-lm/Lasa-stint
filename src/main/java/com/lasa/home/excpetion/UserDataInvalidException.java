package com.lasa.home.excpetion;

import org.springframework.stereotype.Component;

@Component
public class UserDataInvalidException extends RuntimeException {
    public UserDataInvalidException() {
        super();
    }
    public UserDataInvalidException(String msg) {
        super(msg);
    }

}
