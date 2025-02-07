package com.lasa.home.store.user.controller;


import com.lasa.home.store.user.model.UserModel;
import com.lasa.home.store.user.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    private UserService service;

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    @GetMapping(value = "/")
    public String init(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        session.setAttribute("contextPath",contextPath);
        return "home";
    }

    @GetMapping(value = "/login")
    public String loginPage(HttpSession session,Model model) {
        if(session.getAttribute("username") != null){
            return "redirect:/success";
        }
        return "login";
    }
    @GetMapping(value = "/logout")
    public String logoutpage(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, HttpSession session,Model model) {
        if(username != null && !username.isEmpty()) {
            UserModel user = (UserModel) service.loginByName(username, password);
            if (user != null && user.getUname().equals(username) && user.getPassword().equals(password)) {
                session.setAttribute("username", username);
                session.setAttribute("user", user);
                return "redirect:/success";
            }
        }
        model.addAttribute("error", "Invalid credentials entered!... ");
        return "login";
    }

    @GetMapping(value = "/register")
    public String registerPage(Model model) {
        model.addAttribute("userModel",new UserModel());
        return "register";
    }

    @PostMapping(value = "/register")
    public String handleRegisterUser(@Valid UserModel userModel, BindingResult result, Model model) {
        String status = service.registerUser(userModel);
        if (result.hasErrors()) {
            model.addAttribute("msg","Something went wrong!");
            return "register";
        }
        if (status != null && status.equals("success")) {
            model.addAttribute("msg", "User Registered successfully. ");
        } else {
            model.addAttribute("msg", "User already exist.");
        }
        return "redirect:/login";
    }
    @GetMapping(value = "/success")
    public String getSuccess(HttpSession session, Model model){
        String username = (String) session.getAttribute("username");
        if (username == null) {
            return "redirect:/login";
        }
        model.addAttribute("user",session.getAttribute("user"));
        return "success";
    }
    /*@GetMapping("/view")
    public String handleViewRequest(Model model){
        List<UserModel> users = service.viewRequest();
        if (users.isEmpty()){
            model.addAttribute("error","No Records Fetch");
        }else{
            model.addAttribute("users",users);
            model.addAttribute("size",users.size());
        }
        model.addAttribute("contextPath",contextPath);
        return "view";
    }*/
}
