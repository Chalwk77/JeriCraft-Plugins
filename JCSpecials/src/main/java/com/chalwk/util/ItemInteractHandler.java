/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import io.github.thebusybiscuit.slimefun4.api.items.ItemHandler;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

@FunctionalInterface
public interface ItemInteractHandler extends ItemHandler {

    void onInteract(PlayerInteractEvent e, SlimefunItem slimefunItem);

    @Override
    default @NotNull Class<? extends ItemHandler> getIdentifier() {
        return ItemInteractHandler.class;
    }
}