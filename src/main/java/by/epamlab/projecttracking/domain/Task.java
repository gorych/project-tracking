package by.epamlab.projecttracking.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "task")
@XmlRootElement(name = "task")
public class Task implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @NotNull
    private Project project;

    @Column(name = "description")
    private String description;

    @Column(name = "psd")
    @NotNull(message = "Correct date format must be YYYY-mm-dd.")
    private Date psd;

    @Column(name = "pdd")
    @NotNull(message = "Correct date format must be YYYY-mm-dd.")
    private Date pdd;

    @Column(name = "asd")
    private Date asd;

    @Column(name = "`add`")
    private Date aed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Activity> activities;

    @OneToMany(mappedBy = "task", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attachment> attachments;

    @XmlTransient
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getPsd() {
        return psd;
    }

    public void setPsd(Date psd) {
        this.psd = psd;
    }

    public Date getPdd() {
        return pdd;
    }

    public void setPdd(Date pdd) {
        this.pdd = pdd;
    }

    public Date getAsd() {
        return asd;
    }

    public void setAsd(Date asd) {
        this.asd = asd;
    }

    public Date getAed() {
        return aed;
    }

    public void setAed(Date add) {
        this.aed = add;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @XmlTransient
    public List<Activity> getActivities() {
        return activities;
    }

    @XmlTransient
    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", project=" + project +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", psd=" + psd +
                ", pdd=" + pdd +
                ", asd=" + asd +
                ", add=" + aed +
                '}';
    }
}
