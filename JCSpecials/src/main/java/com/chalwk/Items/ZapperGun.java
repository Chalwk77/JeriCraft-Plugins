/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.Messages;
import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.BlockPlaceHandler;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import javax.annotation.Nonnull;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static com.chalwk.util.Items.ZAPPER_GUN;
import static com.chalwk.util.Items.ZAPPER_GUN_AMMO;

public class ZapperGun extends SlimefunItem {

    private static final JCSpecials instance = JCSpecials.getInstance();
    private final HashMap<UUID, Long> uses = new HashMap<>();
    private final int maxUses;

    public ZapperGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.register(instance);

        Config cfg = new Config(instance);
        maxUses = cfg.getInt("item-settings.zapper-gun.uses");
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

    private void onItemUseRightClick(PlayerRightClickEvent e) {

        Player p = e.getPlayer();
        Location location = p.getLocation();
        Inventory inv = p.getInventory();

        e.cancel();
        if (inv.containsAtLeast(ZAPPER_GUN_AMMO, 1)) {

            if (breakItem(p, location)) return;

            inv.removeItem(ZAPPER_GUN_AMMO);
            World world = p.getWorld();

            Random random = ThreadLocalRandom.current();
            for (int i = 0; i < 5; i++) {
                world.spawnParticle(Particle.FIREWORKS_SPARK, location.add(random.nextDouble(), random.nextDouble(), random.nextDouble()), 1);
                world.strikeLightning(p.getTargetBlock(null, 100).getLocation());
            }

            p.playSound(p.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
            p.spawnParticle(Particle.FIREWORKS_SPARK, p.getLocation(), 1);
            p.sendActionBar(Messages.ZAPPER_GUN_BOSS_BAR.getMessage()
                    .replace("{uses}", String.valueOf(uses.get(p.getUniqueId())))
                    .replace("{max_uses}", String.valueOf(maxUses)));

        } else if (!breakItem(p, location)) {
            String itemName = ZAPPER_GUN_AMMO.getItemMeta().getDisplayName();
            p.playSound(p.getLocation(), Sound.ENTITY_BLAZE_HURT, 1, 1);
            p.sendMessage(Messages.ZAPPER_GUN_NO_AMMO.getMessage().replace("{item_name}", itemName));
        }
    }

    private boolean breakItem(Player p, Location location) {
        Long lastUse = uses.get(p.getUniqueId());
        UUID uuid = p.getUniqueId();
        if (lastUse != null) {
            uses.put(uuid, lastUse + 1);
            if (uses.get(uuid) >= maxUses) {
                p.getInventory().removeItem(ZAPPER_GUN);
                p.sendMessage(Messages.ZAPPER_GUN_BROKE.getMessage());
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
