package uz.pdp.cinemarestservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Attachment;
import uz.pdp.cinemarestservice.model.AttachmentContent;
import uz.pdp.cinemarestservice.model.Director;
import uz.pdp.cinemarestservice.payLoad.ApiResponse;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepo;
import uz.pdp.cinemarestservice.repository.AttachmentRepo;
import uz.pdp.cinemarestservice.repository.DirectorRepo;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class DirectorService {

    @Autowired
    AttachmentRepo attachmentRepo;

    @Autowired
    AttachmentContentRepo attachmentContentRepo;

    @Autowired
    DirectorRepo directorRepo;

    public ApiResponse getAllDirectors(){
        List<Director> all = directorRepo.findAll();
        if (all != null){
            return new ApiResponse("Success", true, all);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse getDirectorById(Integer id){
        Optional<Director> optionalDirector = directorRepo.findById(id);
        if (optionalDirector.isPresent()) {
            Director director = optionalDirector.get();
            return new ApiResponse("Success", true, director);
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse addDirector(Director director, MultipartFile file) throws IOException {
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


            Director director1 = new Director();
            director1.setFullName(director.getFullName());
            director1.setPhoto(saveAttachmentContent);
            Director save = directorRepo.save(director1);


            return new ApiResponse("Success", true, save);
        }

        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse updateDirector(Integer id, Director director, MultipartFile file) throws IOException {
        Optional<Director> optionalDirector = directorRepo.findById(id);
        if (optionalDirector.isPresent()) {
            Director director1 = optionalDirector.get();

            AttachmentContent attachmentContent1 = director1.getPhoto();
            Attachment attachment1 = attachmentContent1.getAttachment();

            if (file != null) {
                String originalFilename1 = file.getOriginalFilename();
                long size1 = file.getSize();
                String contentType1 = file.getContentType();
                byte[] bytes = file.getBytes();


                attachment1.setOriginalName(originalFilename1);
                attachment1.setSize(size1);
                attachment1.setContentType(contentType1);


                attachmentContent1.setBytes(bytes);
                attachmentContent1.setAttachment(attachment1);

                director1.setFullName(director.getFullName());
                director1.setPhoto(attachmentContentRepo.save(attachmentContent1));
                Director save = directorRepo.save(director1);

                return new ApiResponse("success", true, save);
            }
        }
        return new ApiResponse("Not found", false, null);
    }

    public ApiResponse deleteDirector(Integer id){
        Optional<Director> optionalDirector = directorRepo.findById(id);
        if (optionalDirector.isPresent()) {
            Director director = optionalDirector.get();

            attachmentRepo.delete(director.getPhoto().getAttachment());
            attachmentContentRepo.delete(director.getPhoto());
            directorRepo.delete(director);
            return new ApiResponse("Success", true);
        }
        return new ApiResponse("Not found", false);
    }
}
