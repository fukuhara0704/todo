package com.example.todo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.todo.mapper.WeatherMapper;
import com.example.todo.model.Weather;
// import com.example.todo.repository.WeatherRepository;
@Service
@Transactional
public class WeatherService{
  
  @Autowired
  WeatherMapper weatherMapper;
  
    /**
     * レコードを全件取得する。
     * @return
     */
    public Weather findById(Integer id){
    
        Weather weather = weatherMapper.findById(id);
        return weather;
    }
    /**
     * レコードを全件取得する。
     * @return
     */
    public List<Weather> findAll(){
        List<Weather> weatherList = weatherMapper.findAll();
        return weatherList;
    }
}