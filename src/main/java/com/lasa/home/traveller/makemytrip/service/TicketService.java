package com.lasa.home.traveller.makemytrip.service;

import com.lasa.home.traveller.makemytrip.entity.PassangerTicket;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class TicketService {

    @Value("${irctc.endpoint.book_ticket}")
    private String IRCTC_ENDPOINT_BOOKTICKET;
    @Value("${irctc.endpoint.view_ticket}")
    private String IRCTC_ENDPOINT_VIEWTICKET;
    @Value("${irctc.endpoint.view_all_ticketS}")
    private String IRCTC_END_POINT_VIEWALLTICKETS;

    public ResponseEntity<String> ticketBookInit(PassangerTicket passanger) {

        /*RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(IRCTC_ENDPOINT_BOOKTICKET, passanger, String.class);

        if (responseEntity.getStatusCodeValue() == 201) {
            return responseEntity;
        }*/
        WebClient webClient = WebClient.create();
        String response = webClient.post()
                .uri(IRCTC_ENDPOINT_BOOKTICKET)
                .bodyValue(passanger)
                .accept(MediaType.TEXT_PLAIN)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        if(!response.isEmpty()){
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<PassangerTicket> ticketInfo(String ticketNo) {

        /*RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<PassangerTicket> responseEntity = restTemplate.postForEntity(IRCTC_ENDPOINT_VIEWTICKET, ticketNo, PassangerTicket.class);
        if (responseEntity.getStatusCodeValue() == 200) {
            return responseEntity;
        }*/
        WebClient webClient = WebClient.create();
        PassangerTicket passangerTicket = webClient.post()
                .uri(IRCTC_ENDPOINT_VIEWTICKET)
                .bodyValue(ticketNo)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PassangerTicket.class)
                .block();
        if (passangerTicket != null) {
            return new ResponseEntity<>(passangerTicket, HttpStatus.OK);
        }
        return null;
    }

    public ResponseEntity<List<PassangerTicket>> allTicketInfo() {
        /*RestTemplate restTemplate = new RestTemplate();
        PassangerTicket[] passangerTickets = restTemplate.getForEntity(IRCTC_END_POINT_VIEWALLTICKETS, PassangerTicket[].class).getBody();
        List<PassangerTicket> ticketList = Arrays.stream(passangerTickets).toList();
        System.out.println(ticketList);
        if (!ticketList.isEmpty()) {
            return new ResponseEntity<>(ticketList, HttpStatus.OK);
        }*/
        WebClient webClient = WebClient.create();
        PassangerTicket[] passangerTickets = webClient.get()
                .uri(IRCTC_END_POINT_VIEWALLTICKETS)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(PassangerTicket[].class)
                .block();
        List<PassangerTicket> passangerTicketList = Arrays.asList(passangerTickets);
        if (!passangerTicketList.isEmpty()) {
            return new ResponseEntity<>(passangerTicketList, HttpStatus.OK);
        }
        return null;
    }
}
