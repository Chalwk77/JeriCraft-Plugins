/* Copyright (c) 2023, Magnet. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk;

import com.chalwk.data.PlayerDataManager;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import javax.xml.stream.Location;
import java.util.Iterator;

public class Magnetize implements Runnable {

    int maxDistance = Magnet.getPluginConfig().getInt("magnet-range");

    Magnetize() {

    }

    @Override
    public void run() {
        Iterator<World> worlds = Bukkit.getWorlds().iterator();
        repeat:
        while (worlds.hasNext()) {
            World world = worlds.next();
            Iterator<Entity> entities = world.getEntities().iterator();

            while (true) {
                Item item;
                Location location;
                do {
                    ItemStack stack;
                    do {
                        do {
                            Entity entity;
                            do {
                                if (!entities.hasNext()) {
                                    continue repeat;
                                }
                                entity = entities.next();
                            } while (!(entity instanceof Item));

                            item = (Item) entity;
                            stack = item.getItemStack();
                            location = (Location) item.getLocation();
                        } while (stack.getAmount() <= 0);
                    } while (item.isDead());
                } while (item.getPickupDelay() > item.getTicksLived());

                Player closestPlayer = null;
                double distanceSmall = maxDistance;

                for (Player player : Bukkit.getOnlinePlayers()) {
                    if (!PlayerDataManager.getData(player).activationState || !player.getWorld().getName().equals(world.getName()))
                        continue;
                    double playerDistance = player.getLocation().distance((org.bukkit.Location) location);
                    if (playerDistance < distanceSmall) {
                        distanceSmall = playerDistance;
                        closestPlayer = player;
                    }
                }

                if (closestPlayer != null) {
                    item.setVelocity(closestPlayer.getLocation().toVector().subtract(item.getLocation().toVector()).normalize());
                }
            }
        }
    }
}
