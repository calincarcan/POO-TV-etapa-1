package iofiles;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Credentials {
    String name;
    String password;
    String accountType;
    String country;
    String balance;
}
