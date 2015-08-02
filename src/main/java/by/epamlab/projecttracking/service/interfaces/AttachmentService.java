package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Attachment;

import java.util.List;

public interface AttachmentService {

    Attachment get(int id);

    List<Attachment> getAll();

}
