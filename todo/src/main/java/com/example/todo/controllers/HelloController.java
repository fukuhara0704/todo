package com.example.todo.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.todo.model.Weather;
import com.example.todo.service.WeatherService;

@Controller
public class HelloController {
  @Autowired
  WeatherService weatherService;
  
  @RequestMapping("/hello")
  public String hello(Model model) {
    
    model.addAttribute("hello", "Change Hello World"); // Hello World!の表示
    
    // 気象データの取得
    List<Weather> weatherDataList = weatherService.findAll();
    // "wearherDataList"をkeyにしてhello.htmlでマッピング？ハンドリング？してる。
    model.addAttribute("weatherDataList", weatherDataList);
    
    return "hello";
  }
}