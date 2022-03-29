package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.Attachment;

public interface AttachmentRepo extends JpaRepository<Attachment, Integer> {
}
