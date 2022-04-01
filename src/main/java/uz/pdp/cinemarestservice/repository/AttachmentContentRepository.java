package uz.pdp.cinemarestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.cinemarestservice.model.AttachmentContent;

import java.util.UUID;

public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {
    AttachmentContent findByAttachmentId(UUID attachment_id);
}
