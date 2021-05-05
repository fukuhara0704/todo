package com.example.todo.mapper;

import java.util.List;

import com.example.todo.model.TaskModel;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * t_taskのSQLを書くクラス
 */
@Mapper
public interface TaskMapper {

    // 対象のユーザーの全タスクの情報を取得する。
    @Select("SELECT * FROM public.t_task where user_id = #{user_id} and task_start_datetime < TO_TIMESTAMP(#{start_today}, 'YYYY-MM-DD HH24:MI:SS') and task_end_datetime > TO_TIMESTAMP(#{end_today}, 'YYYY-MM-DD HH24:MI:SS') ORDER BY id")
    public List<TaskModel> findByIdAllTask(@Param("user_id") String userId, @Param("start_today") String startToday, @Param("end_today") String endToday);

    // 対象のユーザーの全未実施タスクの情報を取得する。
    @Select("SELECT * FROM public.t_task where user_id = #{user_id} and task_status = #{taskStatus} ORDER BY id")
    public List<TaskModel> findByIdAllUnfinishTask(@Param("user_id") String userId, @Param("taskStatus") Integer taskStatus);

    // 対象のユーザーの全未実施タスクの情報を取得する。
    @Select("SELECT * FROM public.t_task where user_id = #{user_id} and task_status = #{taskStatus} ORDER BY id")
    public List<TaskModel> findbyIdAllDoneTask(@Param("user_id") String userId, @Param("taskStatus") Integer taskStatus);
}
