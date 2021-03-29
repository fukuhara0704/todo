package com.example.todo.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="weather")
public class Weather {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private Integer location_id;
    
    private String name;
    
    private Integer temperature;
    
    private Integer humidity;
    
    private Timestamp date_time;
    
  public Integer getId() {
    return id;
  }
  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getLocation_id() {
    return location_id;
  }
  public void setLocation_id(Integer location_id) {
    this.location_id = location_id;
  }
    
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public Integer getTemperature() {
    return temperature;
  }
  public void setTemperature(Integer temperature) {
    this.temperature = temperature;
  }
  public Integer getHumidity() {
    return humidity;
  }
  public void setHumidity(Integer humidity) {
    this.humidity = humidity;
  }
  public Timestamp getDate_time() {
    return date_time;
  }
  public void setDate_time(Timestamp date_time) {
    this.date_time = date_time;
  }   
}