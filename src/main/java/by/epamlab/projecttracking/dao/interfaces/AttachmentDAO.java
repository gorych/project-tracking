package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Attachment;

import java.util.List;

public interface AttachmentDAO {

    Attachment getAttachmentById(int id);

    List<Attachment> getAllAttachments();
}
