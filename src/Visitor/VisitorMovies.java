package Visitor;

import Data.CurrentPage;
import Data.Database;
import Data.ErrorMessage;
import Data.Movie;
import Factory.ErrorFactory;
import Factory.MovieFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;

import java.util.ArrayList;

public class VisitorMovies implements Visitor{
    private Movie seeDetails(Action action, Database db) {
        for (Movie movie : db.getCurrMovies()) {
            if (movie.getName().equals(action.getMovie())) {
                return movie;
            }
        }
        return null;
    }
    private ArrayList<Movie> searchMovie(String search, Database db) {
        ArrayList<Movie> list = new ArrayList<>();
        for (Movie movie : db.getMovies()) {
            if (!movie.getCountriesBanned()
                    .contains(db.getCurrUser().getCredentials().getCountry())) {
                int index = movie.getName().indexOf(search);
                if (index == 0)
                    list.add(MovieFactory.createMovie(movie));
            }
        }
        return list;
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
                    break;
                }
                if (pageName.equals("logout")) {
                    currentPage.resetHomeNAUTH();
                    db.setCurrUser(null);
                    break;
                }
                if (pageName.equals("see details")) {
                    if (seeDetails(action, db) == null) {
                        ErrorMessage err = ErrorFactory.standardErr();
                        output.addPOJO(err);
                        currentPage.resetHomeAUTH();
                        break;
                    }
                    break;
                }
                //TODO see details implementation
            }
            case "on page" -> {
                if (!pageName.equals(currentPage.getPageName())) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    currentPage.resetHomeNAUTH();
                    break;
                }
                if (action.getFeature().equals("search")) {
                    ArrayList<Movie> list = searchMovie(action.getStartsWith(), db);
                    ErrorMessage err = ErrorFactory.createErr(null, list, db.getCurrUser());
                    output.addPOJO(err);
                    break;
                }
                if (action.getFeature().equals("filter")) {
                    ArrayList<Movie> list = searchMovie(action.getStartsWith(), db);
                    ErrorMessage err = ErrorFactory.createErr(null, list, db.getCurrUser());
                    output.addPOJO(err);
                    break;
                }
                if (action.getFeature().equals("sort")) {

                    break;
                }
            }
            default -> {
                System.out.println("EROARE MASIVA IN VisitorMovies!!!!!");
            }
        }
    }
}
