/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.Messages;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.chalwk.util.Recipes.BLAZE_GUN_AMMO;

public class BlazeGun extends SlimefunItem {

    private static final int cost = 20;
    private static final int researchID = 7500;
    private static final String defaultName = "Blaze Gun";
    private static final JCSpecials instance = JCSpecials.getInstance();

    public BlazeGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.register(instance);
        new Research(new NamespacedKey(instance, "blaze_gun"), researchID, defaultName, cost).addItems(this);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemUseRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemUseRightClick(PlayerRightClickEvent event) {

        Player p = event.getPlayer();
        Location location = p.getLocation();
        Inventory inv = p.getInventory();

        if (inv.containsAtLeast(BLAZE_GUN_AMMO, 1)) {
            inv.removeItem(BLAZE_GUN_AMMO);
            p.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 1, 1);
            p.spawnParticle(Particle.FLAME, location, 1);
            p.launchProjectile(Fireball.class).setIsIncendiary(true);
        } else {
            String itemName = BLAZE_GUN_AMMO.getItemMeta().getDisplayName();
            p.playSound(location, Sound.ENTITY_BLAZE_HURT, 1, 1);
            p.sendMessage(Messages.BLAZE_GUN_NO_AMMO.getMessage().replace("{item_name}", itemName));
        }
    }
}
