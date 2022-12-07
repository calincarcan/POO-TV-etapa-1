package iofiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Action {
    String type;
    String page;
    String movie;
    String feature;
    Credentials credentials;
    String startsWith;
    String count;
    int rate;
    Filtersio filters;
}