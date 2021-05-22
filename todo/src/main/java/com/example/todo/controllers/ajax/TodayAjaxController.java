package com.example.todo.controllers.ajax;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import com.example.todo.model.TaskModel;
import com.example.todo.service.TaskAjaxService;
import com.example.todo.session.UserSession;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TodayAjaxController {

    @Autowired
    TaskAjaxService taskAjaxService;

    @Autowired
    protected UserSession userSession;

    @Autowired
    Gson gson;

    @ResponseBody
    @RequestMapping(value = "/ajax", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String getAjaxTaskData(@RequestBody String json) throws IOException, ServletException {
        System.out.println(json);
        JsonObject jobj = new Gson().fromJson(json, JsonObject.class);

        String result = jobj.get("task_id").getAsString();
        System.out.println(result);
        Integer taskId = Integer.parseInt(result);
        System.out.println(userSession.getUserId());

        List<TaskModel> targetTask = taskAjaxService.findbyTaskId(userSession.getUserId(), taskId);
        System.out.println(gson.toJson(targetTask));
        return gson.toJson(targetTask);
    }

}
