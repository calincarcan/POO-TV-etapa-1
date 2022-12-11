import Data.*;
import Filters.CountryFilter;
import Visitor.VisitorHomeNAUTH;
import Visitor.VisitorSeeDetails;
import Visitor.VisitorHomeAUTH;
import Visitor.VisitorMovies;
import Visitor.VisitorUpgrades;

import Factory.MovieFactory;
import Factory.UserFactory;
import Visitor.Visitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;
import iofiles.Input;
import iofiles.Movieio;
import iofiles.Userio;

import java.io.File;
import java.io.IOException;

public class Main {
    static int i = 1;
    public static void main(String[] args) throws IOException {
        CountryFilter.aolo = 0;
        String inPath = args[0];
        String outPath = args[1];
        ObjectMapper objectMapper = new ObjectMapper();
        // Input data received
        Input inputData = objectMapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectMapper.createArrayNode();
        // TODO: actual code starts here
        CurrentPage currentPage = new CurrentPage();

        currentPage.getVisitorColl().put("HomeNAUTH", new VisitorHomeNAUTH());
        currentPage.getVisitorColl().put("HomeAUTH", new VisitorHomeAUTH());
        currentPage.getVisitorColl().put("movies", new VisitorMovies());
        currentPage.getVisitorColl().put("seeDetails", new VisitorSeeDetails());
        currentPage.getVisitorColl().put("upgrades", new VisitorUpgrades());

        Database database = new Database();
        for (Userio user: inputData.getUsers()) {
            database.getUsers().add(UserFactory.createUser(user));
        }
        for (Movieio movie: inputData.getMovies()) {
            database.getMovies().add(MovieFactory.createMovie(movie));
        }
        database.setActions(inputData.getActions());
        for (Action action : database.getActions()) {
            Visitor visitor = currentPage.getVisitorColl().get(currentPage.getCurrentVisitor());
            currentPage.accept(visitor, action, database, output);
        }

        // Output data finished
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(outPath), output);
        objectWriter.writeValue(new File("checker/resources/out/out_" + i++ + ".json"), output);
    }
}
