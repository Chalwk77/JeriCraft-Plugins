/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.chalwk.Misc.*;

public class CommandSpy {

    public static void commandSpy(CommandSender sender, String command) {

        String senderName = sender.getName();

        for (Player admin : Bukkit.getOnlinePlayers()) {
            if (!(admin.getName().equals(senderName)) && proceed(admin)) {
                String notification = getString("command-spy.notification");
                assert notification != null;
                notification = notification
                        .replace("{player}", senderName)
                        .replace("{cmd}", command);
                send(admin, formatMSG(notification));
            }
        }
    }

    private static boolean proceed(Player player) {

        // Check if this player has permission to see command spy notifications:
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("command-spy.toggle-permission"));
        // Check if big brother is enabled for this player:
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        // Check if command spy is enabled for this player:
        boolean cspyEnabled = PlayerDataManager.getData(player).commands;

        return permission && bbEnabled && cspyEnabled;
    }
}