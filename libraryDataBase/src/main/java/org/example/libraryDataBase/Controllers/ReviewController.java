package org.example.libraryDataBase.Controllers;

import org.example.libraryDataBase.Repositories.ReviewRepository;
import org.example.libraryDataBase.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/review")
public class ReviewController {

        private final ReviewRepository reviewRepository;

        @Autowired
        public ReviewController(ReviewRepository reviewRepository) {
            this.reviewRepository = reviewRepository;
        }

        @PostMapping("/add")
        @ResponseStatus(code = HttpStatus.CREATED)
        public @ResponseBody Review addReview(@RequestBody Review review){
            return reviewRepository.save(review);
        }

        @GetMapping("/getAll")
        @ResponseStatus(code = HttpStatus.CREATED)
        public @ResponseBody Iterable<Review> getAllReview(){
            return reviewRepository.findAll();
        }

    }
