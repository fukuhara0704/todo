package com.example.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.todo.model.Weather;
@Repository
public interface WeatherRepository extends JpaRepository<Weather, Integer> {}