package com.example.tpvalidation.repository;

import com.example.tpvalidation.entity.Genre;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends CrudRepository<Genre,Integer> {
}
