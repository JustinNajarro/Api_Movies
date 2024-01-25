package njarro.villagaray.movies.controller;

import njarro.villagaray.movies.model.Category;
import njarro.villagaray.movies.model.Movie;
import njarro.villagaray.movies.service.ServiceMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/movies")
public class ControllerMovie {
    @Autowired
    private ServiceMovie serviceMovie;

    @GetMapping("/byCategory")
    public ResponseEntity<List<Movie>> getMoviesByCategory(@RequestParam("categoryId") Long categoryId) {
        Category category = new Category();
        category.setId(categoryId);

        List<Movie> movies = serviceMovie.getMoviesByCategory(category);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovieById(@PathVariable("movieId") Long movieId) {
        Optional<Movie> movieOptional = serviceMovie.getMovieById(movieId);

        return movieOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
