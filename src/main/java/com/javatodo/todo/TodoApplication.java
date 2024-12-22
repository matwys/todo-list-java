package com.javatodo.todo;

import com.javatodo.todo.entity.Task;
import com.javatodo.todo.repository.TaskDataAccessObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/task")
public class TodoApplication {
    @Autowired
    private TaskDataAccessObject dao;

    @PostMapping("/action")
    public String save(@RequestBody Task task) {
        if (dao.checkIdExist(task.getId())){
            return "Task with the same id exists";
        }
        dao.save(task);
        return "Task inserted successfully";
    }

//    @PostMapping
//    public Task save(@RequestBody Task task) {
//        return dao.save(task);
//    }

    @GetMapping("/action")
    public List<Task> getAllTasks() {
        return dao.findAll();
    }

    @GetMapping("/action/{id}")
    public Task findTask(@PathVariable int id) {
        return dao.findTaskById(id);
    }

    @PutMapping("/action/{id}")
    public String updateTask(@PathVariable int id, @RequestBody Task task) {
        task.setId(id);
        if (dao.checkIdExist(task.getId())){
            dao.save(task);
            return "Task updated successfully";
        }
        dao.save(task);
        return "Task inserted successfully";
    }

    @DeleteMapping("/action/{id}")
    public String remove(@PathVariable int id)   {
        return dao.deleteTask(id);
    }


    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

}




//package com.javatodo.todo;
//
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//@SpringBootApplication
//public class TodoApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(TodoApplication.class, args);
//    }
//
//}
