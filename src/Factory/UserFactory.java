package Factory;

import Data.User;
import iofiles.Credentials;
import iofiles.Userio;

import java.util.ArrayList;

public class UserFactory {
    public static User createUser(User user) {
        User newUser = new User();
        newUser.setCredentials(user.getCredentials());
        newUser.setTokensCount(user.getTokensCount());
        newUser.setNumFreePremiumMovies(user.getNumFreePremiumMovies());
        newUser.setPurchasedMovies(user.getPurchasedMovies());
        newUser.setWatchedMovies(user.getWatchedMovies());
        newUser.setLikedMovies(user.getLikedMovies());
        newUser.setRatedMovies(user.getRatedMovies());
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
