package by.epamlab.projecttracking.domain;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="task")
public class Task {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;

    @Column(name = "description")
    private String description;

    @Column(name = "psd")
    private Date psd;

    @Column(name = "pdd")
    private Date pdd;

    @Column(name = "asd")
    private Date asd;

    @Column(name = "add")
    private Date add;

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public Date getAdd() {
        return add;
    }

    public void setAdd(Date add) {
        this.add = add;
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
                ", add=" + add +
                '}';
    }
}
