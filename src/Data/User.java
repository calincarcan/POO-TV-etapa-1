package Data;

import iofiles.Credentials;
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
}
