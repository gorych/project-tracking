package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.AttachmentDAO;
import by.epamlab.projecttracking.domain.Attachment;
import by.epamlab.projecttracking.service.interfaces.AttachmentService;
import by.epamlab.projecttracking.web.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    AttachmentDAO attachmentDAO;

    @Transactional
    public Attachment getAttachmentById(int id) {
        return attachmentDAO.getAttachmentById(id);
    }

    @Transactional
    public void addAttachment(Attachment attachment) {
        attachmentDAO.insertAttachment(attachment);
    }

    public void uploadFileToServer(String serverName, MultipartFile file) throws Exception {
        try (BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(new File(Constants.ROOT_DIR + serverName)))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);

        }
    }

    public void downloadFileFromServer(Attachment attachment, HttpServletResponse response) throws Exception {
        final int BUFFER_SIZE = 1024;
        final int OFFSET = 0;
        File file = new File(Constants.ROOT_DIR + attachment.getServerName());

        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()) {

            response.setContentType(Constants.RESPONSE_CONTENT_TYPE);
            response.setHeader(Constants.RESPONSE_HEADER_NAME, Constants.RESPONSE_HEADER_SUFFIX +
                    "\"" + attachment.getName() + "\"");
            byte[] buffer = new byte[BUFFER_SIZE];
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, OFFSET, length);
            }
        }
    }
}
