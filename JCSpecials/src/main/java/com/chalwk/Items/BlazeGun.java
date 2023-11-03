/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.Items;
import com.chalwk.util.Messages;
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

import java.util.HashMap;
import java.util.UUID;

import static com.chalwk.JCSpecials.cfg;
import static com.chalwk.util.Items.BLAZE_GUN;
import static com.chalwk.util.Items.BLAZE_GUN_AMMO;

public class BlazeGun extends SlimefunItem {

    private final HashMap<UUID, Long> uses = new HashMap<>();
    private final int maxUses;

    public BlazeGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, JCSpecials plugin) {
        super(itemGroup, item, recipeType, recipe);
        this.register(plugin);
        plugin.registerResearch("blaze_gun", 7500, "Blaze Gun", 20, Items.BLAZE_GUN);
        maxUses = cfg.getInt("item-settings.blaze-gun.uses");
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onItemUseRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onItemUseRightClick(PlayerRightClickEvent e) {

        Player p = e.getPlayer();
        Location location = p.getLocation();
        Inventory inv = p.getInventory();
        e.cancel();

        if (inv.containsAtLeast(BLAZE_GUN_AMMO, 1)) {
            if (breakItem(p, location)) return;
            inv.removeItem(BLAZE_GUN_AMMO);
            fireProjectile(p, location);
            p.sendActionBar(Messages.BLAZE_GUN_BOSS_BAR.getMessage()
                    .replace("{uses}", String.valueOf(uses.get(p.getUniqueId())))
                    .replace("{max_uses}", String.valueOf(maxUses)));
        } else if (!breakItem(p, location)) {
            String itemName = BLAZE_GUN_AMMO.getItemMeta().getDisplayName();
            p.playSound(location, Sound.ENTITY_BLAZE_HURT, 1, 1);
            p.sendMessage(Messages.BLAZE_GUN_NO_AMMO.getMessage().replace("{item_name}", itemName));
        }
    }

    private void fireProjectile(Player p, Location location) {
        p.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 1, 1);
        Fireball fireball = p.launchProjectile(Fireball.class);
        fireball.setVelocity(location.getDirection().multiply(2));
        fireball.setYield(5);
        fireball.setIsIncendiary(false);
    }

    private boolean breakItem(Player p, Location location) {
        Long lastUse = uses.get(p.getUniqueId());
        UUID uuid = p.getUniqueId();
        if (lastUse != null) {
            uses.put(uuid, lastUse + 1);
            if (uses.get(uuid) >= maxUses) {
                p.getInventory().removeItem(BLAZE_GUN);
                p.sendMessage(Messages.BLAZE_GUN_BROKE.getMessage());
                p.playSound(location, Sound.ENTITY_ITEM_BREAK, 1, 1);
                uses.put(uuid, 0L);
                return true;
            }
        } else {
            uses.put(uuid, 1L);
        }
        return false;
    }
}
