package com.krunal.demo.javaPractice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.UUID;

public class ExceptionHandling {

    public static void main(String[] args) {
        Repository repo = Repository.getInstance();

        try {
            repo.login("KrunalPate1", "Krunal@123");
        } catch (UserNotFoundException e) {
            e.printStackTrace();
        } catch (EmptyPasswordException | InvalidPasswordException e) {
            System.out.println(e.getMessage());
        }

        try {
            String token = repo.login("Kruna1Pate1", "Krunal@123").toString();
            System.out.println("Your unique id is " + token);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("Login attempt");
        }

        // Try with resource
        try {
            URL url = new URL("https://jsonplaceholder.typicode.com/todos/1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
                String line = reader.readLine();
                while (line != null) {
                    System.out.println(line);
                    line = reader.readLine();
                }
            } finally {
                System.out.println("Done reading");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

class Repository {

    private final UserDetails userDetails;
    private static Repository repository;

    public static Repository getInstance() {
        if (repository == null) {
            Repository.UserDetails userDetails = new Repository.UserDetails("Kruna1Pate1");
            userDetails.setPassword("Krunal@123");
            repository = new Repository(userDetails);
        }
        return repository;
    }

    private Repository(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    static class UserDetails {

        private final String user;
        private String password;

        UserDetails(String user) {
            this.user = user;
        }

        public String getUser() {
            return user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    public UUID login(String user, String password) throws EmptyPasswordException, InvalidPasswordException, UserNotFoundException {
        validate(password);
        if (!userDetails.user.equals(user)) {
            throw new UserNotFoundException("Invalid user");
        }
        System.out.println("Login successful");
        return UUID.randomUUID();
    }

    public boolean validate(String password) throws InvalidPasswordException, EmptyPasswordException {
        if (password.isEmpty()) {
            throw new EmptyPasswordException("Password is empty");
        } else if (!userDetails.password.equals(password)) {
            throw new InvalidPasswordException("Password does not match");
        } else {
            System.out.println("valid password");
            return true;
        }
    }
}

class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}

class InvalidPasswordException extends Exception {

    public InvalidPasswordException() {
        super();
    }

    public InvalidPasswordException(String message) {
        super(message);
    }
}

class EmptyPasswordException extends Exception {

    public EmptyPasswordException() {
        super();
    }

    public EmptyPasswordException(String message) {
        super(message);
    }
}
