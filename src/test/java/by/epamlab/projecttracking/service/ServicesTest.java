package by.epamlab.projecttracking.service;

import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.service.interfaces.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ServicesTest {

    private static ApplicationContext appCont;

    private static RoleService roleService;
    private static MemberService memberService;
    private static TaskService taskService;
    private static AttachmentService attachmentService;
    private static AssignmentService assignmentService;
    private static ActivityService activityService;
    private static PositionService positionService;
    private static StatusService statusService;
    private static EmployeeService employeeService;
    private static ProjectService projectService;

    @BeforeClass
    public static void setupTest() {
        appCont = new ClassPathXmlApplicationContext("WEB-INF/spring/applicationContext.xml");
        roleService = (RoleService) appCont.getBean("roleServiceImpl");
        memberService = (MemberService) appCont.getBean("memberServiceImpl");
        taskService = (TaskService) appCont.getBean("taskServiceImpl");
        attachmentService = (AttachmentService) appCont.getBean("attachmentServiceImpl");
        assignmentService = (AssignmentService) appCont.getBean("assignmentServiceImpl");
        activityService = (ActivityService) appCont.getBean("activityServiceImpl");
        positionService = (PositionService) appCont.getBean("positionServiceImpl");
        statusService = (StatusService) appCont.getBean("statusServiceImpl");
        employeeService = (EmployeeService) appCont.getBean("employeeServiceImpl");
        projectService = (ProjectService) appCont.getBean("projectServiceImpl");
    }

    @Test
    public void runTest() {
        List<Role> roles = roleService.getAll();
        List<Member> members = memberService.getAll();
        List<Task> tasks = taskService.getAll();
        List<Attachment> attachments = attachmentService.getAll();
        List<Assignment> assignments = assignmentService.getAll();
        List<Activity> activities = activityService.getAll();
        List<Position> positions = positionService.getAll();
        List<Status> statuses = statusService.getAll();
        List<Employee> employees = employeeService.getAll();
        List<Project> projects = projectService.getAll();

        System.out.println("ROLE-SERVICE\n" + roles);
        System.out.println("MEMBER-SERVICE\n" + members);
        System.out.println("TASK-SERVICE\n" + tasks);
        System.out.println("ATTACHMENT-SERVICE\n" + attachments);
        System.out.println("ASSIGNMENT-SERVICE\n" + assignments);
        System.out.println("ACTIVITY-SERVICE\n" + activities);
        System.out.println("POSITION-SERVICE\n" + positions);
        System.out.println("STATUS-SERVICE\n" + statuses);
        System.out.println("EMPLOYEE-SERVICE\n" + employees);
        System.out.println("PROJECT-SERVICE\n" + projects);
    }

}
