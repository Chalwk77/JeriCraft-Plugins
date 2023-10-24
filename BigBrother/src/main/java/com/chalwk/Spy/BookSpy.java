/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.jetbrains.annotations.Nullable;

import static com.chalwk.BigBrother.getPluginConfig;
import static com.chalwk.Misc.*;

public class BookSpy implements Listener {

    private static final FileConfiguration config = getPluginConfig();

    private static boolean proceed(Player player) {

        // Check if this player has permission to see book spy notifications:
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("book-spy.toggle-permission"));
        // Check if big brother is enabled for this player:
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        // Check if book spy is enabled for this player:
        boolean bspyEnabled = PlayerDataManager.getData(player).books;

        return permission && bbEnabled && bspyEnabled;
    }

    @Nullable
    private static String getString(String s) {
        return config.getString(s);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBook(PlayerEditBookEvent event) {

        Player player = event.getPlayer();
        String playerName = player.getName();

        String text = event.getNewBookMeta().toString().substring(0, event.getNewBookMeta().toString().length() - 1);

        for (Player admin : Bukkit.getOnlinePlayers()) {
            if (!(admin.getName().equals(playerName)) && proceed(admin)) {

                String notification = getString("book-spy.notification");
                assert notification != null;

                notification = notification.replace("{player}", playerName);
                notification = notification.replace("{text}", text.substring(33));
                send(admin, formatMSG(notification));
            }
        }
    }
}
