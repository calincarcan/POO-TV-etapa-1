package Factory;

import Data.ErrorMessage;
import Data.Movie;
import Data.User;
import Filters.CountryFilter;

import java.util.ArrayList;

public class ErrorFactory {
    public static ErrorMessage standardErr() {
        ErrorMessage stdErr = new ErrorMessage();
        stdErr.setNrError(CountryFilter.aolo++);
        stdErr.setError("Error");
        stdErr.setCurrentMoviesList(new ArrayList<>());
        stdErr.setCurrentUser(null);
        return stdErr;
    }

    public static ErrorMessage createErr(String error, ArrayList<Movie> list, User currentUser) {
        ErrorMessage err = new ErrorMessage();
        err.setNrError(CountryFilter.aolo++);
        err.setError(error);
        ArrayList<Movie> newList = new ArrayList<>();
        newList.addAll(list);
        err.setCurrentMoviesList(newList);
        err.setCurrentUser(currentUser);
        return err;
    }
}
