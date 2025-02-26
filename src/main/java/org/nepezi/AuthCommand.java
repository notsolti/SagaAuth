package org.nepezi;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class AuthCommand implements CommandExecutor {

    private final AuthenticationService authenticationService = new AuthenticationService();

    // Register command
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Use pattern variable to directly check if sender is a player
        if (sender instanceof Player player) {

            // Check if arguments are provided
            if (args.length < 1) {
                player.sendMessage("Usage: /" + label + " <password>");
                return false;
            }

            String password = args[0];

            if (label.equalsIgnoreCase("register")) {
                // Handle registration
                if (authenticationService.registerUser(player.getName(), password)) {
                    player.sendMessage("Successfully registered!");
                } else {
                    player.sendMessage("Registration failed! User may already exist.");
                }
            } else if (label.equalsIgnoreCase("login")) {
                // Handle login
                if (authenticationService.authenticateUser(player.getName(), password)) {
                    player.sendMessage("Successfully logged in!");
                } else {
                    player.sendMessage("Login failed! Incorrect password.");
                }
            } else {
                player.sendMessage("Invalid command. Use /register or /login.");
            }

            return true;
        } else {
            sender.sendMessage("This command can only be run by a player.");
            return false;
        }
    }
}
