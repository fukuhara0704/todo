package com.example.todo.controllers.task.today;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class TodayController {

    @Autowired
    TaskService taskService;
    
    @Autowired
    protected UserSession userSession;

    
    @RequestMapping("/today")
    public String today(Model model) {
        
        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();
        
        // 今日の日付を取得
        Date now = new Date();
        System.out.println(now); 

        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        String today = day.format(now); 
        System.out.println(today); 

        // yyyy-MM-dd 00:00:00を取得
        String startToday = today + " 00:00:00";
        System.out.println(startToday);

        // yyyy-MM-dd 23:59:59を取得
        String endToday = today + " 23:59:59";
        System.out.println(endToday);

        // 本日のタスク情報を取得する。
        List<TaskModel> todayTaskDataList = taskService.findbyIdAllTask(userId, startToday, endToday);
        
        // 表示したいデータを引数に格納してView側で表示
        model.addAttribute("todayTaskDataList", todayTaskDataList);
        
        // today.htmlに返す
        return "today";
    }
    
}
