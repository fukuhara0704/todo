package com.example.todo.controllers.task.today;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.todo.model.TaskModel;
import com.example.todo.service.TaskService;
import com.example.todo.session.UserSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * 本日タスクを表示するクラス
 */
 @Controller
public class TodayController {

    @Autowired
    TaskService taskService;
    
    @Autowired
    protected UserSession userSession;

    
    @RequestMapping(value = "/today", method = RequestMethod.POST)
    public String today(Model model) {
        
        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();
        
        // 今日の日付を取得
        List<String> result = getTodayDate();

        String startToday = result.get(0);
        String endToday = result.get(1);

        // 本日のタスク情報を取得する。
        List<TaskModel> todayTaskDataList = taskService.findbyIdAllTask(userId, startToday, endToday);
        
        // 表示したいデータを引数に格納してView側で表示
        model.addAttribute("todayTaskDataList", todayTaskDataList);
        
        // today.htmlに返す
        return "today";
    }
    
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteTask(@RequestParam("hd-del-task-id") String param) {
        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();
        
        Integer taskId = Integer.parseInt(param);
        
       if(taskService.deleteTask(userId,taskId)){
            return "redirect:/today";

       }else{
            // todayにリダイレクト
            return "redirect:/error";
       }
    }

        
        
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createTask(@RequestParam("add-task-data") String param) {
        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();
        
        String taskName = param;
        System.out.println(taskName);
        
       if(taskService.createTask(userId,taskName)){
            return "redirect:/today";

       }else{
            // todayにリダイレクト
            return "redirect:/error";

       }

    }
    
    private List<String> getTodayDate(){

        // リストの初期化
        List<String> list = new ArrayList<String>();

        // 今日の日付を取得
        Date now = new Date();
        System.out.println(now); 

        SimpleDateFormat day = new SimpleDateFormat("yyyy-MM-dd");
        String today = day.format(now); 
        System.out.println(today); 
        // yyyy-MM-dd 00:00:00を取得
        String startToday = today + " 00:00:00";
        System.out.println(startToday);
        list.add(startToday);

        // yyyy-MM-dd 23:59:59を取得
        String endToday = today + " 23:59:59";
        System.out.println(endToday);
        list.add(endToday);

        return list;
    }
}
