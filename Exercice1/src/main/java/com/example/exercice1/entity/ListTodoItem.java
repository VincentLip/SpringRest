package com.example.exercice1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "listtodoitem")
public class ListTodoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_todo_item_id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 50)
    private String title;

    @Column(name = "date", nullable = false, length = 50)
    private String date;

    @Column(name = "complete")
    private Boolean complete;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "todo_item_id", nullable = false)
    private TodoItem todoItem;

}