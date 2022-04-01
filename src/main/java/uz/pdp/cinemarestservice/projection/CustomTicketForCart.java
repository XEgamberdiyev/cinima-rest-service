package uz.pdp.cinemarestservice.projection;

import uz.pdp.cinemarestservice.model.Ticket;

import java.util.UUID;

public interface CustomTicketForCart {

    UUID getId();

    Ticket getStatus();
}
