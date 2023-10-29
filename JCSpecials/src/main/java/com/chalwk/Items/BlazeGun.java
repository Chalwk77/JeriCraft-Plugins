/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.Messages;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;

import static com.chalwk.util.Items.BLAZE_GUN;
import static com.chalwk.util.Items.BLAZE_GUN_AMMO;

public class BlazeGun extends SlimefunItem {

    private static final JCSpecials instance = JCSpecials.getInstance();
    private final HashMap<UUID, Long> uses = new HashMap<>();
    private final int maxUses;

    public BlazeGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.register(instance);

        Config cfg = new Config(instance);
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

        if (inv.containsAtLeast(BLAZE_GUN_AMMO, 1)) {
            if (breakItem(p, location)) return;
            inv.removeItem(BLAZE_GUN_AMMO);
            p.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 1, 1);
            p.spawnParticle(Particle.FLAME, location, 1);
            p.launchProjectile(Fireball.class).setVelocity(location.getDirection().multiply(2));
        } else if (!breakItem(p, location)) {
            String itemName = BLAZE_GUN_AMMO.getItemMeta().getDisplayName();
            p.playSound(location, Sound.ENTITY_BLAZE_HURT, 1, 1);
            p.sendMessage(Messages.BLAZE_GUN_NO_AMMO.getMessage().replace("{item_name}", itemName));
        }
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
                return true;
            }
        } else {
            uses.put(uuid, 1L);
        }
        return false;
    }
}
