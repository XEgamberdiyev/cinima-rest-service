package uz.pdp.cinemarestservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.cinemarestservice.dtos.TicketDto;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.service.TransactionService;

@RestController
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService ptransactionService;


    @RequestMapping("/api/transaction")
    public HttpEntity<?> createStripeSession() {
        ApiResponse apiResponse = ptransactionService.createStripeSession();
        return ResponseEntity.status(apiResponse.isStatus() ? 200 : 204).body(apiResponse);
    }

    @RequestMapping(value = "/api/refund", method = RequestMethod.POST)
    public HttpEntity<?> createStripeRefund(@RequestBody TicketDto ticketDto) {
        return ptransactionService.refundTicket(ticketDto.getTicketId());
    }

}
