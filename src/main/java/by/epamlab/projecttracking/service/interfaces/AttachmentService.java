package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Attachment;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface AttachmentService {

    Attachment getAttachmentById(int id);

    void addAttachment(Attachment attachment);

    void uploadFileToServer(String serverName, MultipartFile file) throws Exception;

    void downloadFileFromServer(Attachment attachment, HttpServletResponse response) throws Exception;

}
