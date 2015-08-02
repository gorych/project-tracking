package by.epamlab.projecttracking.domain;

import javax.persistence.*;

@Entity
@Table(name = "assigment")
public class Assigment {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "description")
    private String description;

    @OneToMany
    @JoinColumn(name = "mamber_id")
    private Member member;

    @OneToMany
    @JoinColumn(name = "task_id")
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
}
