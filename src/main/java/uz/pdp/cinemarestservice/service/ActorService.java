package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Actor;
import uz.pdp.cinemarestservice.model.Attachment;
import uz.pdp.cinemarestservice.model.AttachmentContent;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.ActorRepository;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.cinemarestservice.repository.AttachmentRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ActorService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;
    private final ActorRepository actorRepository;

    public ApiResponse getAllActors() {
        List<Actor> actorList = actorRepository.findAll();
        if (actorList.size() == 0) {
            return new ApiResponse("List empty!!!", false);
        }
        return new ApiResponse("Success!", true, actorList);
    }


    public ApiResponse getActorById(UUID id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Distribut not found!!!", false);
        }
        Actor actor = optionalActor.get();
        return new ApiResponse("Success!", true, actor);
    }


    public ApiResponse addActor(MultipartFile file, Actor actor) {
        try {
            Attachment newAttachment = attachmentRepository.save(new Attachment(
                            file.getOriginalFilename(),
                            file.getContentType(),
                            file.getSize()
                    )
            );
            AttachmentContent newAttachmentContent = contentRepository.save(new AttachmentContent(file.getBytes(), newAttachment));
            Actor newActor = actorRepository.save(new Actor(actor.getFullName(), actor.getBio(), newAttachment));
            return new ApiResponse("Success!", true, newActor);
        } catch (IOException e) {
            return new ApiResponse("Error!!!", false);
        }
    }


    public ApiResponse editActor(UUID id, MultipartFile file, Actor actor) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Actor not found!!!", false);
        }
        try {
            Actor editActor = optionalActor.get();
            editActor.setFullName(actor.getFullName());
            editActor.setBio(actor.getBio());
            if (file.isEmpty()) {
                Actor saveActor = actorRepository.save(editActor);
                return new ApiResponse("Success!", true, saveActor);
            }
            Attachment editAttachment = editActor.getActorPhoto();
            editAttachment.setContentType(file.getContentType());
            editAttachment.setSize(file.getSize());
            editAttachment.setOriginalName(file.getOriginalFilename());
            Attachment saveAttachment = attachmentRepository.save(editAttachment);

            AttachmentContent editAttachmentContent = contentRepository.findByAttachmentId(saveAttachment.getId());
            editAttachmentContent.setBytes(file.getBytes());
            editAttachmentContent.setAttachment(saveAttachment);
            contentRepository.save(editAttachmentContent);
            editActor.setActorPhoto(saveAttachment);
            Actor saveActor = actorRepository.save(editActor);
            return new ApiResponse("Success!", true, saveActor);

        } catch (IOException e) {
            return new ApiResponse("Error!!!", false);
        }

    }


    public ApiResponse deleteActor(UUID id) {
        Optional<Actor> optionalActor = actorRepository.findById(id);
        if (!optionalActor.isPresent()) {
            return new ApiResponse("Actor not found!!!", false);
        }
        try {
            Actor deleteActor = optionalActor.get();
            Attachment attachment = deleteActor.getActorPhoto();
            AttachmentContent attachmentContent = contentRepository.findByAttachmentId(attachment.getId());
            contentRepository.deleteById(attachmentContent.getId());
            attachmentRepository.deleteById(attachment.getId());
            actorRepository.deleteById(deleteActor.getId());
            return new ApiResponse("Success!", true);
        } catch (Exception e) {
            return new ApiResponse("Error!!!", false);
        }
    }
}
