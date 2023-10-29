/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import com.chalwk.JCSpecials;
import org.bukkit.ChatColor;

public enum Messages {

    BLAZE_GUN_NO_AMMO("blaze-gun.no-ammo"),
    BLAZE_GUN_BROKE("blaze-gun.broke"),
    BLAZE_GUN_BOSS_BAR("blaze-gun.boss-bar"),
    ZAPPER_GUN_NO_AMMO("zapper-gun.no-ammo"),
    ZAPPER_GUN_BROKE("zapper-gun.broke"),
    ZAPPER_GUN_BOSS_BAR("zapper-gun.boss-bar"),
    SMELTERS_SHOVEL_BROKE("smelters-shovel.broke"),
    SMELTERS_SHOVEL_BOSS_BAR("smelters-shovel.boss-bar");

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