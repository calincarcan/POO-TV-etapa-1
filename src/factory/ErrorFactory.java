package factory;

import data.ErrorMessage;
import data.Movie;
import data.User;
import filters.CountryFilter;

import java.util.ArrayList;
public final class ErrorFactory {
    /**
     * Method creates and returns a standard error message.
     * Also it increases the debugVar that has been used for debug.
     * @return returns a standard error message
     */
    public static ErrorMessage standardErr() {
        ErrorMessage stdErr = new ErrorMessage();
        stdErr.setNrError(CountryFilter.debugVar++);
        stdErr.setError("Error");
        stdErr.setCurrentMoviesList(new ArrayList<>());
        stdErr.setCurrentUser(null);
        return stdErr;
    }
    /**
     * Method creates and returns a specific error message.
     * Also it increases the debugVar that has been used for debug.
     * @return returns a specific error message
     */
    public static ErrorMessage createErr(final String error,
                                         final ArrayList<Movie> list, final User currentUser) {
        ErrorMessage err = new ErrorMessage();
        err.setNrError(CountryFilter.debugVar++);
        err.setError(error);
        ArrayList<Movie> newList = new ArrayList<>();
        newList.addAll(list);
        err.setCurrentMoviesList(newList);
        err.setCurrentUser(currentUser);
        return err;
    }
    private ErrorFactory() {

    }
}
