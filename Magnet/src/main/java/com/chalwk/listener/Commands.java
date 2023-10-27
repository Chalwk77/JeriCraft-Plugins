/* Copyright (c) 2023, Magnet. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.listener;

import com.chalwk.Magnet;
import com.chalwk.data.PlayerDataManager;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static com.chalwk.Magnet.getPluginConfig;
import static com.chalwk.Misc.*;
import static com.chalwk.data.PlayerDataManager.getData;

public class Commands {

    private static final FileConfiguration config = getPluginConfig();
    private static final Magnet instance = Magnet.getInstance();
    private static final String toggleMessage = getString("toggle-message");

    public static boolean commandHandler(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        } else {

            Player player = (Player) sender;
            if (args.length == 0) {
                boolean activationState = getData(player).getActivationState();
                PlayerDataManager.getData(player).setActivationState(!activationState);
                notify(player, activationState);
            } else {
                String module = args[0].toLowerCase();
                if (module.equals("reload")) {
                    if (hasPerm(player, (getString("magnet.reload")))) {
                        instance.reloadConfig();
                        send(player, formatMSG(getString("config-reload")));
                    } else {
                        send(player, formatMSG(getString("no-permission")));
                    }
                } else {
                    send(player, formatMSG(getString("help")));
                }
            }
        }
        return true;
    }

    private static void notify(Player sender, boolean books) {
        String message = toggleMessage
                .replace("{state}", books ? "disabled" : "enabled")
                .replace("{module}", "Magnet");
        send(sender, formatMSG(message));
    }
}
