package com.lasa.home.traveller.makemytrip.controller;

import com.lasa.home.excpetion.UserDataInvalidException;
import com.lasa.home.traveller.makemytrip.entity.PassangerTicket;
import com.lasa.home.traveller.makemytrip.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/ticket")
public class TicketController {

    @Value("${server.servlet.context-path}")
    private String contextPath;
    @Autowired
    private TicketService service;

    @GetMapping(value = "/")
    public String init(Model model) {
        model.addAttribute("passanger", new PassangerTicket());
        model.addAttribute("contextPath", contextPath + "/ticket/view");
        return "ticket";
    }

    @PostMapping(value = "/booking")
    public String bookTicket(PassangerTicket passangerTicket, Model model){
        try {
            if (passangerTicket.getPhNo() != null) {
                ResponseEntity<String> response = service.ticketBookInit(passangerTicket);
                model.addAttribute("msg", response.getBody());
                model.addAttribute("contextPath", contextPath + "/ticket/view");
                return "success";
            }
        }catch (RuntimeException e){
            throw new UserDataInvalidException("Provide Data Invalid or Data Empty");
        }
        return "ticket";
    }

    @GetMapping(value = "/view")
    public String viewTicketInfo(Model model) {
        model.addAttribute("dataCheck", "null");
        model.addAttribute("display", "true");
        model.addAttribute("passangers", null);
        model.addAttribute("contextPath", contextPath + "/ticket/");
        model.addAttribute("viewTicketStatus", "false");
        model.addAttribute("contextPathViewAll", contextPath + "/ticket/view/all");
        return "view";
    }

    @PostMapping(value = "/view")
    public String viewTciket(@RequestParam("ticketId") String ticketId, Model model) {
        List<PassangerTicket> passangerTicketList = null;
        if (!ticketId.isEmpty()) {
            passangerTicketList = new ArrayList<>();
            ResponseEntity<PassangerTicket> response = service.ticketInfo(ticketId);
            passangerTicketList.add((PassangerTicket) response.getBody());
            model.addAttribute("passangers", passangerTicketList);
        } else {
            model.addAttribute("passangers", null);
        }
        model.addAttribute("dataCheck", "true");
        model.addAttribute("display", "true");
        model.addAttribute("contextPath", contextPath + "/ticket/");
        model.addAttribute("contextPathViewAll", contextPath + "/ticket/view/all");
        return "view";
    }

    @GetMapping(value = "/view/all")
    public String viewAllTickets(Model model) {
        ResponseEntity<List<PassangerTicket>> response = service.allTicketInfo();
        List<PassangerTicket> passangerTicketList = response.getBody();
        if (!passangerTicketList.isEmpty()) {
            model.addAttribute("passangers", passangerTicketList);
        } else {
            model.addAttribute("passangers", null);
        }
        model.addAttribute("dataCheck", "true");
        model.addAttribute("display", "false");
        model.addAttribute("viewTicketStatus", "true");
        model.addAttribute("contextPath", contextPath + "/ticket/");
        model.addAttribute("contextPathView", contextPath + "/ticket/view");
        return "view";
    }
}
