package Visitor;

import Data.CurrentPage;
import Data.Database;
import Data.ErrorMessage;
import Data.User;
import Factory.UserFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;
import java.util.ArrayList;

public class VisitorHomeNAUTH implements Visitor{
    private User checkLogin(Action action, Database db) {
        String loginName = action.getCredentials().getName();
        String loginPassword = action.getCredentials().getPassword();
//        db.getActions().stream().filter()
        for (User user : db.getUsers()) {
            if (user.getCredentials().getName().equals(loginName) &&
                user.getCredentials().getPassword().equals(loginPassword)) {
                return user;
            }
        }
        return null;
    }
    @Override
    public void visit(CurrentPage currentPage, Action action, Database db, ArrayNode output) {
        String actionType = action.getType();
        switch (actionType) {
            case "change page" -> {
                if (!action.getPage().equals("login") &&
                        !action.getPage().equals("register")) {
                    ErrorMessage err = new ErrorMessage();
                    err.setError("Error");
                    err.setCurrentMoviesList(new ArrayList<>());
                    err.setCurrentUser(null);
                    output.addPOJO(err);
                    currentPage.resetHomeNAUTH();
                    break;
                }
                currentPage.setPageName(action.getPage());
            }
            case "on page" -> {
                if (!action.getPage().equals(currentPage.getPageName())) {
                    ErrorMessage err = new ErrorMessage();
                    err.setError("Error");
                    err.setCurrentMoviesList(new ArrayList<>());
                    err.setCurrentUser(null);
                    output.addPOJO(err);
                    currentPage.resetHomeNAUTH();
                    break;
                }
                if (action.getPage().equals("login")) {
                    User foundUser = checkLogin(action, db);
                    if (!action.getFeature().equals("login") || foundUser == null) {
                        ErrorMessage err = new ErrorMessage();
                        err.setError("Error");
                        err.setCurrentMoviesList(new ArrayList<>());
                        err.setCurrentUser(null);
                        output.addPOJO(err);
                        currentPage.resetHomeNAUTH();
                        break;
                    }
                    currentPage.resetHomeAUTH();
                    db.setCurrUser(foundUser);

                    ErrorMessage err = new ErrorMessage();
                    User errUser = UserFactory.createUser(foundUser);
                    err.setError(null);
                    err.setCurrentMoviesList(new ArrayList<>());
                    err.setCurrentUser(errUser);
                    output.addPOJO(err);
                    break;
                }
                if (action.getPage().equals("register")) {
                    if (!action.getFeature().equals("register")) {
                        ErrorMessage err = new ErrorMessage();
                        err.setError("Error");
                        err.setCurrentMoviesList(new ArrayList<>());
                        err.setCurrentUser(null);
                        output.addPOJO(err);
                        currentPage.resetHomeNAUTH();
                        break;
                    }
                    //TODO DE VERIFICAT CA USER UL NU EXISTA DEJA
                    User newUser = UserFactory.createUser(action.getCredentials());
                    db.getUsers().add(newUser);

                    currentPage.resetHomeAUTH();
                    db.setCurrUser(newUser);

                    ErrorMessage err = new ErrorMessage();
                    User errUser = UserFactory.createUser(newUser);
                    err.setError(null);
                    err.setCurrentMoviesList(new ArrayList<>());
                    err.setCurrentUser(errUser);
                    output.addPOJO(err);
                }
            }
            default -> {
                System.out.println("EROARE MASIVA IN VisitorHomeNAUTH!!!!!");
            }
        }
    }
}
