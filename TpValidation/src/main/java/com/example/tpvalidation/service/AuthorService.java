package com.example.tpvalidation.service;

import com.example.tpvalidation.entity.Author;
import com.example.tpvalidation.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void save(Author author){
        authorRepository.save(author);
    }

    public List<Author> getAllAuthor(){
        return (List<Author>) authorRepository.findAll();

    }

    public Author updateAuthor(Integer id, Author author){

        Optional<Author> author1 = authorRepository.findById(id);

        if(author1.isPresent()){
            Author author2 = author1.get();
            author2.setEmail(author.getEmail());
            author2.setName(author.getName());
            return authorRepository.save(author2);
        }
        return null;
    }

    public Optional<Author> getAuthorById(Integer id){
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Integer id){
        authorRepository.deleteById(id);
    }


}
