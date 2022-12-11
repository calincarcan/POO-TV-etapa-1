package Data;

import iofiles.Action;
import lombok.Setter;

import java.util.ArrayList;

@Setter
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

    public ArrayList<User> getUsers() {
        return this.users;
    }

    public ArrayList<Movie> getMovies() {
        return this.movies;
    }

    public ArrayList<Action> getActions() {
        return this.actions;
    }

    public User getCurrUser() {
        return this.currUser;
    }

    public ArrayList<Movie> getCurrMovies() {
        return this.currMovies;
    }
}
