package com.lasa.home.traveller.makemytrip.entity;

import lombok.Data;

@Data
public class PassangerTicket {
    private String ticketNo;
    private String trainName;
    private String source;
    private String destination;
    private Double price;
    private String journeyDate;
    private String duration;
    private String passangerName;
    private Long phNo;
    private Integer age;
    private String gender;
    private String status;
}
