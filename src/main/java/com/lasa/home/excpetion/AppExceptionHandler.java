package com.lasa.home.excpetion;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = UserDataInvalidException.class)
    public String handleUserDataInvalidExcpetion(UserDataInvalidException udie, Model model) {
        ExceptionInfo exceptionInfo = new ExceptionInfo();
        exceptionInfo.setCode("EX-100");
        exceptionInfo.setMsg(udie.getMessage());

        model.addAttribute("exception", exceptionInfo);
        return "ticket";
    }
}
