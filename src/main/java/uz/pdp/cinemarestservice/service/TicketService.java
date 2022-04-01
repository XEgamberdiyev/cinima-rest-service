package uz.pdp.cinemarestservice.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.cinemarestservice.model.Attachment;
import uz.pdp.cinemarestservice.model.AttachmentContent;
import uz.pdp.cinemarestservice.model.Ticket;
import uz.pdp.cinemarestservice.model.TransactionHistory;
import uz.pdp.cinemarestservice.model.enums.TicketStatus;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.projection.TicketProjection;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.cinemarestservice.repository.AttachmentRepository;
import uz.pdp.cinemarestservice.repository.TicketRepository;
import uz.pdp.cinemarestservice.repository.TransactionHistoryRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class TicketService {

    private final TicketRepository ticketRepository;
    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;
    private final TransactionHistoryRepository historyRepository;

    public ApiResponse transactionTicket(UUID ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            Attachment attachment = getQrCodeAttachment(ticketId);
            ticket.setQrCode(attachment);
            ticket.setStatus(TicketStatus.TRANSACTION);
            ticketRepository.save(ticket);
            return new ApiResponse("Success!",true,attachment.getId());
        }
        return new ApiResponse("Not Added!!!", false);
    }

    public Attachment getQrCodeAttachment(UUID ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            try {
                byte[] qrCodeImage = new byte[0];
                qrCodeImage = getQrCodeImage(ticket.getId().toString(), 200, 200);
                Attachment attachment = new Attachment();
                attachment.setOriginalName(ticket.getId().toString());
                attachment.setContentType("image/png");
                AttachmentContent attachmentContent = new AttachmentContent(qrCodeImage, attachment);
                attachmentRepository.save(attachment);
                contentRepository.save(attachmentContent);
                return attachment;
            } catch (IOException | WriterException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public byte[] getQrCodeImage(String text, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix,"PNG",pngOutputStream);
        return pngOutputStream.toByteArray();
    }

    public HttpEntity<?> getCurrentUserTickets(UUID userId) {
        List<TicketProjection> allTickets= ticketRepository.getTicketsByUserId(userId);
        return ResponseEntity.ok(allTickets);
    }

    public void changeTicketStatusToTransaction(UUID userId){
        // TODO: 31.03.2022 user id boicha new ticketlar olinadi va transaction ga ozgartriladi!!!

        List<Ticket> ticketsList = ticketRepository.findByUserIdAndAndStatus(userId, TicketStatus.NEW);

        for (Ticket ticket : ticketsList) {
            ticket.setStatus(TicketStatus.TRANSACTION);
        }
        ticketRepository.saveAll(ticketsList);
    }

    public void addTransactionHistory(UUID userId, String paymentIntent){
        List<Ticket> ticketsList = ticketRepository.findByUserIdAndAndStatus(userId, TicketStatus.NEW);

        TransactionHistory transactionHistory = new TransactionHistory(ticketsList, null, paymentIntent);
        historyRepository.save(transactionHistory);
    }


}
