package njarro.villagaray.movies.service;

import njarro.villagaray.movies.constants.MovieConstants;
import njarro.villagaray.movies.model.Movie;
import njarro.villagaray.movies.repository.RepositoryMovie;
import njarro.villagaray.movies.util.MovieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
public class ServiceMovie {
    @Autowired
    RepositoryMovie repositoryMovie;

    public Collection<Movie> findAll() {
        try {
            return (Collection<Movie>) repositoryMovie.findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public ResponseEntity<String> saveMovie(Movie movie) {
        try {
            if (!validateMovieData(movie)) {
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


    public ResponseEntity<?> updateMovie(@PathVariable Long movieId, @RequestBody Movie movieUpdate){
        try{
            Optional<Movie> movie = repositoryMovie.findById(movieId);
            if(movie.isEmpty()){
                return MovieUtils.getResponseEntity("No existe la pelicula con id: "+movieId , HttpStatus.BAD_REQUEST);
            }

            if(!validateMovieData(movieUpdate)){
                return MovieUtils.getResponseEntity(MovieConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
            }

            Movie existingMovie = repositoryMovie.findByTitleAndIdNot(movieUpdate.getTitle(), movieId);
            if (existingMovie != null) {
                return MovieUtils.getResponseEntity("Ya existe una película con este nombre", HttpStatus.BAD_REQUEST);
            }

            movie.get().setTitle(movieUpdate.getTitle());
            movie.get().setDirector(movieUpdate.getDirector());
            movie.get().setCategory(movieUpdate.getCategory());
            repositoryMovie.save(movie.get());
            return MovieUtils.getResponseEntity("La película fue actualizada con éxito", HttpStatus.OK);

        }catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<?> deleteMovie(@PathVariable Long movieId){
        try{
            Optional<Movie> movie = repositoryMovie.findById(movieId);
            if(movie.isEmpty()){
                return MovieUtils.getResponseEntity("No existe la pelicula con id: "+movieId , HttpStatus.BAD_REQUEST);
            }
            else{
                repositoryMovie.deleteById(movieId);
                return MovieUtils.getResponseEntity("La película fue eliminada con éxito", HttpStatus.OK);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return MovieUtils.getResponseEntity(MovieConstants.AN_ERROR_OCCURRED, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    private boolean validateMovieData(Movie movie){
        return movie.getTitle() != null && !movie.getTitle().isEmpty() &&
                movie.getDirector() != null && !movie.getDirector().isEmpty() &&
                movie.getCategory() != null;
    }
}
