/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEditBookEvent;

import static com.chalwk.Misc.*;

public class BookSpy implements Listener {

    private static boolean proceed(Player player) {
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("book-spy.toggle-permission"));
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        boolean bspyEnabled = PlayerDataManager.getData(player).books;
        return permission && bbEnabled && bspyEnabled;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onBook(PlayerEditBookEvent event) {

        Player player = event.getPlayer();
        String playerName = player.getName();

        for (Player admin : Bukkit.getOnlinePlayers()) {
            if (!(admin.getName().equals(playerName)) && proceed(admin)) {

                String text = event.getNewBookMeta().toString().substring(0, event.getNewBookMeta().toString().length() - 1);
                String notification = getString("book-spy.notification");
                assert notification != null;

                notification = notification.replace("{player}", playerName);
                notification = notification.replace("{text}", text.substring(33));
                send(admin, formatMSG(notification));
            }
        }
    }
}
