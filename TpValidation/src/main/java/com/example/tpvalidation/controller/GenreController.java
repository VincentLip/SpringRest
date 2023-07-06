package com.example.tpvalidation.controller;

import com.example.tpvalidation.entity.Author;
import com.example.tpvalidation.entity.Book;
import com.example.tpvalidation.entity.Genre;
import com.example.tpvalidation.service.BookService;
import com.example.tpvalidation.service.GenreService;
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
@RequestMapping("/api/v1/genre")
public class GenreController {

    @Autowired
    GenreService genreService;

    @PostMapping("/save")
    ResponseEntity<String> createPerson(@Valid @RequestBody Genre genre){

        genreService.save(genre);

        return ResponseEntity.ok("Genre created");
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllGenre() {
        List<Genre> genres = genreService.getAllGenre();
        String message = "La liste est vide";

        if (genres.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
        } else {
            return ResponseEntity.ok(genres);
        }

    }

    @PutMapping("put/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Integer id, @RequestBody Genre genre){

        if(!genreService.getGenreById(id).isPresent()){
            String message = "Genre n'existe pas";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        if(genre == null || genre.getName() == null || genre.getDescription() == null){
            String message = "Requete invalide : le nom et la description sont requis";
            return ResponseEntity.badRequest().body(message);

        }
        genreService.updateGenre(id, genre);
        String message = "Le genre avec l'id : " + id + "est mis à jour";
        return ResponseEntity.ok(message);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id){

        if(!genreService.getGenreById(id).isPresent()){
            String message = "Utilisateur n'existe pas";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
        }

        genreService.deleteGenre(id);

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
