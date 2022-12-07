package Factory;

import Data.ErrorMessage;
import Data.Movie;
import Data.User;

import java.util.ArrayList;

public class ErrorFactory {
    public static ErrorMessage standardErr() {
        ErrorMessage err = new ErrorMessage();
        err.setError("Error");
        err.setCurrentMoviesList(new ArrayList<>());
        err.setCurrentUser(null);
        return err;
    }
    public static ErrorMessage createErr(String error, ArrayList<Movie> list, User currentUser) {
        ErrorMessage err = new ErrorMessage();
        err.setError(error);
        err.setCurrentMoviesList(list);
        err.setCurrentUser(currentUser);
        return err;
    }
}
