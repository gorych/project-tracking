package by.epamlab.projecttracking.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "assignment")
public class Assignment {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "description")
    @NotNull
    private String description;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "mamber_id")
    @NotNull
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    @NotNull
    private Task task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", member=" + member +
                ", task=" + task +
                '}';
    }
}
