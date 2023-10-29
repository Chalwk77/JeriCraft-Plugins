/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.Messages;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static com.chalwk.util.Items.BLAZE_GUN_AMMO;

public class BlazeGun extends SlimefunItem implements DamageableItem {

    private static final JCSpecials instance = JCSpecials.getInstance();
    private final boolean damageable;

    public BlazeGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.register(instance);

        Config cfg = new Config(instance);
        damageable = cfg.getBoolean("item-settings.blaze-gun.damageable");
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
            inv.removeItem(BLAZE_GUN_AMMO);
            p.playSound(location, Sound.ENTITY_BLAZE_SHOOT, 1, 1);
            p.spawnParticle(Particle.FLAME, location, 1);
            p.launchProjectile(Fireball.class).setIsIncendiary(true);
            if (isDamageable())
                damageItem(p);
        } else {
            String itemName = BLAZE_GUN_AMMO.getItemMeta().getDisplayName();
            p.playSound(location, Sound.ENTITY_BLAZE_HURT, 1, 1);
            p.sendMessage(Messages.BLAZE_GUN_NO_AMMO.getMessage().replace("{item_name}", itemName));
        }
    }

    private void damageItem(Player p) {
        ItemStack itemInMainHand = p.getInventory().getItemInMainHand();
        damageItem(p, itemInMainHand);
    }

    @Override
    public boolean isDamageable() {
        return damageable;
    }
}
