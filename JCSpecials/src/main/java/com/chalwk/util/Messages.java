/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import com.chalwk.JCSpecials;
import org.bukkit.ChatColor;

public enum Messages {

    BLAZE_GUN_NO_AMMO("items.blaze-gun.no-ammo"),
    ZAPPER_GUN_NO_AMMO("items.zapper-gun.no-ammo");

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