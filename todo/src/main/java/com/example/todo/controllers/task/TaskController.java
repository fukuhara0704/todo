package com.example.todo.controllers.task;

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
 * タスクを表示するクラス
 */
@Controller
public class TaskController {
    @Autowired
    TaskService taskService;

    @Autowired
    protected UserSession userSession;

    @RequestMapping({ "/", "/index" })
    public String index() {
        return "redirect:/task";
    }
    /**
     * タスクを表示
     * 
     * @param model
     * @return
     */
    @RequestMapping(value = "/task", method = RequestMethod.GET)
    public String getTask(Model model, @RequestParam(name = "type" ,required = false) String type) {

        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();
        // SESSIONからUserNameを取得
        String userName = userSession.getUserName();
        // 表示する画面名
        String targetHtml = "";
        // 画面が参照するデータ
        List<TaskModel> taskDataList;

        // パラメータの存在確認
        if (type != "") {
            // 今日の日付を取得
            List<String> result = getTodayDate();
            String startToday = result.get(0);
            String endToday = result.get(1);
            // 本日のタスク情報を取得する。
            taskDataList = taskService.findbyIdAllTask(userId, startToday, endToday);
            targetHtml = "today";
        } else {
            // すべてのタスク情報を取得する。
            taskDataList = taskService.findAllTask(userId);
            targetHtml = "index";
        }

        // 表示したいデータを引数に格納してView側で表示
        model.addAttribute("taskDataList", taskDataList);

        return targetHtml;
    }

    /**
     * 
     * @param param
     * @return
     */
    @RequestMapping(value = "/task/delete", method = RequestMethod.POST)
    public String deleteTask(@RequestParam("hd-del-task-id") String param, @RequestParam("hd-del-type") String type) {
        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();

        Integer taskId = Integer.parseInt(param);

        if (taskService.deleteTask(userId, taskId)) {
            return "redirect:/task?type=" + type;

        } else {
            // todayにリダイレクト
            return "redirect:/error";
        }
    }

    /**
     * タスク作成
     * 
     * @param param
     * @return
     */
    @RequestMapping(value = "/task/create", method = RequestMethod.POST)
    public String createTask(@RequestParam("add-task-data") String param, @RequestParam("hd-create-type") String type) {
        // SESSIONからUserIDを取得
        String userId = userSession.getUserId();

        String taskName = param;
        System.out.println(taskName);

        if (taskService.createTask(userId, taskName)) {
            return "redirect:/task?type=" + type;
        } else {
            // todayにリダイレクト
            return "redirect:/error";
        }
    }

    /**
     * 今日の日付を取得
     * 
     * @return
     */
    private List<String> getTodayDate() {
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
