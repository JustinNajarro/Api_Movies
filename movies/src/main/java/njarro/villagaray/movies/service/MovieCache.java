package njarro.villagaray.movies.service;

import njarro.villagaray.movies.model.Movie;
import njarro.villagaray.movies.repository.RepositoryMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/** Lectura cacheada en Redis de películas por id (bean aparte para que el proxy de caché aplique). */
@Component
public class MovieCache {
    @Autowired
    private RepositoryMovie repositoryMovie;

    @Cacheable(value = "movie", key = "#movieId", unless = "#result == null")
    public Movie getById(Long movieId) {
        return repositoryMovie.findById(movieId).orElse(null);
    }
}
