/* Copyright (c) 2023, JeriCraft-Plugins. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.listener;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.List;

import static com.chalwk.BigBrother.getPluginConfig;
import static com.chalwk.Spy.CommandSpy.commandSpy;

public class CommandPreprocess implements Listener {

    private static final FileConfiguration config = getPluginConfig();
    private static final List<String> hiddenCommands = config.getStringList("command-spy.hidden");

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String command = event.getMessage();
        Player player = event.getPlayer();
        commandSpy(player, command);
    }
}
