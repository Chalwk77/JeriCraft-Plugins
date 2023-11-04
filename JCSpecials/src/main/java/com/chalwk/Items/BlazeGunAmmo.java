/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import com.chalwk.JCSpecials;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.inventory.ItemStack;

public class BlazeGunAmmo extends SlimefunItem {

    public BlazeGunAmmo(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe, JCSpecials plugin) {
        super(itemGroup, item, recipeType, recipe);
        this.register(plugin);
        plugin.registerResearch("blaze_gun_ammo", 7501, "Blaze Gun Ammo", 10, item);
    }
}
