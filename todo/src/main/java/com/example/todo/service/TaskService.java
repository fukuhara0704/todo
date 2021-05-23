package com.example.todo.service;


import java.util.List;

import com.example.todo.mapper.TaskMapper;
import com.example.todo.model.TaskModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TaskService {
    
    @Autowired
    TaskMapper taskMapper;

    /**
     * 対象のユーザーの全タスクの情報を取得する。
     * @return
     */
    public List<TaskModel> findbyIdAllTask(String userId, String startToday, String endToday){

        List<TaskModel> taskModel = taskMapper.findByIdAllTask(userId, startToday, endToday);
        return taskModel;
    }

    /**
     * 対象のユーザーの未実施の全タスクの情報を取得する。
     * @return
     */
    public List<TaskModel> findbyIdAllUnfinishTask(String userId, Integer taskStatus){

        List<TaskModel> taskModel = taskMapper.findByIdAllUnfinishTask(userId, taskStatus);
        return taskModel;
    }

    /**
     * 対象のユーザーの実施済みの全タスクの情報を取得する。
     * @return
     */
    public List<TaskModel> findbyIdAllDoneTask(String userId, Integer taskStatus){

        List<TaskModel> taskModel = taskMapper.findbyIdAllDoneTask(userId, taskStatus);
        return taskModel;
    }

    public boolean deleteTask(String userId, Integer taskId) {
        if (!taskMapper.deleteTask(userId, taskId)) {
            return false;
        }
        return true;
    }

    public boolean createTask(String userId, String taskName) {
        if (!taskMapper.createTask(userId, taskName)) {
            return false;
        }
        return true;
    }

    public List<TaskModel> findAllTask(String userId) {
        List<TaskModel> allTaskList = taskMapper.findbyAllTask(userId);
        return allTaskList;
    }
}
