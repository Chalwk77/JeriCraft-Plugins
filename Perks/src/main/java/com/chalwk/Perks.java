/* Copyright (c) 2023, Perks. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.commands.CommandManager;
import com.chalwk.listener.GUIListener;
import com.chalwk.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class Perks extends JavaPlugin {

    public static FileConfiguration config;
    static Perks instance;

    public static FileConfiguration getPluginConfig() {
        return config;
    }

    public static Perks getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;
        this.saveDefaultConfig();
        config = this.getConfig();
        config.options().copyDefaults(true);

        registerListeners();
        String pluginVersion = getDescription().getVersion();
        getLogger().info("Perks [" + pluginVersion + "] has been loaded!");
        getCommand("perks").setExecutor(new CommandManager());
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new GUIListener(), this);
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Perks has been unloaded!");
    }
}
