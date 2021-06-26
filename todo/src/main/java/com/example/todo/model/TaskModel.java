package com.example.todo.model;

import lombok.Data;

@Data
public class TaskModel {
    
    private Integer id;
    private String user_id;
    private Integer project_id;
    private Integer task_id;
    private String task_name;
    private Integer task_priority;
    private String task_start_datetime;
    private String task_end_datetime;
    private Integer task_routine;
    private Integer task_status;
    private String task_memo;
    private Short task_today_flag;
    private String task_created_time;

    // getter
    public Integer getTask_status() {
        return task_status;
    }
    public Integer getId() {
        return id;
    }
    public String getUser_id() {
        return user_id;
    }
    public Integer getProject_id() {
        return project_id;
    }
    public Integer getTask_id() {
        return task_id;
    }
    public String getTask_name() {
        return task_name;
    }
    public Integer getTask_priority() {
        return task_priority;
    }
    public String getTask_start_datetime() {
        return task_start_datetime;
    }
    public String getTask_end_datetime() {
        return task_end_datetime;
    }
    public Integer getTask_routine() {
        return task_routine;
    }
    public String getTask_memo() {
        return task_memo;
    }
    public Short getTask_today_flag() {
        return task_today_flag;
    }
    public String getTask_created_time() {
        return task_created_time;
    }
}
