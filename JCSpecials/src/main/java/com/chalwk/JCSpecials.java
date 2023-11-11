/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Listeners.PlayerListener;
import com.chalwk.Listeners.ReloadCommand;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public class JCSpecials extends JavaPlugin implements SlimefunAddon {

    public static Config cfg;
    public static boolean debug = true;
    private static JCSpecials instance;

    public static JCSpecials getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {

        cfg = new Config(this);
        debug = cfg.getBoolean("options.debug");
        instance = this;

        if (debug) {
            getLogger().info("Enabling...");
        }
        new PlayerListener(this);
        new ReloadCommand(this);
        new Setup(this, debug);
    }

    @Override
    public void onDisable() {
        if (debug) {
            getLogger().info("Disabling...");
        }
    }


    public void registerResearch(String key, int id, String name, int defaultCost, ItemStack... items) {
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
