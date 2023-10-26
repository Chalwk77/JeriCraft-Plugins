/* Copyright (c) 2023, NightVision. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.listener;

import com.chalwk.NightVision;
import com.chalwk.data.PlayerDataManager;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import static com.chalwk.Misc.*;
import static com.chalwk.NightVision.getPluginConfig;
import static com.chalwk.data.PlayerDataManager.getData;

public class Commands {

    private static final FileConfiguration config = getPluginConfig();
    private static final NightVision instance = NightVision.getInstance();
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
                toggle(player);

            } else {
                String module = args[0].toLowerCase();
                if (module.equals("reload")) {
                    if (hasPerm(player, (getString("nightvision.reload")))) {
                        config.options().copyDefaults(true);
                        instance.reloadConfig();
                        instance.saveConfig();
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

    private static void toggle(Player player) {
        Location loc = player.getLocation();
        player.playSound(loc, Sound.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 0.0F);
        loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2004);
    }

    private static void notify(Player sender, boolean books) {
        String message = toggleMessage
                .replace("{state}", books ? "disabled" : "enabled")
                .replace("{module}", "Night Vision");
        send(sender, formatMSG(message));
    }
}
