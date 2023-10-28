/* Copyright (c) 2023, ExtraWeapons. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.chalwk.util.Recipes.BLAZE_GUN_AMMO;
import static com.chalwk.util.util.send;

public class BlazeGun extends SlimefunItem {

    public BlazeGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemUseRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {

        Player player = event.getPlayer();
        Location location = player.getLocation();
        Inventory inv = player.getInventory();
        String itemName = BLAZE_GUN_AMMO.getItemMeta().getDisplayName();

        if (inv.containsAtLeast(BLAZE_GUN_AMMO, 1)) {
            inv.removeItem(BLAZE_GUN_AMMO);
            player.launchProjectile(Fireball.class);
            player.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 1, 1);
        } else {
            player.playSound(location, Sound.ENTITY_BLAZE_HURT, 1, 1);
            send(player, "&cYou need &b" + itemName + " &cto shoot!");
        }
    }
}
