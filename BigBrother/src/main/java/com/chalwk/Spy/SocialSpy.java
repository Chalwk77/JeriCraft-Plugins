/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.chalwk.BigBrother.getPluginConfig;
import static com.chalwk.Misc.hasPerm;

public class SocialSpy {

    private static final FileConfiguration config = getPluginConfig();
    private static final List<String> socialCommands = config.getStringList("social-spy.trigger-commands");

    public static boolean socialSpy(CommandSender sender, String command, String[] args) {
        Player player = (Player) sender;
        for (String s : socialCommands) {
            if (command.equalsIgnoreCase(s) && proceed(player)) {
                String senderName = player.getName();
                String commandArgs = command + " " + String.join(" ", args);
                for (Player admin : player.getServer().getOnlinePlayers()) {
                    if (!(admin.getName().equals(command)) && proceed(admin)) {
                        String notification = getString("social-spy.notification");
                        assert notification != null;
                        notification = notification
                                .replace("{player}", senderName)
                                .replace("{cmd}", commandArgs);
                        admin.sendMessage(notification);
                    }
                }
            }
            return true;
        }
        return false;
    }

    private static boolean proceed(Player player) {

        // Check if this player has permission to see social spy notifications:
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("social-spy.toggle-permission"));
        // Check if big brother is enabled for this player:
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        // Check if social spy is enabled for this player:
        boolean sspyEnabled = PlayerDataManager.getData(player).social;

        return permission && bbEnabled && sspyEnabled;
    }

    @Nullable
    private static String getString(String s) {
        return config.getString(s);
    }
}
