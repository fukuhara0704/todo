package com.example.todo.service;

import java.util.List;

import com.example.todo.mapper.TaskMapper;
import com.example.todo.model.TaskModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskAjaxService {
    
    @Autowired
    TaskMapper taskMapper;
    
    /**
     * 対象のタスクの情報を取得する。
     * @return
     */
    public List<TaskModel> findbyTaskId(String userId, Integer taskId){

        List<TaskModel> taskModel = taskMapper.findByTaskId(userId, taskId);
        return taskModel;
    }
}
