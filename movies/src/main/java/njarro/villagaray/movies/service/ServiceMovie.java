package njarro.villagaray.movies.service;

import njarro.villagaray.movies.constants.MovieConstants;
import njarro.villagaray.movies.model.Movie;
import njarro.villagaray.movies.repository.RepositoryMovie;
import njarro.villagaray.movies.util.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

@Service
public class ServiceMovie {
    @Autowired
    RepositoryMovie repositoryMovie;

    private boolean validateCreatedMovie(Movie movie){
        return movie.getTitle() != null && !movie.getTitle().isEmpty() &&
                movie.getDirector() != null && !movie.getDirector().isEmpty() &&
                movie.getCategory() != null;
    }

    public ResponseEntity<String> saveMovie(Movie movie) {
        try {
            if (!validateCreatedMovie(movie)) {
                return MovieUtils.getResponseEntity(MovieConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

            Movie existingMovie = repositoryMovie.findByTitle(movie.getTitle());
            if (existingMovie != null) {
                return MovieUtils.getResponseEntity("Ya existe una pelicula con este nombre", HttpStatus.BAD_REQUEST);
            }

            repositoryMovie.save(movie);
            return MovieUtils.getResponseEntity("La película fue registrada con éxito", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public Collection<Movie> findAll() {
        try {
            return (Collection<Movie>) repositoryMovie.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }


    public ResponseEntity<?> findById(Long movieId){
        try{
            Optional<Movie> movie = repositoryMovie.findById(movieId);
            if(movie.isPresent()){
                return new ResponseEntity<>(movie,HttpStatus.OK);
            }
            else{
                return MovieUtils.getResponseEntity("No existe la pelicula con id: "+movieId , HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);

    }







}
