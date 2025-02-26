package org.nepezi;

import org.bukkit.entity.Player;
import org.mindrot.jbcrypt.BCrypt;
import java.util.HashMap;
import java.util.Map;

public class AuthenticationService {

    // A simple in-memory store for demonstration purposes. Replace this with a database or file.
    private final Map<String, String> userDatabase = new HashMap<>();

    // Register a new user
    public boolean registerUser(String username, String password) {
        if (userDatabase.containsKey(username)) {
            return false;  // User already exists
        }

        String salt = BCrypt.gensalt();  // Generate a salt
        String hashedPassword = BCrypt.hashpw(password, salt);  // Hash the password with the salt

        userDatabase.put(username, hashedPassword);  // Store hashed password in the database
        return true;
    }

    // Authenticate a user
    public boolean authenticateUser(String username, String password) {
        String storedHashedPassword = userDatabase.get(username);  // Retrieve stored hashed password

        if (storedHashedPassword == null) {
            return false;  // User not found
        }

        // Check if the provided password matches the stored hashed password
        return BCrypt.checkpw(password, storedHashedPassword);
    }
}
