package com.example.exercice1.controller;

import com.example.exercice1.entity.ListTodoItem;
import com.example.exercice1.service.ListTodoItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("listtodoitem")
public class ListTodoItemController {

    @Autowired
    private ListTodoItemService listTodoItemService;


    @PostMapping("created")
    public ResponseEntity<String> post(@RequestBody ListTodoItem listtodoitem) {

//        if (listTodoItemService.getListTodoItemTitle(listtodoitem.getTitle())) {
//            String errorMessage = "La liste existe déjà";
//            return ResponseEntity.badRequest().body(errorMessage);
//        }

        if (listtodoitem == null || listtodoitem.getTitle() == null || listtodoitem.getDate() == null || listtodoitem.getComplete() == null) {
            String message = "Requête invalide : le titre et la date sont requis";
            return ResponseEntity.badRequest().body(message);
        }

        listTodoItemService.createListTodoItem(listtodoitem);
        return ResponseEntity.status(HttpStatus.CREATED).body("Liste créée avec succès");

    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllListTodoItem() {
        List<ListTodoItem> listTodoItems= listTodoItemService.getAllListtodoitem();
        String message = "La liste est vide";

        if (listTodoItems.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(listTodoItems);
        }

    }
}
