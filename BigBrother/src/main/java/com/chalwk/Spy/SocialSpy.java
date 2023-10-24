/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.chalwk.Misc.*;

public class SocialSpy {

    public static boolean socialSpy(CommandSender sender, String command) {
        Player player = (Player) sender;
        String senderName = player.getName();
        for (Player admin : player.getServer().getOnlinePlayers()) {
            if (!admin.getName().equals(senderName) && proceed(admin)) {
                String notification = getString("social-spy.notification");
                assert notification != null;
                notification = notification
                        .replace("{player}", senderName)
                        .replace("{cmd}", command);
                send(admin, formatMSG(notification));
                return true;
            }
        }
        return false;
    }

    private static boolean proceed(Player player) {
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("social-spy.toggle-permission"));
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        boolean sspyEnabled = PlayerDataManager.getData(player).social;
        return permission && bbEnabled && sspyEnabled;
    }
}
