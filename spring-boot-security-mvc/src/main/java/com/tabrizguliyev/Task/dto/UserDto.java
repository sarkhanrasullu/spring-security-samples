package com.tabrizguliyev.Task.dto;

import lombok.Data;

/**
 *
 * @author tabrizguliyev
 */
@Data
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private boolean blocked;

    public UserDto() {
    }

    public UserDto(int id) {
        this.id = id;
    }

    public UserDto(int id, String name, String surname, String username, String password, boolean blocked) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.blocked = blocked;
    }

}
