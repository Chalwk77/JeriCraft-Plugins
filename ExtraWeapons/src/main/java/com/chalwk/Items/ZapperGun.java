/* Copyright (c) 2023, JeriCraft-Plugins. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.chalwk.util.Recipes.ZAPPER_GUN_AMMO;
import static com.chalwk.util.util.send;

public class ZapperGun extends SlimefunItem {

    public ZapperGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemUseRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {

        Player player = event.getPlayer();
        Inventory inv = player.getInventory();
        String itemName = ZAPPER_GUN_AMMO.getItemMeta().getDisplayName();

        if (inv.containsAtLeast(ZAPPER_GUN_AMMO, 1)) {
            inv.removeItem(ZAPPER_GUN_AMMO);

            Location location = player.getLocation();
            World world = player.getWorld();

            Random random = ThreadLocalRandom.current();
            for (int i = 0; i < 5; i++) {
                world.spawnParticle(Particle.FIREWORKS_SPARK, location.add(random.nextDouble(), random.nextDouble(), random.nextDouble()), 1);
                world.strikeLightning(player.getTargetBlock(null, 100).getLocation());
            }

            player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
            player.spawnParticle(Particle.FIREWORKS_SPARK, player.getLocation(), 1);
        } else {
            player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
            send(player, "&cYou need &b" + itemName + " &cto shoot!");
        }
    }
}
