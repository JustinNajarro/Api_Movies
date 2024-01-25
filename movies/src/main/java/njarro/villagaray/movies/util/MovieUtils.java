package njarro.villagaray.movies.util;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MovieUtils {

    private MovieUtils(){

    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){
        return new ResponseEntity<String>("Mensaje : " + message, httpStatus);
    }
}
