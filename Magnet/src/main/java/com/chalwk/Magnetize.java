/* Copyright (c) 2023, Magnet. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Magnetize implements Runnable {

    static int maxDistance = Magnet.getPluginConfig().getInt("magnet-range");

    Magnetize() {

    }

    @Override
    public void run() {
        for (World world : Bukkit.getWorlds()) {
            for (Entity entity : world.getEntities()) {
                if (entity instanceof Item) {
                    Item item = (Item) entity;
                    ItemStack stack = item.getItemStack();
                    Location location = item.getLocation();
                    if (stack.getAmount() > 0 && !item.isDead() && item.getPickupDelay() < item.getTicksLived()) {
                        Player closestPlayer = null;
                        double distance = maxDistance;
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (PlayerDataManager.getData(player).activationState && player.getWorld().getName().equals(world.getName())) {
                                double playerDistance = player.getLocation().distance(location);
                                if (playerDistance < distance) {
                                    distance = playerDistance;
                                    closestPlayer = player;
                                }
                            }
                        }
                        if (closestPlayer != null) {
                            item.setVelocity(closestPlayer.getLocation().toVector().subtract(item.getLocation().toVector()).normalize());
                        }
                    }
                }
            }
        }
    }
}
