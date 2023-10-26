/* Copyright (c) 2023, Magnet. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static com.chalwk.Misc.*;
import static com.chalwk.listener.Commands.commandHandler;

public final class Magnet extends JavaPlugin implements Listener {

    public static FileConfiguration config;
    static Magnet instance;

    public static FileConfiguration getPluginConfig() {
        return config;
    }

    public static Magnet getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;

        saveDefaultConfig();
        config = getConfig();
        config.options().copyDefaults(true);
        saveConfig();

        Bukkit.getPluginManager().registerEvents(this, this);
        Log("Magnet is enabled!");
        this.getServer().getScheduler().scheduleSyncRepeatingTask(this, new Magnetize(), 5L, 5L);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (hasPerm(player, "magnet.use")) {
            commandHandler(sender, args);
        } else {
            send(player, formatMSG(getString("no-permission")));
        }
        return true;
    }

    @Override
    public void onDisable() {
        Log("Magnet was disabled!");
    }
}
