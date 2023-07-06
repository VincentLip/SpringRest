package com.example.tpvalidation.controller;

import com.example.tpvalidation.entity.Author;
import com.example.tpvalidation.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {

    @Autowired
    AuthorService authorService;

    @PostMapping("/save")
    ResponseEntity<String> createPerson(@Valid @RequestBody Author author){

        authorService.save(author);

        return ResponseEntity.ok("Author created");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllAuthor() {
        List<Author> authors = authorService.getAllAuthor();
        String message = "La liste est vide";

        if (authors.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(authors);
        }

    }

    @PutMapping("put/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody Author author){

        if(!authorService.getAuthorById(id).isPresent()){
            String message = "Autheur n'existe pas";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        if(author == null || author.getEmail() == null || author.getName() == null){
            String message = "Requete invalide : le nom et l'email sont requis";
            return ResponseEntity.badRequest().body(message);

        }
        authorService.updateAuthor(id, author);
        String message = "L'autheur avec l'id : " + id + "est mis à jour";
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){

        if(!authorService.getAuthorById(id).isPresent()){
            String message = "Utilisateur n'existe pas";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        authorService.deleteAuthor(id);

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("/api/v1/all")).build();

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException exception){

        Map<String, String> errors = new HashMap<>();

        exception.getBindingResult().getAllErrors().forEach((error)->{
                    String fieldName = ((FieldError)error).getField();
                    String message = error.getDefaultMessage();
                    errors.put(fieldName,message);
                }
        );
        return errors;

    }
}
