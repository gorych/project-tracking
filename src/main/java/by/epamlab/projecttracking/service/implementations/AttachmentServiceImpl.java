package by.epamlab.projecttracking.service.implementations;

import by.epamlab.projecttracking.dao.interfaces.AttachmentDAO;
import by.epamlab.projecttracking.domain.Attachment;
import by.epamlab.projecttracking.service.interfaces.AttachmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AttachmentServiceImpl implements AttachmentService{

    @Autowired
    private AttachmentDAO attachmentDAO;

    @Transactional
    public Attachment get(int id) {
        return attachmentDAO.get(id);
    }

    @Transactional
    public List<Attachment> getAll() {
        return attachmentDAO.getAll();
    }

}
