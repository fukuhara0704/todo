package com.example.todo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.todo.model.Weather;
import com.example.todo.repository.WeatherRepository;
@Service
@Transactional
public class WeatherService{
  
  @Autowired
  WeatherRepository weatherRepository;
  
  /**
   * レコードを全件取得する。
   * @return
   */
  public List<Weather> findAllWeatherData(){
    
    return weatherRepository.findAll();
  }
}