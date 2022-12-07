package Data;

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
    ArrayList<Movie> currMovies;
    public Database() {
        users = new ArrayList<>();
        movies = new ArrayList<>();
        actions = new ArrayList<>();
        currMovies = new ArrayList<>();
        currUser = null;
    }
}
