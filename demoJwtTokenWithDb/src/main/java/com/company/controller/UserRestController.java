/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.UserDto;
import com.company.entity.User;
import com.company.service.UserServiceInterface;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
/**
 *
 * @author tabrizguliyev
 */
@RestController
public class UserRestController {//single responsibilty

    @Autowired
    UserServiceInterface userService;

    @RequestMapping("/users")
    public ResponseEntity page(@RequestParam(name = "name", required = false) String name, @RequestParam(name = "surname", required = false) String surname) {

        List<UserDto> result = new ArrayList<UserDto>();

        List<User> users = null;
        if (name != null && surname != null) {
            users = userService.findUserByNameAndSurname(name, surname);
        } else {
            users = userService.getAll();
        }
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            result.add(new UserDto(u.getId(), u.getName(), u.getSurname(), u.getUsername(), null, u.getBlocked()));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(result));
    }

    @RequestMapping("/users/{id}")
    public ResponseEntity page(@PathVariable("id") int id) {
        User u = userService.findUserById(id);

        UserDto result = new UserDto(u.getId(), u.getName(), u.getSurname(), u.getUsername(), null, u.getBlocked());
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(result));
    }

    @PostMapping("/users")
    public ResponseEntity addUser(@RequestBody UserDto userDto) {

        int id = userService.add(new User(null, userDto.getName(), userDto.getSurname(), null, null, false));
        userDto.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDTO(userDto));
    }
}
