/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import com.chalwk.util.ItemInteractHandler;
import com.chalwk.util.Messages;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ToolUseHandler;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.items.SimpleSlimefunItem;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.protection.Interaction;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

import static com.chalwk.util.Items.SMELTERS_SHOVEL;

public class SmeltersShovel extends SimpleSlimefunItem<ItemInteractHandler> {

    private static final JCSpecials instance = JCSpecials.getInstance();
    private final HashMap<UUID, Long> uses = new HashMap<>();
    private final int maxUses;

    public SmeltersShovel(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {

        super(itemGroup, item, recipeType, recipe);
        this.register(instance);

        Config cfg = new Config(instance);
        maxUses = cfg.getInt("item-settings.smelters-shovel.uses");
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
                Location location = p.getLocation();
                if (Slimefun.getProtectionManager().hasPermission(p, b.getLocation(), Interaction.BREAK_BLOCK)) {
                    switch (e.getAction()) {
                        case RIGHT_CLICK_BLOCK:
                            if (b.getType() == Material.SAND || b.getType() == Material.RED_SAND) {
                                if (breakItem(p, location)) return;

                                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(b.getType() == Material.SAND ? Material.GLASS : Material.RED_STAINED_GLASS, 1));
                                b.getWorld().playEffect(b.getLocation(), Effect.MOBSPAWNER_FLAMES, 1);
                                b.setType(Material.AIR);
                            }
                            break;
                        case LEFT_CLICK_BLOCK:
                            if ((b.getType() != Material.SAND || b.getType() != Material.RED_SAND)) {
                                // do nothing (for a future update)
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

    private boolean breakItem(Player p, Location location) {
        Long lastUse = uses.get(p.getUniqueId());
        UUID uuid = p.getUniqueId();
        if (lastUse != null) {
            uses.put(uuid, lastUse + 1);
            if (uses.get(uuid) >= maxUses) {
                p.getInventory().removeItem(SMELTERS_SHOVEL);
                p.sendMessage(Messages.SMELTERS_SHOVEL_BROKE.getMessage());
                p.playSound(location, Sound.ENTITY_ITEM_BREAK, 1, 1);
                return true;
            }
        } else {
            uses.put(uuid, 1L);
        }
        return false;
    }
}
