package com.lambdaschool.todos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "todos")
public class Todos extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long todoid;

  private boolean completed = false;

  @ManyToOne
  @JoinColumn(name = "userid")
  @JsonIgnoreProperties(value = "todos", allowSetters = true)
  private User user;

//  @Column(nullable = false)
  private String description;

  //todoid primary key, not null long
  //description string, not null
  //completed boolean. Note that for all new todos, default completed to false
  //userid foreign key (one user to many todos) not null

  public Todos() {
  }

  public Todos(User user, String description) {
    this.description = description;
    this.user = user;
  }

//  public Todos(String description) {
//    this.description = description;
//  }

  public long getTodoid() {
    return todoid;
  }

  public void setTodoid(long todoid) {
    this.todoid = todoid;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
