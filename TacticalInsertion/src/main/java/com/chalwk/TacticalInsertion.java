package com.chalwk;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.config.Config;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class TacticalInsertion extends JavaPlugin implements SlimefunAddon {

    @Override
    public void onEnable() {

        Config cfg = new Config(this);

        if (cfg.getBoolean("options.auto-update")) {
            //
            // for a future update
            //
        }

        ItemStack itemGroupItem = new CustomItemStack(Material.DIAMOND, "&4Test Category", "", "&a> Click to open");
        NamespacedKey itemGroupId = new NamespacedKey(this, "test_category");
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);

        SlimefunItemStack slimefunItem = new SlimefunItemStack("COOL_DIAMOND", Material.DIAMOND, "&4Cool Diamond", "&c+20% Coolness");
        SlimefunItem item = getSlimefunItem(itemGroup, slimefunItem);
        item.register(this);

        NamespacedKey researchKey = new NamespacedKey(this, "test_research");
        Research research = new Research(researchKey, 4825, "test_message", 10);
        research.addItems(item);
        research.register();
    }

    private SlimefunItem getSlimefunItem(ItemGroup itemGroup, SlimefunItemStack slimefunItem) {
        ItemStack[] recipe = {
                new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD),
                null, new ItemStack(Material.DIAMOND), null,
                new ItemStack(Material.EMERALD), null, new ItemStack(Material.EMERALD)};

        /* RECIPE LAYOUT:
            [E] [ ] [E]
            [ ] [D] [ ]
            [E] [ ] [E]
        */
        return new SlimefunItem(itemGroup, slimefunItem, RecipeType.ENHANCED_CRAFTING_TABLE, recipe);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/Chalwk77/JeriCraft-Plugins/issues";
    }

    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

}
