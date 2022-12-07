package Visitor;

import Data.CurrentPage;
import Data.Database;
import Data.ErrorMessage;
import Factory.ErrorFactory;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;

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
                    currentPage.resetHomeNAUTH();
                    break;
                }
                if (action.getPage().equals("movies")) {
                    currentPage.setPageName("movies");
                    currentPage.setCurrentVisitor("movies");
                    ErrorMessage err = ErrorFactory.createErr(null,
                            db.getCurrMovies(), db.getCurrUser());
                    output.addPOJO(err);
                    break;
                }
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
