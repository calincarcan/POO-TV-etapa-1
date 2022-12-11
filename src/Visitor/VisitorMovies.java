package Visitor;

import Data.*;
import Factory.ErrorFactory;
import Factory.MovieFactory;
import Factory.UserFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;
import iofiles.Filters;
import Filters.CountryFilter;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class VisitorMovies implements Visitor {
    private ArrayList<Movie> filter(Filters filters, ArrayList<Movie> movies) {
        return new ArrayList<>(movies.stream()
                .sorted((o1, o2) -> {
                    if (filters.getSort() != null) {
                        if (filters.getSort().getRating().equals("decreasing")) {
                            return filterFunc(filters, o1, o2, o2.getRating(), o1.getRating());
                        } else {
                            return filterFunc(filters, o1, o2, o1.getRating(), o2.getRating());
                        }
                    }
                    return 0;
                }).toList());
    }

    private int filterFunc(Filters filters, Movie o1, Movie o2, double rating, double rating2) {
        if (filters.getSort().getDuration().equals("decreasing")) {
            if (o1.getRating() - o2.getRating() == 0)
                return o2.getDuration() - o1.getDuration();
            else {
                return (int) (rating - rating2);
            }
        } else {
            if (o1.getRating() - o2.getRating() == 0)
                return o1.getDuration() - o2.getDuration();
            else {
                return (int) (rating - rating2);
            }
        }
    }
    @Override
    public void visit(CurrentPage currentPage, Action action, Database db, ArrayNode output) {
        String actionType = action.getType();
        String pageName = action.getPage();
        switch (actionType) {
            case "change page" -> {
                if (!pageName.equals("home")
                        && !pageName.equals("see details")
                        && !pageName.equals("logout")) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    break;
                }
                if (pageName.equals("home")) {
                    currentPage.resetHomeAUTH();
                    String country = db.getCurrUser().getCredentials().getCountry();
                    db.setCurrMovies(CountryFilter.moviePerms(country, db));
                    break;
                }
                if (pageName.equals("logout")) {
                    currentPage.resetHomeNAUTH();
                    db.setCurrUser(null);
                    db.setCurrMovies(new ArrayList<>());
                    break;
                }
                Movie details = null;
                for (Movie movie : db.getCurrMovies()) {
                    if (movie.getName().equals(action.getMovie())) {
                        details = movie;
                        break;
                    }
                }
                if (details == null) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    currentPage.resetMovies();
                }
                else {
                    currentPage.resetSeeDetails();
                    ArrayList<Movie> errMovie = new ArrayList<>();
                    errMovie.add(MovieFactory.createMovie(details));
                    User user = UserFactory.createUser(db.getCurrUser());
                    db.setCurrMovies(new ArrayList<>());
                    db.getCurrMovies().add(details);
                    ErrorMessage err = ErrorFactory.createErr(null, errMovie, user);
                    output.addPOJO(err);
                }
            }
            case "on page" -> {
                if (!action.getFeature().equals("search")
                        && !action.getFeature().equals("filter")) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    break;
                }
                if (action.getFeature().equals("search")) {
                    ArrayList<Movie> list = new ArrayList<>();
                    for (Movie movie : db.getCurrMovies()) {
                        if (movie.getName().indexOf(action.getStartsWith()) == 0)
                            list.add(MovieFactory.createMovie(movie));
                    }
                    User errUser = UserFactory.createUser(db.getCurrUser());
                    ErrorMessage err = ErrorFactory.createErr(null, list, errUser);
                    output.addPOJO(err);
                    break;
                }
                if (action.getFeature().equals("filter")) {
                    ArrayList<Movie> list = new ArrayList<>();
                    for (Movie movie : db.getCurrMovies()) {
                        list.add(MovieFactory.createMovie(movie));
                    }
                    if (action.getFilters().getContains() != null) {
                        // Filter by genre
                        if (action.getFilters().getContains().getGenre() != null) {
                            for (String genre : action.getFilters().getContains().getGenre()) {
                                for (Movie movie : list) {
                                    if (!movie.getGenres().contains(genre)) {
                                        list.remove(movie);
                                    }
                                }
                            }
                        }
                        // Filter by actor
                        if (action.getFilters().getContains().getActors() != null) {
                            for (String actor : action.getFilters().getContains().getActors()) {
                                for (Movie movie : list) {
                                    if (!movie.getActors().contains(actor)) {
                                        list.remove(movie);
                                    }
                                }
                            }
                        }
                    }
                    list = filter(action.getFilters(), list);
                    User user = UserFactory.createUser(db.getCurrUser());
                    ErrorMessage err = ErrorFactory.createErr(null, list, user);
                    output.addPOJO(err);
                }
            }
            default -> {
                System.out.println("EROARE MASIVA IN VisitorMovies!!!!!");
            }
        }
    }
}
