package com.example.tpvalidation.service;

import com.example.tpvalidation.entity.Genre;
import com.example.tpvalidation.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    private GenreRepository genreRepository;

    public void save(Genre genre){
        genreRepository.save(genre);
    }


    public List<Genre> getAllGenre(){
        return (List<Genre>) genreRepository.findAll();

    }

    public Genre updateGenre(Integer id, Genre genre){

        Optional<Genre> genre1 = genreRepository.findById(id);

        if(genre1.isPresent()){
            Genre genre2 = genre1.get();
            genre2.setName(genre.getName());
            genre2.setDescription(genre.getDescription());
            return genreRepository.save(genre2);
        }
        return null;
    }

    public Optional<Genre> getGenreById(Integer id){
        return genreRepository.findById(id);
    }

    public void deleteGenre(Integer id){
        genreRepository.deleteById(id);
    }
}
