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

    private static MemberService memberService;
    private static AssignmentService assignmentService;
    private static ActivityService activityService;
    private static PositionService positionService;
    private static EmployeeService employeeService;
    private static ProjectService projectService;

    @BeforeClass
    public static void setupTest() {
        appCont = new ClassPathXmlApplicationContext("WEB-INF/spring/applicationContext.xml");
        memberService = (MemberService) appCont.getBean("memberServiceImpl");
        assignmentService = (AssignmentService) appCont.getBean("assignmentServiceImpl");
        activityService = (ActivityService) appCont.getBean("activityServiceImpl");
        positionService = (PositionService) appCont.getBean("positionServiceImpl");
        employeeService = (EmployeeService) appCont.getBean("employeeServiceImpl");
        projectService = (ProjectService) appCont.getBean("projectServiceImpl");
    }

    @Test
    public void runTest() {
        List<Member> members = memberService.getAll();
        List<Assignment> assignments = assignmentService.getAll();
        List<Activity> activities = activityService.getAll();
        List<Position> positions = positionService.getAll();
        List<Employee> employees = employeeService.getAll();
        List<Project> projects = projectService.getAll();

        System.out.println("MEMBER-SERVICE\n" + members);
        System.out.println("ASSIGNMENT-SERVICE\n" + assignments);
        System.out.println("ACTIVITY-SERVICE\n" + activities);
        System.out.println("POSITION-SERVICE\n" + positions);
        System.out.println("EMPLOYEE-SERVICE\n" + employees);
        System.out.println("PROJECT-SERVICE\n" + projects);
    }

}
