package njarro.villagaray.movies.controller;

import njarro.villagaray.movies.constants.MovieConstants;
import njarro.villagaray.movies.model.Movie;
import njarro.villagaray.movies.service.ServiceMovie;
import njarro.villagaray.movies.util.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/movies")
public class ControllerMovie {
    @Autowired
    private ServiceMovie serviceMovie;

    @GetMapping("/")
    public ResponseEntity<?> getAllMovies(){
        try {
            Collection<Movie> itemsMovies =  serviceMovie.findAll();
            return new ResponseEntity<>(itemsMovies, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createMovie (@RequestBody(required = true) Movie movie){
        try{
            return serviceMovie.saveMovie(movie);
        }catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable Long movieId){
        try{
            return serviceMovie.findById(movieId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @PutMapping("/update/{movieId}")
    public ResponseEntity<?> updateMovie(@PathVariable Long movieId, @RequestBody Movie movieUpdate){
        try{
            return serviceMovie.updateMovie(movieId, movieUpdate);
        }catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/delete/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId){
        try{
            return serviceMovie.deleteMovie(movieId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
