package com.company.dto;

//import lombok.Data;

/**
 *
 * @author tabrizguliyev
 */
//@Data
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    
}
