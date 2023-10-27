/* Copyright (c) 2023, HealMe. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.listener.Commands;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.chalwk.Misc.Log;

public final class HealMe extends JavaPlugin implements Listener {

    public static ArrayList<String> commands = new ArrayList<>();
    public static FileConfiguration config;
    static HealMe instance;

    public static HealMe getInstance() {
        return instance;
    }
    public static FileConfiguration getPluginConfig() {
        return config;
    }

    @Override
    public void onEnable() {

        instance = this;
        this.saveDefaultConfig();
        config = this.getConfig();
        config.options().copyDefaults(true);

        commands.add("feed");
        commands.add("heal");
        commands.add("healme_reload");

        Bukkit.getPluginManager().registerEvents(new Commands(), this);
        Commands command = new Commands();
        for (String s : commands) {
            getCommand(s).setExecutor(command);
        }

        Log("HealMe is enabled!");
    }

    @Override
    public void onDisable() {
        Log("HealMe was disabled!");
    }
}
