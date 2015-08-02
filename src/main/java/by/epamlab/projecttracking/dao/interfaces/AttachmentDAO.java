package by.epamlab.projecttracking.dao.interfaces;

import by.epamlab.projecttracking.domain.Attachment;

import java.util.List;

public interface AttachmentDAO {

    public Attachment get(int id);

    public List<Attachment> getAll();
}
