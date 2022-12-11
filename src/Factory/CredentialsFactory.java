package Factory;

import iofiles.Credentials;

public class CredentialsFactory {
    public static Credentials createCred (Credentials c) {
        Credentials newCred = new Credentials();
        newCred.setName(c.getName());
        newCred.setPassword(c.getPassword());
        newCred.setAccountType(c.getAccountType());
        newCred.setCountry(c.getCountry());
        newCred.setCountry(c.getCountry());
        newCred.setBalance(c.getBalance());
        return newCred;
    }
}
