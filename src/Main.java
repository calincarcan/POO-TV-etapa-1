import data.CurrentPage;
import data.Database;
import filters.CountryFilter;
import visitor.VisitorHomeNAUTH;
import visitor.VisitorSeeDetails;
import visitor.VisitorHomeAUTH;
import visitor.VisitorMovies;
import visitor.VisitorUpgrades;

import factory.MovieFactory;
import factory.UserFactory;
import visitor.Visitor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;
import iofiles.Input;
import iofiles.Movieio;
import iofiles.Userio;

import java.io.File;
import java.io.IOException;

public final class Main {
    // var used in debug
    // static int outputNr = 1;
    public static void main(String[] args) throws IOException {
        // var used in debug
        CountryFilter.debugVar = 0;
        String inPath = args[0];
        String outPath = args[1];
        ObjectMapper objectMapper = new ObjectMapper();
        // Input data received
        Input inputData = objectMapper.readValue(new File(inPath), Input.class);
        ArrayNode output = objectMapper.createArrayNode();
        // Actual code starts here
        CurrentPage currentPage = new CurrentPage();
        // Visitors initialized
        currentPage.getVisitorColl().put("HomeNAUTH", new VisitorHomeNAUTH());
        currentPage.getVisitorColl().put("HomeAUTH", new VisitorHomeAUTH());
        currentPage.getVisitorColl().put("movies", new VisitorMovies());
        currentPage.getVisitorColl().put("seeDetails", new VisitorSeeDetails());
        currentPage.getVisitorColl().put("upgrades", new VisitorUpgrades());
        // Database populated
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
        // Output for debug
        // objectWriter.writeValue(new File("checker/resources/out/out_" + outputNr++ + ".json"), output);
    }
}
