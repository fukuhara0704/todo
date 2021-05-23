package com.example.todo.mapper;

import java.util.List;

import com.example.todo.model.TaskModel;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    
    
    @Select("Select task_id, task_name, task_memo, task_end_datetime, task_today_flag, to_char(task_created_time, 'yyyy/mm/dd hh24:mm') task_created_time from public.t_task where user_id = #{user_id} and task_id = #{taskId}")
    public List<TaskModel> findByTaskId(@Param("user_id") String userId, @Param("taskId") Integer taskId);

    @Update("update public.t_task set  task_name= #{taskName} where user_id = #{userId} and task_id = #{taskId}" )
    public boolean updatebyTaskId(@Param("userId") String userId,@Param("taskId") Integer taskId,@Param("taskName") String taskName);

    @Update("update public.t_task set  task_memo= #{taskMemo} where user_id = #{userId} and task_id = #{taskId}" )
    public boolean updateMemo(@Param("userId") String userId,@Param("taskId") Integer taskId,@Param("taskMemo") String taskMemo);

    @Delete("delete from public.t_task where user_id = #{userId} and task_id = #{taskId}")
    public boolean deleteTask(@Param("userId") String userId,@Param("taskId") Integer taskId);

    @Insert("Insert INTO public.t_task (user_id, task_name) VALUES (#{userId}, #{taskName});")
    public boolean createTask(@Param("userId") String userId,@Param("taskName") String taskName);

    @Select("Select * from public.t_task where user_id = #{userId} ORDER BY id DESC")
    public List<TaskModel> findbyAllTask(@Param("userId") String userId);
}
