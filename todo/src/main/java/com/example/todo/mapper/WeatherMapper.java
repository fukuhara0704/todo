package com.example.todo.mapper;

import java.util.List;

import com.example.todo.model.Weather;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

    
@Mapper
public interface WeatherMapper {

    @Select("SELECT * FROM public.weather ORDER BY id")
    public List<Weather> findAll();

    @Select("SELECT * FROM public.weather WHERE id = #{id}")
    public Weather findById(@Param("id") Integer id);

}
