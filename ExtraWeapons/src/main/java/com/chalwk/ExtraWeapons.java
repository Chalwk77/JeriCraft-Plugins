/* Copyright (c) 2023, ExtraWeapons. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Items.BlazeGun;
import com.chalwk.Items.BlazeGunAmmo;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class ExtraWeapons extends JavaPlugin implements SlimefunAddon {

    public static ItemStack BLAZE_GUN_AMMO;

    @Override
    public void onEnable() {

        int researchID = 7500;

        /* CATEGORY */
        NamespacedKey categoryID = new NamespacedKey(this, "extra_weapons");
        ItemStack categoryItem = new CustomItemStack(Material.GOLDEN_SWORD, "&4Extra Weapons", "", "&a> Click to open");
        ItemGroup itemGroup = new ItemGroup(categoryID, categoryItem);

        /* ITEMS */
        BlazeGun blazeGunItem = getBlazeGun(itemGroup);
        blazeGunItem.register(this);

        BlazeGunAmmo blazeGunAmmoItem = getGunAmmo(itemGroup);
        blazeGunAmmoItem.register(this);

        registerResearch("blaze_gun", researchID, "Blaze Gun", 20, blazeGunItem.getItem());
        registerResearch("blaze_gun_ammo", ++researchID, "Blaze Gun Ammo", 10, blazeGunAmmoItem.getItem());
    }

    @NotNull
    private BlazeGunAmmo getGunAmmo(ItemGroup itemGroup) {
        SlimefunItemStack blazeGunAmmo = new SlimefunItemStack("BLAZE_GUN_AMMO", Material.MAGMA_CREAM, "&6Blaze Gun Ammo", "&bUsed to shoot blaze gun");
        BLAZE_GUN_AMMO = blazeGunAmmo;
        ItemStack[] blazeGunAmmoRecipe = {
                null, new ItemStack(Material.BLAZE_POWDER), null,
                new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.MAGMA_CREAM), new ItemStack(Material.BLAZE_POWDER),
                null, new ItemStack(Material.BLAZE_POWDER), null
        };

        return new BlazeGunAmmo(itemGroup, blazeGunAmmo, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunAmmoRecipe);
    }

    @NotNull
    private BlazeGun getBlazeGun(ItemGroup itemGroup) {
        SlimefunItemStack blazeGun = new SlimefunItemStack("BLAZE_GUN", Material.BLAZE_ROD, "&6Blaze Gun", "&bShoots burning magma cream");
        ItemStack[] blazeGunRecipe = {
                new ItemStack(Material.BLAZE_POWDER), null, new ItemStack(Material.BLAZE_POWDER),
                null, new ItemStack(Material.STICK), null,
                new ItemStack(Material.BLAZE_POWDER), null, new ItemStack(Material.BLAZE_POWDER)
        };

        return new BlazeGun(itemGroup, blazeGun, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunRecipe);
    }

    @Override
    public void onDisable() {

    }

    private void registerResearch(String key, int id, String name, int defaultCost, ItemStack... items) {
        Research research = new Research(new NamespacedKey(this, key), id, name, defaultCost);
        for (ItemStack item : items) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);
            if (sfItem != null) {
                research.addItems(sfItem);
            }
        }
        research.register();
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Chalwk77/JeriCraft-Plugins/issues";
    }

    @Override
    public @NotNull JavaPlugin getJavaPlugin() {
        return this;
    }
}
