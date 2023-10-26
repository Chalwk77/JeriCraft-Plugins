/* Copyright (c) 2023, Magnet. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static com.chalwk.Magnet.getPluginConfig;
import static org.bukkit.Bukkit.getLogger;

public class Misc {

    private static final FileConfiguration config = getPluginConfig();

    public static void send(Player sender, String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static String formatMSG(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void Log(String message) {
        getLogger().info(message);
    }

    public static String getString(String s) {
        return config.getString(s);
    }

    public static boolean hasPerm(Player player, String permission) {
        return player.hasPermission(permission);
    }
}
