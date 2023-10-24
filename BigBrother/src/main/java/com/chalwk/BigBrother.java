/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Spy.AnvilSpy;
import com.chalwk.Spy.BookSpy;
import com.chalwk.Spy.SignSpy;
import com.chalwk.listener.CommandPreprocess;
import com.chalwk.listener.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static com.chalwk.Misc.Log;
import static com.chalwk.listener.Commands.commandHandler;

public final class BigBrother extends JavaPlugin implements Listener {

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
        Bukkit.getPluginManager().registerEvents(new CommandPreprocess(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        commandHandler(sender, args);
        return true;
    }
}
