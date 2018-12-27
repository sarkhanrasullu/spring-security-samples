package com.tabrizguliyev.Task.dto;

import java.util.Date;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author tabrizguliyev
 */
@Data
public class TaskDto {

    private int id;
    private String title;
    private String description;
    private UserDto userId;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date dueDate;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date finishDate;

    public TaskDto() {
    }

    public TaskDto(int id, String title, String description, UserDto userId, Date dueDate, Date finishDate) {
        this.id = id;
        System.out.println("nnn");
        this.title = title;
        this.description = description;
        this.userId = userId;
        this.dueDate = dueDate;
        this.finishDate = finishDate;
    }

}
