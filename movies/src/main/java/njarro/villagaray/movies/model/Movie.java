package njarro.villagaray.movies.model;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
@Data
@Entity
public class Movie implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private String director;

    @ManyToOne
    private Category category;
}
