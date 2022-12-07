package Data;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class Movie {
    String name;
    int year;
    int duration;
    ArrayList<String> genres;
    ArrayList<String> actors;
    ArrayList<String> countriesBanned;
    int numLikes;
    int rating;
    int numRatings;

    public Movie() {}
}
