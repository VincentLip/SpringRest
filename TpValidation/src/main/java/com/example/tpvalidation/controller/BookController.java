package com.example.tpvalidation.controller;

import com.example.tpvalidation.entity.Author;
import com.example.tpvalidation.entity.Book;
import com.example.tpvalidation.service.AuthorService;
import com.example.tpvalidation.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/save")
    ResponseEntity<String> createPerson(@Valid @RequestBody Book book){

        bookService.save(book);

        return ResponseEntity.ok("Book created");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllBook() {
        List<Book> books = bookService.getAllBook();
        String message = "La liste est vide";

        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(books);
        }

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
