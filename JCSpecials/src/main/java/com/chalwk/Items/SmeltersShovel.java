/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.ItemInteractHandler;
import com.chalwk.util.Messages;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class SmeltersShovel extends SimpleSlimefunItem<ItemInteractHandler> implements DamageableItem {

    private static final int cost = 20;
    private static final int researchID = 7504;
    private static final String defaultName = "Smelters Shovel";
    private static final JCSpecials instance = JCSpecials.getInstance();
    private final int cooldown;
    private final boolean damageable;
    private final HashMap<UUID, Long> lastUses = new HashMap<>();

    public SmeltersShovel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {

        super(itemGroup, item, recipeType, recipe);
        this.register(instance);
        new Research(new NamespacedKey(instance, "smelters_shovel"), researchID, defaultName, cost).addItems(this);

        Config cfg = new Config(instance);
        cooldown = cfg.getInt("item-settings.smelters-shovel.cooldown");
        damageable = cfg.getBoolean("item-settings.smelters-shovel.damageable");
    }

    @Override
    public void preRegister() {
        super.preRegister();
        addItemHandler(getToolUseHandler());
    }

    @Override
    public @NotNull ItemInteractHandler getItemHandler() {
        return (e, slimefunItem) -> {
            if (!slimefunItem.getId().equals(getId())) {
                return;
            }
            e.setCancelled(true);

            Block b = e.getClickedBlock();
            if (b != null) {
                Player p = e.getPlayer();
                if (Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.BREAK_BLOCK)) {
                    if (cooldown(p)) return;
                    switch (e.getAction()) {
                        case RIGHT_CLICK_BLOCK:
                            e.setCancelled(true);
                            break;
                        case LEFT_CLICK_BLOCK:
                            if (b.getType() == Material.SAND) {
                                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GLASS, 1));
                                b.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
                                b.setType(Material.AIR);
                                if (isDamageable()) {
                                    damageItem(p, e.getItem());
                                }
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
            e.setCancelled(true);
        };
    }

    private boolean cooldown(Player p) {
        Long lastUse = lastUses.get(p.getUniqueId());
        UUID uuid = p.getUniqueId();
        if (lastUse != null) {
            if ((System.currentTimeMillis() - lastUse) < cooldown) {
                p.sendMessage(
                        Messages.SMELTERS_SHOVEL_COOLDOWN.getMessage().replace(
                                "{time}",
                                String.valueOf(cooldown - (System.currentTimeMillis() - lastUse)))
                );
                return true;
            }
        }
        lastUses.put(uuid, System.currentTimeMillis());
        return false;
    }

    private ToolUseHandler getToolUseHandler() {
        return (e, item, i, list) -> {
            if (isItem(item)) {
                e.setCancelled(true);
                e.getPlayer().sendMessage(Messages.SMELTERS_SHOVEL_BLOCK_BREAK.getMessage());
            }
        };
    }

    @Override
    public boolean isDamageable() {
        return damageable;
    }
}
