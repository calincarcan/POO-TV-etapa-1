package Factory;

import Data.Movie;
import Data.User;
import iofiles.Credentials;
import iofiles.Userio;

import java.util.ArrayList;

public class UserFactory {
    public static User createUser(User user) {
        User newUser = new User();
        newUser.setCredentials(CredentialsFactory.createCred(user.getCredentials()));
        newUser.setTokensCount(user.getTokensCount());
        newUser.setNumFreePremiumMovies(user.getNumFreePremiumMovies());
        ArrayList<Movie> auxList = new ArrayList<>();
        for (Movie movie : user.getPurchasedMovies()) {
            auxList.add(MovieFactory.createMovie(movie));
        }
        newUser.setPurchasedMovies(auxList);
        auxList = new ArrayList<>();
        for (Movie movie : user.getWatchedMovies()) {
            auxList.add(MovieFactory.createMovie(movie));
        }
        newUser.setWatchedMovies(auxList);
        auxList = new ArrayList<>();
        for (Movie movie : user.getLikedMovies()) {
            auxList.add(MovieFactory.createMovie(movie));
        }
        newUser.setLikedMovies(auxList);
        auxList = new ArrayList<>();
        for (Movie movie : user.getRatedMovies()) {
            auxList.add(MovieFactory.createMovie(movie));
        }
        newUser.setRatedMovies(auxList);
        return newUser;
    }
    public static User createUser(Userio user) {
        User newUser = new User();
        newUser.setCredentials(user.getCredentials());
        newUser.setTokensCount(0);
        newUser.setNumFreePremiumMovies(15);
        newUser.setPurchasedMovies(new ArrayList<>());
        newUser.setWatchedMovies(new ArrayList<>());
        newUser.setLikedMovies(new ArrayList<>());
        newUser.setRatedMovies(new ArrayList<>());
        return newUser;
    }
    public static User createUser(Credentials credentials) {
        User newUser = new User();
        newUser.setCredentials(credentials);
        newUser.setTokensCount(0);
        newUser.setNumFreePremiumMovies(15);
        newUser.setPurchasedMovies(new ArrayList<>());
        newUser.setWatchedMovies(new ArrayList<>());
        newUser.setLikedMovies(new ArrayList<>());
        newUser.setRatedMovies(new ArrayList<>());
        return newUser;
    }
}
