package me.cole.managerplugin;

import me.cole.managerplugin.commands.FlyCommand;
import me.cole.managerplugin.commands.GamemodeCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Manager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Startup logic
        System.out.println("big poopy bum holes");

        // Register commands
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand(this));
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this));

        // Configuration
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
