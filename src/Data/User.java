package Data;

import iofiles.Credentials;
import iofiles.Userio;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class User {
    Credentials credentials;
    int tokensCount;
    int numFreePremiumMovies;
    ArrayList<Movie> purchasedMovies;
    ArrayList<Movie> watchedMovies;
    ArrayList<Movie> likedMovies;
    ArrayList<Movie> ratedMovies;

    public User(User user) {
        this.credentials = user.getCredentials();
        this.tokensCount = user.getTokensCount();
        this.numFreePremiumMovies = user.getNumFreePremiumMovies();
        this.purchasedMovies = user.getPurchasedMovies();
        this.watchedMovies = user.getWatchedMovies();
        this.likedMovies = user.getLikedMovies();
        this.ratedMovies = user.getRatedMovies();
    }

    public User() { }
}
