package com.example.todo.mapper;

import com.example.todo.model.User;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("SELECT id, name, password, enabled FROM public.t_user where name = #{name}")
    public User selectByUser(@Param("name") String name);
}
