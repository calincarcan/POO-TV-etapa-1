package filters;

import data.Database;
import data.Movie;

import java.util.ArrayList;

public final class CountryFilter implements Filter {
    public static int debugVar = 0;

    /**
     * Method parses the whole movie database and returns a list containing all the movies that
     * can be viewed by a user from a given country
     * @param country
     * @param db
     * @return allowedMovieList
     */
    public static ArrayList<Movie> moviePerms(final String country, final Database db) {
        ArrayList<Movie> allMovies = db.getMovies();
        ArrayList<Movie> allowedMovieList = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (!movie.getCountriesBanned().contains(country)) {
                allowedMovieList.add(movie);
            }
        }
        return allowedMovieList;
    }

    private CountryFilter() {

    }
}
