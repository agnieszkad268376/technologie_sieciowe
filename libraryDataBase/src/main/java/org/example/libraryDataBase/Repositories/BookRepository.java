package org.example.libraryDataBase.Repositories;

import org.example.libraryDataBase.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends CrudRepository <Book, Integer> {

}



