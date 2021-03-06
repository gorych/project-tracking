package by.epamlab.projecttracking.web.controllers;

import by.epamlab.projecttracking.domain.*;
import by.epamlab.projecttracking.security.UserRoleConstants;
import by.epamlab.projecttracking.service.interfaces.*;
import by.epamlab.projecttracking.web.AttributeConstants;
import by.epamlab.projecttracking.web.PageConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.spel.ast.Assign;
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
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


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
        model.addAttribute(AttributeConstants.ASSIGNMENT, new Assignment());
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
    @RequestMapping(value = "/" + PageConstants.UPLOAD, method = RequestMethod.GET)
    public String showUploadForm(@RequestParam(value = "taskId") int taskId,
                                 @RequestParam(value = "projectId") int projectId, Model model) {
        model.addAttribute(AttributeConstants.ATTACHMENT, new Attachment());
        model.addAttribute(AttributeConstants.TASK_ID, taskId);
        model.addAttribute(AttributeConstants.PROJECT_ID, projectId);
        return PageConstants.UPLOAD;
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = "/" + PageConstants.UPLOAD, method = RequestMethod.POST)
    public String fileUpload(@Valid Attachment attachment, HttpServletRequest request,
                             Model model, @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            model.addAttribute(AttributeConstants.UPLOAD_FILE_ERROR, "The upload file is empty.");
            return PageConstants.UPLOAD;
        }

        Task task = taskService.getTaskById(attachment.getTask().getId());
        Project project = projectService.getProjectById(attachment.getProject().getId());
        long sizeKb = file.getSize() % 1024;

        attachment.setTask(task);
        attachment.setProject(project);
        attachment.setSize(sizeKb);
        attachment.setName(file.getOriginalFilename());
        attachment.setServerName(file.getOriginalFilename());

        try {
            attachmentService.uploadFileToServer(attachment.getServerName(), file);
            attachmentService.addAttachment(attachment);
        } catch (Exception e) {
            model.addAttribute(AttributeConstants.UPLOAD_FILE_ERROR, "Error loading file.");
            return PageConstants.UPLOAD;
        }

        return "redirect:" + request.getSession().getAttribute(AttributeConstants.PREVIOUS_PAGE);
    }

    @Secured(value = {UserRoleConstants.USER})
    @RequestMapping(value = "/" + PageConstants.DOWNLOAD, method = RequestMethod.GET)
    public void download(@RequestParam(value = "id", required = false) int attachmentId,
                         HttpServletResponse response) {
        try {
            Attachment attachment = attachmentService.getAttachmentById(attachmentId);
            attachmentService.downloadFileFromServer(attachment, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
