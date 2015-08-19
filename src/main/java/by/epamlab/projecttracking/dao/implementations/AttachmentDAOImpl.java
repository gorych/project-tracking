package by.epamlab.projecttracking.dao.implementations;

import by.epamlab.projecttracking.dao.interfaces.AttachmentDAO;
import by.epamlab.projecttracking.domain.Attachment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AttachmentDAOImpl implements AttachmentDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public Attachment getAttachmentById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return (Attachment) session.get(Attachment.class, id);
    }

    @SuppressWarnings("unchecked")
    public List<Attachment> getAllAttachments() {
        return sessionFactory.getCurrentSession().createQuery("from Attachment")
                .list();
    }

    public void insertAttachment(Attachment attachment) {
        sessionFactory.getCurrentSession().save(attachment);
    }

}
