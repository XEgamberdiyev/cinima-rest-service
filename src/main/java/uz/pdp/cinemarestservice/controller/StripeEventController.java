package uz.pdp.cinemarestservice.controller;

import com.stripe.Stripe;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import uz.pdp.cinemarestservice.service.TicketService;

import java.util.UUID;

@RestController
@RequestMapping("/api/stripe-webhook")
@RequiredArgsConstructor
public class StripeEventController {

    private final TicketService ticketService;

    @Value("${STRIPE_SK_KEY}")
    String stripeApiKey;
    @Value("${WEBHOOK_SECRET_KEY}")
    String endpointSecret;

    @PostMapping
    public Object handle(@RequestBody String payload, @RequestHeader(name = "Stripe-Signature") String sigHeader) {

        Stripe.apiKey = stripeApiKey;
        Event event = null;

        try {
            event = Webhook.constructEvent(payload, sigHeader, endpointSecret);
        } catch (SignatureVerificationException e) {
            e.printStackTrace();

            return "";
        }

        if ("checkout.session.completed".equals(event.getType())) {
            Session session = (Session) event.getDataObjectDeserializer().getObject().get();

            // Fulfill the ptransaction...
            fulfillOrder(session);

        }
        System.out.println("Got payload!" + payload);

        return "";
    }

    public void fulfillOrder(Session session) {

        System.out.println("Current User id - " + session.getClientReferenceId());

        String currentUserId = session.getClientReferenceId();

        ticketService.addTransactionHistory(UUID.fromString(currentUserId), session.getPaymentIntent());

        ticketService.changeTicketStatusToTransaction(UUID.fromString(currentUserId));
    }
}
