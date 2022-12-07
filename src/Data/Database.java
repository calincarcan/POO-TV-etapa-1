package Data;

import com.fasterxml.jackson.databind.node.ArrayNode;
import iofiles.Action;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
@Setter
@Getter
public class Database {
    ArrayList<User> users;
    ArrayList<Movie> movies;
    ArrayList<Action> actions;
    User currUser;
    public Database() {
        users = new ArrayList<>();
        movies = new ArrayList<>();
        actions = new ArrayList<>();
        currUser = null;
    }
}
