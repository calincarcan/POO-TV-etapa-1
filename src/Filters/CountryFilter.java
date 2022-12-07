package Filters;

import Data.Database;
import Data.Movie;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CountryFilter implements Filter{
    public static ArrayList<Movie> moviePerms(String country, Database db) {
        ArrayList<Movie> allMovies = db.getMovies();
        return (ArrayList<Movie>) allMovies
                .stream()
                .filter(movie -> !movie.getCountriesBanned().contains(country))
                .collect(Collectors.toList());
    }
}
