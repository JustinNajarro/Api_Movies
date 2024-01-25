package njarro.villagaray.movies.repository;

import njarro.villagaray.movies.model.Category;
import njarro.villagaray.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface RepositoryMovie extends JpaRepository<Movie, Long> {
    List<Movie> findByCategory(Category category);
    Optional<Movie> findById(Integer Long);
}
