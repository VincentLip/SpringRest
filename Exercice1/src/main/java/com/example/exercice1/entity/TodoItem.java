package com.example.exercice1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "todoitem")
public class TodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "todo_item_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "date", nullable = false, length = 50)
    private String date;

    @OneToMany(mappedBy = "todoItem")
    private Set<ListTodoItem> listTodoItems = new LinkedHashSet<>();

}