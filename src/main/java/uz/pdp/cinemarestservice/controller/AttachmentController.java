package uz.pdp.cinemarestservice.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import uz.pdp.cinemarestservice.model.Attachment;
import uz.pdp.cinemarestservice.repository.AttachmentContentRepo;
import uz.pdp.cinemarestservice.repository.AttachmentRepo;
import uz.pdp.cinemarestservice.service.AttachmentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController{

    @Autowired
    AttachmentRepo attachmentRepo;
    @Autowired
    AttachmentContentRepo attachmentContentRepo;
    @Autowired
    AttachmentService attachmentService;

    @GetMapping("/{id}")
    public void getFile(@PathVariable Integer id, HttpServletResponse response) throws IOException {
        attachmentService.getFile(id, response);
    }

    @PostMapping("/upload")
    public Attachment uploadFile(MultipartFile request) throws IOException {
        return attachmentService.upload(request);
    }

}
