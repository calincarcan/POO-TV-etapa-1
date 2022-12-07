package Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
public class ErrorMessage {
    String error;
    ArrayList<Movie> currentMoviesList;
    User currentUser;
}
