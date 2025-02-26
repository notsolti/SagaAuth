package org.nepezi;

import org.bukkit.plugin.java.JavaPlugin;

public class SagaAuth extends JavaPlugin {

    @Override
    public void onEnable() {
        // Register the /register and /login commands
        this.getCommand("register").setExecutor(new AuthCommand());
        this.getCommand("login").setExecutor(new AuthCommand());
        getLogger().info("SagaAuth has been enabled.");
    }

    @Override
    public void onDisable() {
        getLogger().info("SagaAuth has been disabled.");
    }
}
