package by.epamlab.projecttracking.service;

import by.epamlab.projecttracking.domain.Role;
import by.epamlab.projecttracking.service.interfaces.RoleService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ServiceImplTest {

    private static ApplicationContext appCont;
    private static RoleService roleService;

    @BeforeClass
    public static void setupTest() {
        appCont = new ClassPathXmlApplicationContext("WEB-INF/spring/applicationContext.xml");
        roleService = (RoleService) appCont.getBean("roleServiceImpl");
    }

    @Test
    public void runTest() {
        List<Role> role = roleService.getAll();
        System.out.println(role);
    }

}
