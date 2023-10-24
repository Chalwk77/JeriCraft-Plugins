/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Spy.AnvilSpy;
import com.chalwk.Spy.BookSpy;
import com.chalwk.Spy.SignSpy;
import com.chalwk.listener.Commands;
import com.chalwk.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import static com.chalwk.Misc.Log;

public final class BigBrother extends JavaPlugin {

    public static FileConfiguration config;
    static BigBrother instance;

    public static FileConfiguration getPluginConfig() {
        return config;
    }

    public static BigBrother getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        registerListeners();
        Log(config.getString("on-enable"));
    }

    @Override
    public void onDisable() {
        Log(config.getString("on-disable"));
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new SignSpy(), this);
        Bukkit.getPluginManager().registerEvents(new AnvilSpy(), this);
        Bukkit.getPluginManager().registerEvents(new BookSpy(), this);
        Bukkit.getPluginManager().registerEvents(new Commands(), this);
    }
}
