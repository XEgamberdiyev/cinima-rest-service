package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Attachment;
import uz.pdp.cinemarestservice.model.AttachmentContent;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.cinemarestservice.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AttachmentService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository attachmentContentRepository;

    public ApiResponse fileUpload(MultipartFile file) {
        try {
            Attachment saveAttachment = attachmentRepository.save(new Attachment(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize()
            ));
            attachmentContentRepository.save(new AttachmentContent(file.getBytes(), saveAttachment));
            return new ApiResponse("Successfully Uploaded!!!", true);
        } catch (IOException e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ResponseEntity<ByteArrayResource> fileDownload(UUID attachment_id) {
        AttachmentContent attachmentContent = attachmentContentRepository.findByAttachmentId(attachment_id);
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(attachmentContent.getAttachment().getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=\"" + attachmentContent
                        .getAttachment().getOriginalName() + "\"")
                .body(new ByteArrayResource(attachmentContent.getBytes()));
    }

    public ApiResponse getAllAttachment() {
        List<Attachment> attachmentList = attachmentRepository.findAll();
        if (attachmentList.size() != 0) {
            return new ApiResponse("Success", true, attachmentList);
        }
        return new ApiResponse("List empty!", false);
    }

    public ApiResponse getAttachmentById(UUID id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found!", false);
        }
        return new ApiResponse("Success!", true, optionalAttachment.get());
    }

    public ApiResponse editAttachment(UUID id, MultipartFile file) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found!", false);
        }
        try {
            Attachment editAttachment = optionalAttachment.get();
            editAttachment.setContentType(file.getContentType());
            editAttachment.setOriginalName(file.getOriginalFilename());
            editAttachment.setSize(file.getSize());
            Attachment attachment = attachmentRepository.save(editAttachment);

            AttachmentContent editAttachmentContent = attachmentContentRepository.getById(editAttachment.getId());
            editAttachmentContent.setAttachment(attachment);
            editAttachmentContent.setBytes(file.getBytes());
            return new ApiResponse("Successfully edited!", true);
        } catch (IOException e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse deleteAttachment(UUID id) {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (!optionalAttachment.isPresent()) {
            return new ApiResponse("Attachment not found!!!", false);
        }
        try {
            AttachmentContent attachmentContent = attachmentContentRepository.getById(id);
            attachmentContentRepository.deleteById(attachmentContent.getId());
            attachmentRepository.deleteById(id);
            return new ApiResponse("Successfully deleted!", true);

        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }

}
