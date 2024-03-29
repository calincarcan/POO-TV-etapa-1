package visitor;

import data.CurrentPage;
import data.Database;
import data.ErrorMessage;
import data.User;
import factory.ErrorFactory;
import factory.UserFactory;
import filters.CountryFilter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;

import java.util.ArrayList;

public final class VisitorHomeNAUTH implements Visitor {
    /**
     * Method checks if there is a user present in the database
     * with the same credentials as the ones given by the login action
     * @param action
     * @param db
     * @return
     */
    private User checkLogin(final Action action, final Database db) {
        String loginName = action.getCredentials().getName();
        String loginPassword = action.getCredentials().getPassword();
        for (User user : db.getUsers()) {
            if (user.getCredentials().getName().equals(loginName)
                    && user.getCredentials().getPassword().equals(loginPassword)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Visitor executes the on page and change page commands specific
     * to the not authenticated home page
     * @param currentPage
     * @param action
     * @param db
     * @param output
     */
    public void visit(final CurrentPage currentPage, final Action action,
                      final Database db, final ArrayNode output) {
        String actionType = action.getType();
        switch (actionType) {
            case "change page" -> {
                if (!action.getPage().equals("login")
                        && !action.getPage().equals("register")) {
                    ErrorMessage err = ErrorFactory.standardErr();
                    output.addPOJO(err);
                    currentPage.resetHomeNAUTH();
                    break;
                }
                currentPage.setPageName(action.getPage());
            }
            case "on page" -> {
                if (action.getFeature().equals("login")) {
                    User foundUser = checkLogin(action, db);
                    if (!action.getFeature().equals("login") || foundUser == null) {
                        ErrorMessage err = ErrorFactory.standardErr();
                        output.addPOJO(err);
                        currentPage.resetHomeNAUTH();
                        break;
                    }
                    currentPage.resetHomeAUTH();
                    db.setCurrUser(foundUser);

                    User errUser = UserFactory.createUser(foundUser);
                    db.setCurrMovies(CountryFilter
                            .moviePerms(foundUser.getCredentials().getCountry(), db));
                    ErrorMessage err = ErrorFactory.createErr(null, new ArrayList<>(), errUser);
                    output.addPOJO(err);
                    break;
                }
                if (action.getFeature().equals("register")) {
                    if (!action.getFeature().equals("register")) {
                        ErrorMessage err = ErrorFactory.standardErr();
                        output.addPOJO(err);
                        currentPage.resetHomeNAUTH();
                        break;
                    }
                    User newUser = UserFactory.createUser(action.getCredentials());
                    db.getUsers().add(newUser);

                    db.setCurrMovies(CountryFilter
                            .moviePerms(newUser.getCredentials().getCountry(), db));
                    currentPage.resetHomeAUTH();
                    db.setCurrUser(newUser);

                    User errUser = UserFactory.createUser(newUser);
                    ErrorMessage err = ErrorFactory.createErr(null, new ArrayList<>(), errUser);
                    output.addPOJO(err);
                }
            }
            default -> {

            }
        }
    }
}
