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
import static com.chalwk.Spy.SocialSpy.socialSpy;

public class CommandPreprocess implements Listener {

    private static final FileConfiguration config = getPluginConfig();
    private static final List<String> socialCommands = config.getStringList("social-spy.trigger-commands");
    private static final List<String> hiddenCommands = config.getStringList("command-spy.hidden");

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        Player player = event.getPlayer();
        String command = event.getMessage();
        if (socialCommands.stream().anyMatch(command::startsWith)) {
            socialSpy(player, command);
        } else if (hiddenCommands.stream().noneMatch(command::startsWith)) {
            commandSpy(player, command);
        }
    }
}
