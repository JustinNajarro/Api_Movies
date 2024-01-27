package njarro.villagaray.movies.repository;

import njarro.villagaray.movies.model.Category;
import njarro.villagaray.movies.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryMovie extends JpaRepository<Movie, Long> {
    List<Movie> findByCategory(Category category);
    Movie findByTitle(@Param(("title")) String title);
    Movie findByTitleAndIdNot(String title, Long id);

}
