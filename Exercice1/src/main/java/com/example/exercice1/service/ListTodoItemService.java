package com.example.exercice1.service;

import com.example.exercice1.entity.ListTodoItem;
import com.example.exercice1.repository.ListTodoItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListTodoItemService {

    @Autowired
    private ListTodoItemRepository listTodoItemRepository;


    public Boolean getListTodoItemTitle(String title){
        ListTodoItem listtodoitem = listTodoItemRepository.findListtodoitemByTitle(title);
        return listtodoitem != null ? true: false;
    }

    public ListTodoItem createListTodoItem(ListTodoItem listtodoitem){
        return listTodoItemRepository.save(listtodoitem);
    }

    public List<ListTodoItem> getAllListtodoitem(){
        return (List<ListTodoItem>) listTodoItemRepository.findAll();
    }

    public Optional<ListTodoItem> getListtodoitemById(Integer id){
        return listTodoItemRepository.findById(id);
    }

    public void deleteListodoitem(Integer id){
        listTodoItemRepository.deleteById(id);
    }
}
