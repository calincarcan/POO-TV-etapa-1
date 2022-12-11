package Data;

import Visitor.Visitor;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
public class CurrentPage {
    String pageName;
    String currentVisitor;
    HashMap<String, Visitor> visitorColl;

    public CurrentPage() {
        setPageName("HomeNAUTH");
        setCurrentVisitor("HomeNAUTH");
        setVisitorColl(new HashMap<>());
    }
    public void resetUpgrades() {
        this.setPageName("upgrades");
        this.setCurrentVisitor("upgrades");
    }
    public void resetSeeDetails() {
        this.setPageName("seeDetails");
        this.setCurrentVisitor("seeDetails");
    }
    public void resetMovies() {
        this.setPageName("movies");
        this.setCurrentVisitor("movies");
    }
    public void resetHomeNAUTH() {
        this.setPageName("HomeNAUTH");
        this.setCurrentVisitor("HomeNAUTH");
    }
    public void resetHomeAUTH() {
        this.setPageName("HomeAUTH");
        this.setCurrentVisitor("HomeAUTH");
    }
    public void accept(Visitor visitor, Action action, Database db, ArrayNode output) {
        visitor.visit(this, action, db, output);
    }
}
