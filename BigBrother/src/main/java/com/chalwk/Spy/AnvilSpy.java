/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Spy;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static com.chalwk.Misc.*;

public class AnvilSpy implements Listener {

    private static boolean proceed(Player player) {

        // Check if this player has permission to see anvil spy notifications:
        boolean permission = hasPerm(player, getString("primary-permission")) && hasPerm(player, getString("anvil-spy.toggle-permission"));
        // Check if big brother is enabled for this player:
        boolean bbEnabled = PlayerDataManager.getData(player).activationState;
        // Check if sign spy is enabled for this player:
        boolean espyEnabled = PlayerDataManager.getData(player).anvils;

        return permission && bbEnabled && espyEnabled;
    }

    @EventHandler(priority = EventPriority.MONITOR)
    public void onInventoryClick(InventoryClickEvent event) {

        if (event.isCancelled()) {
            return;
        }

        HumanEntity ent = event.getWhoClicked();
        if (!(ent instanceof Player)) {
            return;
        }

        Player player = (Player) ent;
        Inventory inventory = event.getInventory();

        if (!(inventory instanceof AnvilInventory)) {
            return;
        } else if (!(proceed(player))) {
            return;
        }

        InventoryView view = event.getView();
        int rawSlot = event.getRawSlot();
        if (rawSlot != view.convertSlot(rawSlot) && rawSlot != 2) {
            return;
        }

        ItemStack item = event.getCurrentItem();
        if (item == null) {
            return;
        }

        ItemMeta meta = item.getItemMeta();
        if (meta != null && meta.hasDisplayName()) {

            String itemDisplayName = meta.getDisplayName();

            String playerName = player.getName();
            String notification = getString("anvil-spy.notification");
            notification = notification
                    .replace("{player}", playerName)
                    .replace("{item}", item.getType().toString())
                    .replace("{newname}", itemDisplayName);

            for (Player admin : Bukkit.getOnlinePlayers()) {
                //if (!(admin.getName().equals(playerName)) && proceed(admin)) {
                send(admin, formatMSG(notification));
                //}
            }
        }
    }
}
