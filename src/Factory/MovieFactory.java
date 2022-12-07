package Factory;

import Data.Movie;
import iofiles.Movieio;

public class MovieFactory {
    public static Movie createMovie(Movieio movie) {
        Movie newMovie = new Movie();
        newMovie.setName(movie.getName());
        newMovie.setYear(movie.getYear());
        newMovie.setDuration(movie.getDuration());
        newMovie.setGenres(movie.getGenres());
        newMovie.setActors(movie.getActors());
        newMovie.setCountriesBanned(movie.getCountriesBanned());
        newMovie.setGenres(movie.getGenres());
        newMovie.setNumLikes(0);
        newMovie.setRatings(0);
        newMovie.setNumRatings(0);
        return newMovie;
    }
}
