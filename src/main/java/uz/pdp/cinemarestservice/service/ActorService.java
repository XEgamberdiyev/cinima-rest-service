package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Actor;
import uz.pdp.cinemarestservice.model.Attachment;
import uz.pdp.cinemarestservice.model.AttachmentContent;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.ActorRepo;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepo;
import uz.pdp.cinemarestservice.repository.AttachmentRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ActorService {

    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    AttachmentContentRepo attachmentContentRepo;

    @Autowired
    ActorRepo actorRepo;

    public ApiResponse getAllActors(){
        List<Actor> all = actorRepo.findAll();
        if (all.size() != 0){
            return new ApiResponse("Success", true, all);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse getActorById(Integer id){
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            return new ApiResponse("Success", true, actor);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse addActor(Actor actor, MultipartFile file) throws IOException {
//        Iterator<String> fileNames = request.getFileNames();
//        MultipartFile file = request.getFile(fileNames.next());

        if (file != null) {
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            Attachment attachment = new Attachment();
            attachment.setOriginalName(originalFilename);
            attachment.setSize(size);
            attachment.setContentType(contentType);
            Attachment saveAttachment = attachmentRepo.save(attachment);


            AttachmentContent attachmentContent = new AttachmentContent();
            attachmentContent.setBytes(file.getBytes());
            attachmentContent.setAttachment(saveAttachment);
            AttachmentContent saveAttachmentContent = attachmentContentRepo.save(attachmentContent);


            Actor actor1 = new Actor();
            actor1.setFullName(actor.getFullName());
            actor1.setPhoto(saveAttachment);
            Actor save = actorRepo.save(actor1);


            return new ApiResponse("Success", true, save);
        }

        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse updateActor(Integer id, Actor actor, MultipartFile file) throws IOException {
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor1 = optionalActor.get();

//            AttachmentContent attachmentContent1 = actor1.getPhoto();
//            Attachment attachment1 = attachmentContent1.getAttachment();

            Attachment attachment = actor1.getPhoto();

            Optional<Attachment> optionalAttachment = attachmentRepo.findById(attachment.getId());
            if (optionalAttachment.isPresent()) {
                Attachment attachment1 = optionalAttachment.get();
                Optional<AttachmentContent> optionalAttachmentContent = attachmentContentRepo.findByAttachmentId(attachment.getId());
                if (optionalAttachmentContent.isPresent()) {
                    AttachmentContent attachmentContent = optionalAttachmentContent.get();
                    if (file != null) {
                        String originalFilename1 = file.getOriginalFilename();
                        long size1 = file.getSize();
                        String contentType1 = file.getContentType();
                        byte[] bytes = file.getBytes();


                        attachment1.setOriginalName(originalFilename1);
                        attachment1.setSize(size1);
                        attachment1.setContentType(contentType1);


                        attachmentContent.setBytes(bytes);
                        attachmentContent.setAttachment(attachment1);


                        actor1.setFullName(actor.getFullName());
                        actor1.setPhoto(attachmentRepo.save(attachment1));
                        Actor save = actorRepo.save(actor1);

                        return new ApiResponse("success", true, save);
                    }


                }


            }



        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse deleteActor(Integer id){
        Optional<Actor> optionalActor = actorRepo.findById(id);
        if (optionalActor.isPresent()) {
            Actor actor = optionalActor.get();
            attachmentRepo.delete(actor.getPhoto());
//            attachmentContentRepo.delete(actor.getPhoto());
            actorRepo.delete(actor);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Not found", false);
    }
}
