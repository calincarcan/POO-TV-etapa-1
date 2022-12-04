package iofiles;

import java.util.ArrayList;

public class Input {
    ArrayList<User> users;
    ArrayList<Movieio> movies;
    ArrayList<Action> actions;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Movieio> getMovies() {
        return movies;
    }

    public void setMovies(ArrayList<Movieio> movies) {
        this.movies = movies;
    }

    public ArrayList<Action> getActions() {
        return actions;
    }

    public void setActions(ArrayList<Action> actions) {
        this.actions = actions;
    }
}
