package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;

import java.util.ArrayList;

public interface TodosService
{
    Todos markComplete(long todoid);

    ArrayList<Todos> findAllTodos();

    Todos save(long userid, Todos newTodos);


    Todos update(long todoid, Todos todo);

    void deleteById(long todoid);
}
