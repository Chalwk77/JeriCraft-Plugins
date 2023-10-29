/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import org.bukkit.ChatColor;

import static com.chalwk.JCSpecials.cfg;

public enum Messages {

    PLUGIN_RELOAD("plugin-reload"),
    NO_PERMISSION("no-permission"),
    BLAZE_GUN_NO_AMMO("items.blaze-gun.no-ammo"),
    BLAZE_GUN_BROKE("items.blaze-gun.broke"),
    BLAZE_GUN_BOSS_BAR("items.blaze-gun.boss-bar"),
    ZAPPER_GUN_NO_AMMO("items.zapper-gun.no-ammo"),
    ZAPPER_GUN_BROKE("items.zapper-gun.broke"),
    ZAPPER_GUN_BOSS_BAR("items.zapper-gun.boss-bar"),
    SMELTERS_SHOVEL_BROKE("items.smelters-shovel.broke"),
    SMELTERS_SHOVEL_BOSS_BAR("items.smelters-shovel.boss-bar");

    private final String message;

    Messages(String path) {
        message = color(cfg.getString("messages." + path));
    }

    private String color(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }

    public String getMessage() {
        return message;
    }

}