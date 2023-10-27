/* Copyright (c) 2023, ExtraWeapons. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Items;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.handlers.ItemUseHandler;
import org.bukkit.inventory.ItemStack;

public class BlazeGun extends SlimefunItem {

    public BlazeGun(ItemGroup itemGroup, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(itemGroup, item, recipeType, recipe);
    }

    @Override
    public void preRegister() {
        ItemUseHandler itemUseHandler = this::onRightClick;
        addItemHandler(itemUseHandler);
    }

    private void onRightClick(PlayerRightClickEvent event) {
        event.cancel();
        event.getPlayer().sendMessage("You right clicked the Blaze Gun!");
    }
}
