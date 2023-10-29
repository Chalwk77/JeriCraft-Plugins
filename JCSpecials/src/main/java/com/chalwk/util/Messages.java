/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import com.chalwk.JCSpecials;
import org.bukkit.ChatColor;

public enum Messages {

    BLAZE_GUN_NO_AMMO("blaze-gun.no-ammo"),
    ZAPPER_GUN_NO_AMMO("zapper-gun.no-ammo"),
    BLAZE_GUN_BROKE("blaze-gun.broke"),
    SMELTERS_SHOVEL_BROKE("smelters-shovel.broke"),
    ZAPPER_GUN_BROKE("zapper-gun.broke");

    private final String message;

    Messages(String path) {
        message = color(JCSpecials.getInstance().getConfig().getString("messages.items." + path));
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public String getMessage() {
        return message;
    }

}