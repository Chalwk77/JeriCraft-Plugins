/* Copyright (c) 2023, ExtraWeapons. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Items.BlazeGun;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
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

    @Override
    public void onEnable() {

        NamespacedKey itemGroupId = new NamespacedKey(this, "extra_weapons");
        ItemStack itemGroupItem = new CustomItemStack(Material.GOLDEN_SWORD, "&4Extra Weapons", "", "&a> Click to open");

        BlazeGun gun = getBlazeGun(itemGroupId, itemGroupItem);
        gun.register(this);

        NamespacedKey blazeGunId = new NamespacedKey(this, "blaze_gun");
        Research blazeGunResearch = new Research(blazeGunId, 7824, "Blaze Gun", 20);
        blazeGunResearch.addItems(gun);
        blazeGunResearch.register();
    }

    @NotNull
    private BlazeGun getBlazeGun(NamespacedKey itemGroupId, ItemStack itemGroupItem) {
        ItemGroup itemGroup = new ItemGroup(itemGroupId, itemGroupItem);
        SlimefunItemStack blazeGun = new SlimefunItemStack("BLAZE_GUN", Material.BLAZE_ROD, "&6Blaze Gun", "&bShoots burning magma cream");
        ItemStack[] gunRecipe = {
                new ItemStack(Material.BLAZE_POWDER), null, new ItemStack(Material.BLAZE_POWDER),
                null, new ItemStack(Material.STICK), null,
                new ItemStack(Material.BLAZE_POWDER), null, new ItemStack(Material.BLAZE_POWDER)
        };
        return new BlazeGun(itemGroup, blazeGun, RecipeType.ENHANCED_CRAFTING_TABLE, gunRecipe);
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
