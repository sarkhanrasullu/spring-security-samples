package com.tabrizguliyev.Task.controller;

import com.tabrizguliyev.Task.dto.UserDto;
import com.tabrizguliyev.Task.entities.User;
import com.tabrizguliyev.Task.service.UserServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserController {

    @Autowired
    UserServiceInterface userService;

    @Autowired
    PasswordEncoder encoder;

    @ModelAttribute("user")
    User user() {
        return new User();
    }

    @RequestMapping("/user")
    public String getAllUsers(Model model) {
        List<User> list = userService.getAll();
        System.out.println("getAll=" + list);
        model.addAttribute("userList", convertoDto(list));
        return "user";
    }

    @RequestMapping(value = "/user/update", method = RequestMethod.POST)
    public String updateUser(UserDto u) {
        User temp = userService.findUserById(u.getId());
        temp.setName(u.getName());
        temp.setSurname(u.getSurname());
        temp.setBlocked(u.isBlocked());
        temp.setUsername(u.getUsername());
        
        if(u.getPassword()!=null && !u.getPassword().trim().isEmpty()){
            temp.setPassword(encoder.encode(u.getPassword()));
        }
        
        userService.update(temp);

        return "redirect:/user";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(User u) {
        u.setPassword(encoder.encode(u.getPassword()));
        userService.add(u);

        return "redirect:/user";
    }

    @RequestMapping(value = "/user/delete", method = RequestMethod.POST)
    public String User(int id) {
        userService.delete(id);
        return "redirect:/user";
    }

    public static List<UserDto> convertoDto(List<User> l) {
        List<UserDto> list = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            User uu = l.get(i);
            list.add(new UserDto(uu.getId(), uu.getName(), uu.getSurname(), uu.getUsername(), uu.getPassword(), uu.getBlocked()));
        }
        System.out.println("dtoList" + list);
        return list;

    }
}
