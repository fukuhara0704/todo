package com.example.todo.controllers.task.unfinish;

import java.util.List;

import com.example.todo.model.TaskModel;
import com.example.todo.service.TaskService;
import com.example.todo.session.UserSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * 本日タスクを表示するクラス
 */
 @Controller
public class UnfinishController {

    @Autowired
    TaskService taskService;
    
    @Autowired
    protected UserSession userSession;

    
    @RequestMapping("/unfinish")
    public String today(Model model) {
        
        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();
        
        // 今日の日付を取得
        Integer taskStatus = 0;

        // 未実施のタスク情報を取得する。
        List<TaskModel> unfinishTaskDataList = taskService.findbyIdAllUnfinishTask(userId, taskStatus);
        
        // 表示したいデータを引数に格納してView側で表示
        model.addAttribute("unfinishTaskDataList", unfinishTaskDataList);
        
        // today.htmlに返す
        return "unfinish";
    }
    
}
