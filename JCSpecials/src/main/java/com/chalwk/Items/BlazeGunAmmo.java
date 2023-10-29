/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

public class BlazeGunAmmo extends SlimefunItem {

    private static final int cost = 10;
    private static final int researchID = 7501;
    private static final String defaultName = "Blaze Gun Ammo";
    private static final JCSpecials instance = JCSpecials.getInstance();

    public BlazeGunAmmo(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
        this.register(instance);
        new Research(new NamespacedKey(instance, "blaze_gun_ammo"), researchID, defaultName, cost).addItems(this);
    }
}
