package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.*;
import by.epamlab.projecttracking.web.AttributeConstants;
import by.epamlab.projecttracking.web.Constants;
import by.epamlab.projecttracking.web.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@Controller
@Secured({UserRoleConstants.TEAM_LEAD, UserRoleConstants.PR_MANAGER})
public class ProjectLeaderController {

    @Autowired
    AssignmentService assignmentService;

    @Autowired
    AttachmentService attachmentService;

    @Autowired
    MemberService memberService;

    @Autowired
    ProjectService projectService;

    @Autowired
    TaskService taskService;

    @RequestMapping(value = {"/" + PageConstants.CREATE_ISSUE}, method = RequestMethod.GET)
    public String showCreateIssueForm(Model model) {
        model.addAttribute(new Task());
        model.addAttribute(AttributeConstants.PROJECTS, projectService.getAllProjects());
        return PageConstants.CREATE_ISSUE;
    }

    @RequestMapping(value = {"/" + PageConstants.CREATE_ISSUE}, method = RequestMethod.POST)
    public String createIssue(@Valid Task task, BindingResult bindingResult,
                              HttpServletRequest request, Model model) {
        Project project = projectService.getProjectById(task.getProject().getId());
        task.setProject(project);

        if (bindingResult.hasErrors()) {
            return PageConstants.CREATE_ISSUE;
        }

        long startDate = task.getPsd().getTime();
        long endDate = task.getPdd().getTime();
        if (startDate > endDate) {
            model.addAttribute(AttributeConstants.INPUT_DATE_ERROR, "End date must be larger than start date.");
            return PageConstants.CREATE_ISSUE;
        }

        taskService.insertTask(task);
        return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
    }

    @RequestMapping(value = {"/" + PageConstants.ASSIGN}, method = RequestMethod.GET)
    @ExceptionHandler({NumberFormatException.class})
    public String createAssignment(@RequestParam(value = "id", required = false) int taskId, Model model) {
        Task task = taskService.getTaskById(taskId);
        model.addAttribute(AttributeConstants.TASK, task);
        return PageConstants.ASSIGN;
    }

    @RequestMapping(value = {"/" + PageConstants.ASSIGN}, method = RequestMethod.POST)
    public String showAssignForm(@Valid Assignment assignment, HttpServletRequest request) {
        Member member = memberService.getMemberById(assignment.getMember().getId());
        Task task = taskService.getTaskById(assignment.getTask().getId());

        assignment.setTask(task);
        assignment.setMember(member);
        assignmentService.updateAssignment(assignment);

        return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = "/upload", method = RequestMethod.GET)
    public String showUploadForm(@RequestParam(value = "taskId") int taskId,
                                 @RequestParam(value = "projectId") int projectId, Model model) {
        model.addAttribute(AttributeConstants.TASKS_ID, taskId);
        model.addAttribute(AttributeConstants.PROJECT_ID, projectId);
        return "upload";
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String fileUpload(@Valid Attachment attachment, HttpServletRequest request,
                             Model model, @RequestParam("file") MultipartFile file) {


        if (file.isEmpty()) {
            model.addAttribute(AttributeConstants.UPLOAD_FILE_ERROR, "The upload file is empty.");
            return "upload";
        }

        Task task = taskService.getTaskById(attachment.getTask().getId());
        Project project = projectService.getProjectById(attachment.getProject().getId());

        attachment.setTask(task);
        attachment.setProject(project);
        attachment.setSize(file.getSize());
        attachment.setName(file.getOriginalFilename());
        attachment.setServerName(file.getOriginalFilename());

        System.out.println(attachment);
        try (BufferedOutputStream stream = new BufferedOutputStream(
                new FileOutputStream(new File(Constants.ROOT_DIR + attachment.getServerName())))) {
            byte[] bytes = file.getBytes();
            stream.write(bytes);
            attachmentService.addAttachment(attachment);
        } catch (Exception e) {
            model.addAttribute(AttributeConstants.UPLOAD_FILE_ERROR, "Error loading file.");
            return "upload";
        }

        return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
    }

}
