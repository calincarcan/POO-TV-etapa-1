package Visitor;

import Data.CurrentPage;
import Data.Database;
import Data.ErrorMessage;
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
                    ErrorMessage err = new ErrorMessage();
                    err.setError("Error");
                    err.setCurrentMoviesList(new ArrayList<>());
                    err.setCurrentUser(null);
                    output.addPOJO(err);
                    break;
                }
                if (action.getPage().equals("logout")) {
                    db.setCurrUser(null);
                    currentPage.resetHomeNAUTH();
                    break;
                }
            }
            case "on page" -> {
                ErrorMessage err = new ErrorMessage();
                err.setError("Error");
                err.setCurrentMoviesList(new ArrayList<>());
                err.setCurrentUser(null);
                output.addPOJO(err);
                break;
            }
            default -> {
                System.out.println("EROARE MASIVA IN VisitorHomeAUTH!!!!!");
            }
        }
    }
}
