/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import com.chalwk.JCSpecials;
import org.bukkit.ChatColor;

public enum Messages {

    SMELTERS_SHOVEL_BLOCK_BREAK("items.smelters-shovel.block-break"),
    SMELTERS_SHOVEL_COOLDOWN("items.smelters-shovel.cooldown");

    private final String message;

    Messages(String path) {
        message = color(JCSpecials.getInstance().getConfig().getString("messages." + path));
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public String getMessage() {
        return message;
    }

}