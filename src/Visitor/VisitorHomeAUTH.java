package Visitor;

import Data.*;
import Factory.ErrorFactory;
import Factory.MovieFactory;
import Factory.UserFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;

import java.util.ArrayList;

public class VisitorHomeAUTH implements Visitor{
    public void visit(CurrentPage currentPage, Action action, Database db, ArrayNode output) {
        String actionType = action.getType();
        switch (actionType) {
            case "change page" -> {
                if (!action.getPage().equals("movies")
                        && !action.getPage().equals("logout")
                        && !action.getPage().equals("upgrades")) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    break;
                }
                if (action.getPage().equals("logout")) {
                    db.setCurrUser(null);
                    db.setCurrMovies(new ArrayList<>());
                    currentPage.resetHomeNAUTH();
                    break;
                }
                if (action.getPage().equals("movies")) {
                    currentPage.resetMovies();
                    User user = UserFactory.createUser(db.getCurrUser());
                    ArrayList<Movie> list = new ArrayList<>();
                    for (Movie movie : db.getCurrMovies()) {
                        list.add(MovieFactory.createMovie(movie));
                    }
                    ErrorMessage err = ErrorFactory
                            .createErr(null, list, user);
                    output.addPOJO(err);
                    break;
                }
                currentPage.resetUpgrades();
            }
            case "on page" -> {
                ErrorMessage err = ErrorFactory.standardErr();
                output.addPOJO(err);
                break;
            }
            default -> {
                System.out.println("EROARE MASIVA IN VisitorHomeAUTH!!!!!");
            }
        }
    }
}
