/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Listeners;

import com.chalwk.JCSpecials;
import com.chalwk.util.ItemInteractHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class PlayerListener implements Listener {

    public PlayerListener(JCSpecials plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if (SlimefunUtils.canPlayerUseItem(player, e.getItem(), true)) {
            SlimefunItem item = SlimefunItem.getByItem(e.getItem());
            if (item == null) return;
            item.callItemHandler(ItemInteractHandler.class, handler -> handler.onInteract(e, item));
        }
    }
}
