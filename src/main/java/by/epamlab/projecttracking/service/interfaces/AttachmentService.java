package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Attachment;

public interface AttachmentService {

    Attachment getAttachmentById(int id);

    void addAttachment(Attachment attachment);

}
