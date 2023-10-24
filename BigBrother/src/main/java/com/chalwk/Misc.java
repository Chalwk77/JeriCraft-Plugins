/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import static org.bukkit.Bukkit.getLogger;

public class Misc {

    public static void send(Player sender, String msg) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', msg));
    }

    public static void sound(Player player, String sound) {
        player.playSound(player.getLocation(), sound, 1, 1);
    }

    public static String formatMSG(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void Log(String message) {
        getLogger().info(message);
    }

    public static boolean hasPerm(Player player, String permission) {
        return player.hasPermission(permission);
    }
}
