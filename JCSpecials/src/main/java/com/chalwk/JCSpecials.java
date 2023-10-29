/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Listeners.PlayerListener;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static com.chalwk.util.Recipes.*;

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

        getBlazeGun(parentCategory);
        getBlazeGunAmmo(parentCategory);
        getZapperGun(parentCategory);
        getZapperGunAmmo(parentCategory);
        getSmeltersShovel(parentCategory);
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
