/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.ItemInteractHandler;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.attributes.DamageableItem;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SmeltersShovel extends SimpleSlimefunItem<ItemInteractHandler> implements DamageableItem {

    private static final JCSpecials instance = JCSpecials.getInstance();
    private final boolean damageable;

    public SmeltersShovel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {

        super(itemGroup, item, recipeType, recipe);
        this.register(instance);

        Config cfg = new Config(instance);
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

            Block b = e.getClickedBlock();
            if (b != null) {
                Player p = e.getPlayer();
                if (Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.BREAK_BLOCK)) {
                    switch (e.getAction()) {
                        case RIGHT_CLICK_BLOCK:
                            if (b.getType() == Material.SAND || b.getType() == Material.RED_SAND) {
                                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(b.getType() == Material.SAND ? Material.GLASS : Material.RED_STAINED_GLASS, 1));
                                b.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
                                b.setType(Material.AIR);
                                if (isDamageable()) {
                                    damageItem(p, e.getItem());
                                }
                            }
                            break;
                        case LEFT_CLICK_BLOCK:
                            if ((b.getType() != Material.SAND || b.getType() != Material.RED_SAND) && isDamageable()) {
                                damageItem(p, e.getItem());
                            }
                            break;
                        default:
                            break;
                    }
                }
            }
        };
    }

    private ToolUseHandler getToolUseHandler() {
        return (e, item, i, list) -> {
            if (isItem(item)) {
                //
                // for a future update
                //
            }
        };
    }

    @Override
    public boolean isDamageable() {
        return damageable;
    }
}
