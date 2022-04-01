package uz.pdp.cinemarestservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Attachment;
import uz.pdp.cinemarestservice.model.AttachmentContent;
import uz.pdp.cinemarestservice.model.Director;
import uz.pdp.cinemarestservice.poyload.ApiResponse;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepository;
import uz.pdp.cinemarestservice.repository.AttachmentRepository;
import uz.pdp.cinemarestservice.repository.DirectorRepository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class DirectorService {

    private final AttachmentRepository attachmentRepository;
    private final AttachmentContentRepository contentRepository;
    private final DirectorRepository directorRepository;

    public ApiResponse getAllDirectors(){
        List<Director> directorList = directorRepository.findAll();
        if (directorList.size() == 0){
            return new ApiResponse("List empty!!!", false);
        }
        return new ApiResponse("Success!", true, directorList);
    }

    public ApiResponse getDirectorById(UUID id){
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (!optionalDirector.isPresent()) {
            return new ApiResponse("Director empty!!!", false);
        }
        return new ApiResponse("Success!",true);
    }

    public ApiResponse addDirector(MultipartFile file, Director director){
        try {
            Attachment attachment = attachmentRepository.save(new Attachment(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getSize()
            ));
            AttachmentContent attachmentContent = contentRepository.save(new AttachmentContent(file.getBytes(), attachment));
            Director saveDirector = directorRepository.save(new Director(director.getFullName(), director.getBio(), attachment));
            return new ApiResponse("Success!",true, saveDirector);
        } catch (IOException e) {
            return new ApiResponse("Error!!!", false);
        }
    }

    public ApiResponse editDirector(UUID id, MultipartFile file, Director director){
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (!optionalDirector.isPresent()){
            return new ApiResponse("Director not found!!!", false);
        }
        try {
            Director editDirector = optionalDirector.get();
            editDirector.setFullName(director.getFullName());
            editDirector.setBio(director.getBio());
            if (file.isEmpty()) {
                Director newDirector = directorRepository.save(editDirector);
                return new ApiResponse("Success!",true, newDirector);
            }
            Attachment editAttachment = editDirector.getDirectorPhoto();
            editAttachment.setContentType(file.getContentType());
            editAttachment.setSize(file.getSize());
            editAttachment.setOriginalName(file.getOriginalFilename());
            Attachment newAttachment = attachmentRepository.save(editAttachment);

            AttachmentContent editAttachmentContent = contentRepository.findByAttachmentId(newAttachment.getId());
            editAttachmentContent.setBytes(file.getBytes());
            editAttachmentContent.setAttachment(newAttachment);
            contentRepository.save(editAttachmentContent);
            editDirector.setDirectorPhoto(newAttachment);
            Director newDirector = directorRepository.save(editDirector);
            return new ApiResponse("Success",true, newDirector);
        } catch (IOException e) {
            return new ApiResponse("Error!!!",false);
        }
    }

    public ApiResponse deleteDirector(UUID id){
        Optional<Director> optionalDirector = directorRepository.findById(id);
        if (!optionalDirector.isPresent()) {
            return new ApiResponse("Director not found!!!", false);
        }
        try {
            Director director = optionalDirector.get();
            Attachment attachment = director.getDirectorPhoto();
            AttachmentContent attachmentContent = contentRepository.findByAttachmentId(attachment.getId());
            contentRepository.deleteById(attachmentContent.getId());
            attachmentRepository.deleteById(attachment.getId());
            directorRepository.deleteById(director.getId());
            return new ApiResponse("Success!",true);
        } catch (Exception e){
            return new ApiResponse("Error",false);
        }
    }
}
