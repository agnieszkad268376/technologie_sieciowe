package org.example.libraryDataBase.Repositories;

import org.example.libraryDataBase.Review;
import org.springframework.data.repository.CrudRepository;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
}
