package com.lambdaschool.todos.controllers;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.services.TodosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * The entry point for client to access user, todos combinations
 */
@RestController
@RequestMapping("/todos")
public class TodosController
{
    /**
     * Using the Todos service to process user, todos combinations data
     */
    @Autowired
    TodosService todosService;

    @GetMapping(value = "/todos")
    public ResponseEntity<?> getTodos() {
        ArrayList<Todos> todos =  todosService.findAllTodos();
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }
    /**
     * Given the todo id, mark the task as complete
     * <br>Example: <a href="http://localhost:2019/todos/todo/7">http://localhost:2019/todos/todo/7</a>
     *
     * @param todoid The todo to be marked complete
     * @return Status of OK
     */
    @PatchMapping(value = "/todo/{todoid}")
    public ResponseEntity<?> completeTodo(
        @PathVariable
            long todoid)
    {
        todosService.markComplete(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/todo/{todoid}", produces="application/json", consumes = "application/json")
    public ResponseEntity<?> editTodo(@RequestBody Todos todo, @PathVariable long todoid) {
        Todos newTodo = todosService.update(todoid, todo);
        return new ResponseEntity<>(todo, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/todo/{todoid}")
    public ResponseEntity<?> deleteTodo(@PathVariable long todoid) {
        todosService.deleteById(todoid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
