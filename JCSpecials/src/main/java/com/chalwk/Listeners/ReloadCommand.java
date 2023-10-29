/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.Listeners;

import com.chalwk.JCSpecials;
import com.chalwk.util.Messages;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import static com.chalwk.JCSpecials.cfg;

public class ReloadCommand implements Listener, CommandExecutor {

    public ReloadCommand(JCSpecials plugin) {
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        plugin.getCommand("jcspecials_reload").setExecutor(this);
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        Player player = (Player) commandSender;
        if (player.hasPermission("jcspecials.reload")) {
            player.sendMessage(Messages.PLUGIN_RELOAD.getMessage());
            cfg.reload();
        } else {
            player.sendMessage(Messages.NO_PERMISSION.getMessage());
        }
        return true;
    }
}
