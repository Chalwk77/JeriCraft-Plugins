/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Items.*;
import com.chalwk.Listeners.PlayerListener;
import com.chalwk.util.Items;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static com.chalwk.util.Items.*;

public class JCSpecials extends JavaPlugin implements SlimefunAddon {

    private static JCSpecials instance;
    private ItemGroup parentCategory;
    private boolean debug = true;

    public static JCSpecials getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        Config cfg = new Config(this);
        debug = cfg.getBoolean("options.debug");
        instance = this;

        if (debug) {
            getLogger().info("Enabling...");
        }
        new PlayerListener(this);

        setupCategories();
        setupItems();
    }

    @Override
    public void onDisable() {
        if (debug) {
            getLogger().info("Disabling...");
        }
    }

    private void setupCategories() {

        if (debug) {
            getLogger().info("Setting up categories...");
        }

        NamespacedKey categoryKey = new NamespacedKey(this, "jc_specials");
        CustomItemStack categoryItem = new CustomItemStack(Material.GOLDEN_SWORD, "&4JCSpecials", "", "&a> Click to open");
        parentCategory = new ItemGroup(categoryKey, categoryItem);
    }

    private void setupItems() {

        if (debug) {
            getLogger().info("Setting up items...");
        }

        new BlazeGun(parentCategory, Items.BLAZE_GUN, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunRecipe);
        new BlazeGunAmmo(parentCategory, Items.BLAZE_GUN_AMMO, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunAmmoRecipe);
        new ZapperGun(parentCategory, Items.ZAPPER_GUN, RecipeType.ENHANCED_CRAFTING_TABLE, zapperGunRecipe);
        new ZapperGunAmmo(parentCategory, Items.ZAPPER_GUN_AMMO, RecipeType.ENHANCED_CRAFTING_TABLE, zapperGunAmmoRecipe);
        new SmeltersShovel(parentCategory, Items.SMELTERS_SHOVEL, RecipeType.ENHANCED_CRAFTING_TABLE, smeltersShovelRecipe);
        new Sponge(parentCategory, Items.SPONGE, RecipeType.ENHANCED_CRAFTING_TABLE, spongeRecipe);

        registerResearch("blaze_gun", 7500, "Blaze Gun", 20, Items.BLAZE_GUN);
        registerResearch("blaze_gun_ammo", 7501, "Blaze Gun Ammo", 10, Items.BLAZE_GUN_AMMO);
        registerResearch("zapper_gun", 7502, "Zapper Gun", 20, Items.ZAPPER_GUN);
        registerResearch("zapper_gun_ammo", 7503, "Zapper Gun Ammo", 10, Items.ZAPPER_GUN_AMMO);
        registerResearch("smelters_shovel", 7504, "Smelters Shovel", 35, Items.SMELTERS_SHOVEL);
        registerResearch("sponge", 7505, "Sponge", 10, Items.SPONGE);
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
