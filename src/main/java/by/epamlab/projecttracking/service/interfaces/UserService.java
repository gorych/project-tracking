package by.epamlab.projecttracking.service.interfaces;

import by.epamlab.projecttracking.domain.Member;
import by.epamlab.projecttracking.domain.Project;
import by.epamlab.projecttracking.domain.Task;

import java.util.List;

public interface UserService {

    List<Task> getUserTasks();

    List<Member> getMembers();

}
