/* Copyright (c) 2023, JeriCraft-Plugins. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.Messages;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import static com.chalwk.util.Recipes.ZAPPER_GUN_AMMO;

public class ZapperGun extends SlimefunItem {

    private static final int cost = 20;
    private static final int researchID = 7502;
    private static final String defaultName = "Zapper Gun";
    private static final JCSpecials instance = JCSpecials.getInstance();

    public ZapperGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.register(instance);
        new Research(new NamespacedKey(instance, "zapper_gun"), researchID, defaultName, cost).addItems(this);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemUseRightClick;
        addItemHandler(itemUseHandler);
        addItemHandler(
                new BlockPlaceHandler(false) {
                    @Override
                    public void onPlayerPlace(@Nonnull BlockPlaceEvent event) {
                        event.setCancelled(true);
                    }
                }
        );
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {

        Player p = event.getPlayer();
        Inventory inv = p.getInventory();

        if (inv.containsAtLeast(ZAPPER_GUN_AMMO, 1)) {
            inv.removeItem(ZAPPER_GUN_AMMO);

            Location location = p.getLocation();
            World world = p.getWorld();

            Random random = ThreadLocalRandom.current();
            for (int i = 0; i < 5; i++) {
                world.spawnParticle(Particle.FIREWORKS_SPARK, location.add(random.nextDouble(), random.nextDouble(), random.nextDouble()), 1);
                world.strikeLightning(p.getTargetBlock(null, 100).getLocation());
            }

            p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
            p.spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 1);
        } else {
            String itemName = ZAPPER_GUN_AMMO.getItemMeta().getDisplayName();
            p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
            p.sendMessage(Messages.ZAPPER_GUN_NO_AMMO.getMessage().replace("{item_name}", itemName));
        }
    }
}
