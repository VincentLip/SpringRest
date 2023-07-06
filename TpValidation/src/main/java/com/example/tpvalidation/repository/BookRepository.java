package com.example.tpvalidation.repository;

import com.example.tpvalidation.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository extends CrudRepository<Book,Integer> {
}
