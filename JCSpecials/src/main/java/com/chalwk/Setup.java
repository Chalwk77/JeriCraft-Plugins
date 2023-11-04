/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk;

import com.chalwk.Items.misc.Sponge;
import com.chalwk.Items.tools.SmeltersShovel;
import com.chalwk.Items.weapons.*;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import static com.chalwk.util.Items.*;

public final class Setup {

    private static final NestedItemGroup parentCategory = new NestedItemGroup(
            new NamespacedKey(JCSpecials.getInstance(), "jc_specials"),
            new CustomItemStack(Material.GOLDEN_SWORD, "&4JC Specials", "", "&a> Click to open"));
    private static final ItemGroup weapons = new SubItemGroup(
            new NamespacedKey(JCSpecials.getInstance(), "weapons"), parentCategory,
            new CustomItemStack(Material.DIAMOND_SWORD, "&aWeapons"), 1);
    private static final ItemGroup misc = new SubItemGroup(
            new NamespacedKey(JCSpecials.getInstance(), "misc"), parentCategory,
            new CustomItemStack(Material.HOPPER, "&aMisc"), 2);
    private static final ItemGroup tools = new SubItemGroup(
            new NamespacedKey(JCSpecials.getInstance(), "tools"), parentCategory,
            new CustomItemStack(Material.DIAMOND_PICKAXE, "&aTools"), 3);

    Setup(JCSpecials plugin, boolean debug) {

        if (debug) {
            plugin.getLogger().info("Setting up items...");
        }

        // weapons:
        new BlazeGun(weapons, BLAZE_GUN, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunRecipe, plugin);
        new BlazeGunAmmo(weapons, BLAZE_GUN_AMMO, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunAmmoRecipe, plugin);
        new ZapperGun(weapons, ZAPPER_GUN, RecipeType.ENHANCED_CRAFTING_TABLE, zapperGunRecipe, plugin);
        new ZapperGunAmmo(weapons, ZAPPER_GUN_AMMO, RecipeType.ENHANCED_CRAFTING_TABLE, zapperGunAmmoRecipe, plugin);
        new WitherGun(weapons, WITHER_GUN, RecipeType.ENHANCED_CRAFTING_TABLE, witherGunRecipe, plugin);
        new WitherGunAmmo(weapons, WITHER_GUN_AMMO, RecipeType.ENHANCED_CRAFTING_TABLE, witherGunAmmoRecipe, plugin);

        // misc:
        new Sponge(misc, SPONGE, RecipeType.ENHANCED_CRAFTING_TABLE, spongeRecipe, plugin);

        // tools:
        new SmeltersShovel(tools, SMELTERS_SHOVEL, RecipeType.ENHANCED_CRAFTING_TABLE, smeltersShovelRecipe, plugin);
    }
}
