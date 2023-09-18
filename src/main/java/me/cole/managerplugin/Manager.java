package me.cole.managerplugin;

import me.cole.managerplugin.commands.GamemodeCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Manager extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("big poopy bum holes");
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand());
    }
}
