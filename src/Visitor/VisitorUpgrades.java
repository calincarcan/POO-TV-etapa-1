package Visitor;

import Data.*;
import Factory.ErrorFactory;
import Factory.MovieFactory;
import Factory.UserFactory;
import Filters.CountryFilter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;

import java.util.ArrayList;

public class VisitorUpgrades implements Visitor {
    public void visit(CurrentPage currentPage, Action action, Database db, ArrayNode output) {
        String actionType = action.getType();
        switch (actionType) {
            case "change page" -> {
                if (!action.getPage().equals("movies")
                        && !action.getPage().equals("home")
                        && !action.getPage().equals("logout")) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    break;
                }
                if (action.getPage().equals("home")) {
                    db.setCurrMovies(CountryFilter
                            .moviePerms(db.getCurrUser().getCredentials().getCountry(), db));
                    currentPage.resetHomeAUTH();
                    break;
                }
                if (action.getPage().equals("movies")) {
                    db.setCurrMovies(CountryFilter
                            .moviePerms(db.getCurrUser().getCredentials().getCountry(), db));
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
                currentPage.resetHomeNAUTH();
                db.setCurrUser(null);
                db.setCurrMovies(new ArrayList<>());
            }
            case "on page" -> {
                final int PREMIUM_COST = 10;
                if (!action.getFeature().equals("buy tokens")
                        && !action.getFeature().equals("buy premium account")) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    break;
                }
                if (action.getFeature().equals("buy tokens")) {
                    User user = db.getCurrUser();
                    int balance = Integer.parseInt(user.getCredentials().getBalance());
                    int count = Integer.parseInt(action.getCount());
                    if (balance < count) {
                        ErrorMessage err = ErrorFactory.standardErr();
                        output.addPOJO(err);
                        break;
                    }
                    balance -= count;
                    user.getCredentials().setBalance(Integer.toString(balance));
                    user.setTokensCount(user.getTokensCount() + count);
                    break;
                }
                User user = db.getCurrUser();
                int tokens = user.getTokensCount();
                if (tokens < PREMIUM_COST) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    break;
                }
                user.setTokensCount(user.getTokensCount() - PREMIUM_COST);
                user.getCredentials().setAccountType("premium");
            }
            default -> {
                System.out.println("ERROR IN VisitorUpgrades!!!!!");
            }
        }
    }
}
