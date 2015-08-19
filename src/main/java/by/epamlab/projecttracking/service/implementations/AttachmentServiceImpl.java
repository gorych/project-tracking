package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.AttachmentDAO;
import by.epamlab.projecttracking.domain.Attachment;
import by.epamlab.projecttracking.service.interfaces.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
