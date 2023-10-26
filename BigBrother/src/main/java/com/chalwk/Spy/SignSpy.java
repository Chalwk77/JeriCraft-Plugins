/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

import static com.chalwk.Misc.*;

public class SignSpy implements Listener {

    private static boolean proceed(Player player) {
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("sign-spy.toggle-permission"));
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        boolean sspyEnabled = PlayerDataManager.getData(player).signs;
        return permission && bbEnabled && sspyEnabled;
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
                    notification = notification.replace("{line" + (i + 1) + "}", line);
                }
                if (notification.isEmpty()) {
                    return;
                }
                send(admin, formatMSG(notification));
            }
        }
    }
}
