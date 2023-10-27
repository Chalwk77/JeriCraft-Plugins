/* Copyright (c) 2023, HealMe. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class Misc {

    private static final FileConfiguration config = HealMe.getPluginConfig();

    public static void send(Player sender, String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void Log(String message) {
        getLogger().info(message);
    }

    public static String getString(String s) {
        return config.getString(s);
    }
}
