package entities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String username;
    private String password;
    private String email;


    public static boolean validateEmail(String email){
        String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();

    }
    public static boolean validatePassword(String password){
        String passRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[.,-_;]).{7,15}$";
        Pattern pattern = Pattern.compile(passRegex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
