package Data;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
@JsonIgnoreProperties({ "nrError" })
public class ErrorMessage {
    int nrError;
    String error;
    ArrayList<Movie> currentMoviesList;
    User currentUser;
}
