package com.example.todo.controllers;

import com.example.todo.session.UserSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    
    @Autowired UserSession userSession;

    @RequestMapping({ "/", "/index" })
    public String index(Model model) {
        // SESSIONからUserIDを取得
        String userName = userSession.getUserName();

        model.addAttribute("userName", userName);
        return "index";
    }
}