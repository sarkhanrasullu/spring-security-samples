package com.tabrizguliyev.Task.controller;

import com.tabrizguliyev.Task.dto.TaskDto;
import com.tabrizguliyev.Task.dto.UserDto;
import com.tabrizguliyev.Task.entities.Task;
import com.tabrizguliyev.Task.entities.User;
import com.tabrizguliyev.Task.service.TaskServiceInterface;
import com.tabrizguliyev.Task.service.UserServiceInterface;
import com.tabrizguliyev.Task.util.SecurityUtil;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tabrizguliyev
 */
@Controller
public class TaskController {

    @Autowired
    TaskServiceInterface taskService;//business layer, business logic

    @Autowired
    UserServiceInterface userService;

    @ModelAttribute("task")
    TaskDto task() {
        System.out.println("task alma");
        System.out.println("task alma");
        System.out.println("task alma");
        System.out.println("task alma");
        return new TaskDto();
    }

    @RequestMapping("/task")
    public String getAllTask(Model model, Principal principal) {
        List<Task> list = null;

        if (SecurityUtil.hasRole("ADMIN")) {
            list = taskService.getAll();
        } else if (SecurityUtil.hasRole("USER")) {
            list = taskService.findAllByUserId(SecurityUtil.loggedInUser().getUser());
        }
        model.addAttribute("taskList", converToDto(list));

        List<User> users = userService.getAll();
        model.addAttribute("users", users);

        return "task";
    }

    @RequestMapping(value = "/task/add", method = RequestMethod.POST)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String addTask(TaskDto t) {
        Task task = new Task();
        task.setId(t.getId());
        task.setTitle(t.getTitle());
        task.setDescription(t.getDescription());
        task.setDueDate(t.getDueDate());

        User u = new User();
        u.setId(t.getUserId().getId());
        task.setUserId(u);

        taskService.add(task);
        return "redirect:/task";
    }

    @RequestMapping(value = "/task/update", method = RequestMethod.POST)
    public String updateTask(TaskDto t, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Task task = taskService.findById(t.getId());

        task.setTitle(t.getTitle());
        task.setDescription(t.getDescription());
        task.setFinishDate(t.getFinishDate());

        if (SecurityUtil.hasRole("ADMIN")) {
            task.setDueDate(t.getDueDate());
        }

        if (SecurityUtil.hasRole("ADMIN")) {
            User u = new User();
            u.setId(t.getUserId().getId());
            task.setUserId(u);
            taskService.update(task);
        } else if (SecurityUtil.hasRole("USER")) {
            User loggedInUser = SecurityUtil.loggedInUser().getUser();
            if (task.getUserId().getId().equals(loggedInUser.getId())) {
                taskService.update(task);
            } else {
                throw new Exception("Forbidden");
            }

            if (t.getFinishDate() != null && t.getFinishDate().getTime() > task.getDueDate().getTime()) {
                loggedInUser.setBlocked(true);
                userService.update(loggedInUser);

                return SecurityUtil.logout(request, response);
            }
        }

        return "redirect:/task";
    }

    public static List<TaskDto> converToDto(List<Task> l) {
        List<TaskDto> list = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            Task entity = l.get(i);

            UserDto uDto = new UserDto(
                    entity.getUserId().getId(),
                    entity.getUserId().getName(),
                    entity.getUserId().getSurname(),
                    entity.getUserId().getUsername(),
                    entity.getUserId().getPassword(),
                    entity.getUserId().getBlocked());

            TaskDto dto = new TaskDto(entity.getId(),
                    entity.getTitle(),
                    entity.getDescription(),
                    uDto,
                    entity.getDueDate(),
                    entity.getFinishDate());
            list.add(dto);
        }
        System.out.println("dtolist=" + list);
        return list;
    }

    public static Date converToDate(String s) {
        LocalDate date = LocalDate.parse(s);
        Date d = java.sql.Date.valueOf(date);
        return d;
    }

}
