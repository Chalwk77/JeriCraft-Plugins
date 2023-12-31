/* Copyright (c) 2023, NightVision. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.listener.JoinListener;
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

public final class NightVision extends JavaPlugin implements Listener {

    public static FileConfiguration config;
    static NightVision instance;

    public static FileConfiguration getPluginConfig() {
        return config;
    }

    public static NightVision getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        instance = this;
        this.saveDefaultConfig();
        config = this.getConfig();
        config.options().copyDefaults(true);

        Bukkit.getPluginManager().registerEvents(new JoinListener(), this);
        Bukkit.getPluginManager().registerEvents(this, this);
        Log("Night Vision is enabled!");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if (hasPerm(player, "nightvision.use")) {
            commandHandler(sender, args);
        } else {
            send(player, formatMSG(getString("no-permission")));
        }
        return true;
    }

    @Override
    public void onDisable() {
        Log("Night Vision was disabled!");
    }
}
