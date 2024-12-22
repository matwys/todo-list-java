package com.javatodo.todo.repository;

import com.javatodo.todo.entity.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaskDataAccessObject {
    public static final String HASH_KEY = "Task";
    @Autowired
    private RedisTemplate template;


    public Task save(Task task){
        template.opsForHash().put(HASH_KEY,task.getId(),task);
        return task;
    }

//    public Task save(Task task){
//        template.opsForHash().put(HASH_KEY,task.getId(),task);
//        return task;
//    }

    public List<Task> findAll(){
        return template.opsForHash().values(HASH_KEY);
    }

    public Task findTaskById(int id){
        return (Task) template.opsForHash().get(HASH_KEY,id);
    }

    public Boolean checkIdExist(int id){
        return template.opsForHash().hasKey(HASH_KEY,id);
    }

    public String deleteTask(int id){
        template.opsForHash().delete(HASH_KEY,id);
        return "task removed !!";
    }
}
