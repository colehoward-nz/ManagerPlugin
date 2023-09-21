package me.cole.managerplugin;

import me.cole.managerplugin.commands.punish.MuteCommand;
import me.cole.managerplugin.commands.staff.*;
import me.cole.managerplugin.commands.util.SetSpawnCommand;
import me.cole.managerplugin.commands.util.SpawnCommand;
import me.cole.managerplugin.commands.staff.TeleportCommand;
import me.cole.managerplugin.commands.util.TimeCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Manager extends JavaPlugin {

    @Override
    public void onEnable() {
        // Startup logic
        //Manager plugin;
        System.out.println("[Manager] big poopy bum holes");

        // Register commands
        Objects.requireNonNull(getCommand("gamemode")).setExecutor(new GamemodeCommand(this));
        Objects.requireNonNull(getCommand("teleport")).setExecutor(new TeleportCommand(this));
        Objects.requireNonNull(getCommand("setspawn")).setExecutor(new SetSpawnCommand(this));
        Objects.requireNonNull(getCommand("spawn")).setExecutor(new SpawnCommand(this));
        Objects.requireNonNull(getCommand("mute")).setExecutor(new MuteCommand(this));
        Objects.requireNonNull(getCommand("time")).setExecutor(new TimeCommand(this));
        Objects.requireNonNull(getCommand("fly")).setExecutor(new FlyCommand(this));

        // Register listeners
        //getServer().getPluginManager().registerEvents(new onPlayerChat(plugin), this);

        // Configuration
        getConfig().options().copyDefaults();
        saveDefaultConfig();
    }
}
