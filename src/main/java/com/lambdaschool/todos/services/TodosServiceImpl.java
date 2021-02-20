package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;

@Service(value = "todosService")
public class TodosServiceImpl implements TodosService{

  @Autowired
  TodosRepository todosRepository;

  @Autowired
  UserService userService;

  @Override
  public Todos markComplete(long todoid) {
    Todos todo = todosRepository.findById(todoid)
        .orElseThrow(() -> new EntityNotFoundException("Todo item " + todoid + " not found."));
    todo.setCompleted(true);
    return todosRepository.save(todo);
  }

  @Override
  public ArrayList<Todos> findAllTodos() {
    return (ArrayList<Todos>) todosRepository.findAll();
  }

  @Transactional
  @Override
  public Todos save(long userid, Todos newTodos) {
    User newUser = userService.findUserById(userid);
    Todos saveTodo = new Todos(newUser, newTodos.getDescription());
    return todosRepository.save(saveTodo);
  }

  @Transactional
  @Override
  public Todos update(long todoid, Todos receivedTodo) {
    Todos newTodo = todosRepository.findById(todoid)
        .orElseThrow(() -> new EntityNotFoundException("Todo item " + todoid + " not found!"));

    if (receivedTodo.getDescription() != null) { newTodo.setDescription(receivedTodo.getDescription()); }
    if (receivedTodo.getUser() != null) { newTodo.setUser(receivedTodo.getUser()); }

    return todosRepository.save(newTodo);
  }

  @Override
  public void deleteById(long todoid) {
    if (todosRepository.findById(todoid).isPresent()) {
      todosRepository.deleteById(todoid);
    } else {
      throw new EntityNotFoundException("Todo item " + todoid + " not found!");
    }
  }
}
