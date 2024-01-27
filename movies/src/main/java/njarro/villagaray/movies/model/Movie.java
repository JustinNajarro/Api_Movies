package njarro.villagaray.movies.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String director;

    @ManyToOne
    private Category category;
}
