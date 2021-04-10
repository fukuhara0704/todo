package com.example.todo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SuccessController {
  
  @RequestMapping("/success")
  public String hello(Model model) {
    
    model.addAttribute("success", "Success Hello World");
    
    return "success";
  }
}
