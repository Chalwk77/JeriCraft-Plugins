/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.jetbrains.annotations.Nullable;

import static com.chalwk.BigBrother.getPluginConfig;
import static com.chalwk.Misc.*;

public class SignSpy implements Listener {

    private static final FileConfiguration config = getPluginConfig();

    private static boolean proceed(Player player) {

        // Check if this player has permission to see sign spy notifications:
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("sign-spy.toggle-permission"));
        // Check if big brother is enabled for this player:
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        // Check if sign spy is enabled for this player:
        boolean sspyEnabled = PlayerDataManager.getData(player).signs;

        return permission && bbEnabled && sspyEnabled;
    }

    @Nullable
    private static String getString(String s) {
        return config.getString(s);
    }

    @EventHandler
    public void onInteract(SignChangeEvent sign) {

        Player player = sign.getPlayer();
        String playerName = player.getName();

        for (Player admin : Bukkit.getOnlinePlayers()) {
            if (!(admin.getName().equals(playerName)) && proceed(admin)) {

                String notification = getString("sign-spy.notification");
                notification = notification.replace("{player}", playerName);

                for (int i = 0; i < 4; i++) {
                    String line = sign.getLine(i);
                    if (line.isEmpty()) {
                        notification = notification.replace("{line" + i + "}", "");
                        continue;
                    }
                    notification = notification.replace("{line" + i + "}", line);
                }
                send(admin, formatMSG(notification));
            }
        }
    }
}
