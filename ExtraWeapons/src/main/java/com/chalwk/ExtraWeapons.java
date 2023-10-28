/* Copyright (c) 2023, ExtraWeapons. Jericho Crosby <jericho.crosby227@gmail.com> */

package com.chalwk;

import com.chalwk.Items.BlazeGun;
import com.chalwk.Items.BlazeGunAmmo;
import com.chalwk.Items.ZapperGun;
import com.chalwk.Items.ZapperGunAmmo;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import static com.chalwk.util.Recipes.*;

public class ExtraWeapons extends JavaPlugin implements SlimefunAddon {

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

        BlazeGunAmmo blazeGunAmmoItem = getBlazeGunAmmo(itemGroup);
        blazeGunAmmoItem.register(this);

        ZapperGun zapperGunItem = getZapperGun(itemGroup);
        zapperGunItem.register(this);

        ZapperGunAmmo zapperGunAmmoItem = getZapperGunAmmo(itemGroup);
        zapperGunAmmoItem.register(this);

        /* RESEARCHES */
        registerResearch("blaze_gun", researchID, "Blaze Gun", 20, blazeGunItem.getItem());
        registerResearch("blaze_gun_ammo", ++researchID, "Blaze Gun Ammo", 10, blazeGunAmmoItem.getItem());
        registerResearch("zapper_gun", ++researchID, "Zapper Gun", 20, zapperGunItem.getItem());
        registerResearch("zapper_gun_ammo", ++researchID, "Zapper Gun Ammo", 10, zapperGunAmmoItem.getItem());
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
