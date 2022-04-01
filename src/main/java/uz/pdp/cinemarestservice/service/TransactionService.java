package uz.pdp.cinemarestservice.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Refund;
import com.stripe.model.checkout.Session;
import com.stripe.param.RefundCreateParams;
import com.stripe.param.checkout.SessionCreateParams;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestservice.model.Ticket;
import uz.pdp.cinemarestservice.model.TransactionHistory;
import uz.pdp.cinemarestservice.model.User;
import uz.pdp.cinemarestservice.model.enums.TicketStatus;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.projection.TicketProjection;
import uz.pdp.cinemarestservice.repository.TicketRepository;
import uz.pdp.cinemarestservice.repository.TransactionHistoryRepository;
import uz.pdp.cinemarestservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TransactionService {

    private final TransactionHistoryRepository historyRepository;
    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;
    @Value("${STRIPE_SK_KEY}")
    String stripeApiKey;

    public ApiResponse createStripeSession() {
        Stripe.apiKey = stripeApiKey;

//=============== KEYINCHALIK @CURRENTUSER GA O'ZGARADI

        User currentUser = userRepository.findByUserName("test");

//===============================
        List<TicketProjection> allTickets = ticketRepository.getTicketsByUserId(currentUser.getId());


        List<SessionCreateParams.LineItem> lineItems = new ArrayList<>();

        for (TicketProjection ticket : allTickets) {
            String movieTitle = ticket.getTitle();
            Double ticketPrice = ticket.getPrice();


            SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData
                    .builder()
                    .setName(movieTitle)
                    .build();

            SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData
                    .builder()
                    .setProductData(productData)
                    .setCurrency("usd")
                    .setUnitAmount((long) (ticketPrice * 100))
                    .build();

            SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem
                    .builder()
                    .setPriceData(priceData)
                    .setQuantity(1L)
                    .build();


            lineItems.add(lineItem);

        }

        SessionCreateParams params = SessionCreateParams
                .builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setCancelUrl("http://localhost:8080/failed")
                .setSuccessUrl("http://localhost:8080/success?userId=" + currentUser.getId())
                .setClientReferenceId(currentUser.getId().toString())
                .addAllLineItem(lineItems)

                .build();
        try {
            Session session = Session.create(params);
            String checkoutUrl = session.getUrl();


            //returning checkout url of session
            return new ApiResponse("success!", true, checkoutUrl);

        } catch (StripeException e) {
            e.printStackTrace();
        }
        return new ApiResponse("error!", false);

    }

    public HttpEntity<?> refundTicket(UUID ticketId) {

        Stripe.apiKey = stripeApiKey;



        try {
            TransactionHistory transactionHistoryByTicketId = historyRepository.findTransactionHistoryByTicketId(ticketId).orElseThrow(() ->
                    new IllegalStateException("Not found"));
            String stripePaymentIntent = transactionHistoryByTicketId.getStripePaymentIntent();

            RefundCreateParams params = RefundCreateParams
                    .builder()
                    .setPaymentIntent(stripePaymentIntent)
                    .build();

            Refund refund = Refund.create(params);
            System.out.println(refund.getStatus());

            if (refund.getStatus().equals("succeeded")) {
                Optional<Ticket> byId = ticketRepository.findById(ticketId);
                Ticket ticket = byId.get();
                ticket.setStatus(TicketStatus.REFUNDED);
                ticketRepository.save(ticket);
                // TODO: 31.03.2022 TRANSACTION BOLGAN STATUSNI REFUNDGA OZGARTIRISH!
            }
            return ResponseEntity.ok(refund.getStatus());
        } catch (StripeException e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().build();
    }
}
