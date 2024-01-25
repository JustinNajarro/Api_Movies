package njarro.villagaray.movies.service;

import njarro.villagaray.movies.model.Category;
import njarro.villagaray.movies.model.Movie;
import njarro.villagaray.movies.repository.RepositoryMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServiceMovie {
    @Autowired
    private RepositoryMovie repositoryMovie;

    public List<Movie> getMoviesByCategory(Category category){
        return repositoryMovie.findByCategory(category);
    }

    public Optional<Movie> getMovieById(Long id) {
        return repositoryMovie.findById(id);
    }

}
