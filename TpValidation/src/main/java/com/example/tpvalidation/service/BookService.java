package com.example.tpvalidation.service;

import com.example.tpvalidation.entity.Book;
import com.example.tpvalidation.entity.Genre;
import com.example.tpvalidation.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public void save(Book book){
        bookRepository.save(book);
    }


    public List<Book> getAllBook(){
        return (List<Book>) bookRepository.findAll();

    }

    public Book updateBook(Integer id, Book book){

        Optional<Book> book1 = bookRepository.findById(id);

        if(book1.isPresent()){
            Book book2 = book1.get();
            book2.setYear(book.getYear());
            book2.setTitle(book.getTitle());
            book2.setAuthor(book.getAuthor());
            book2.setGenre(book.getGenre());
            return bookRepository.save(book2);
        }
        return null;
    }

    public Optional<Book> getBookById(Integer id){
        return bookRepository.findById(id);
    }

    public void deleteBook(Integer id){
        bookRepository.deleteById(id);
    }
}
