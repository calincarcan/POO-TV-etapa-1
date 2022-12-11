package Data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@JsonIgnoreProperties({ "sumRatings" })
public class Movie {
    String name;
    int year;
    int duration;
    ArrayList<String> genres;
    ArrayList<String> actors;
    ArrayList<String> countriesBanned;
    int numLikes;
    double rating;
    double sumRatings;
    int numRatings;

    public Movie() {}
}
