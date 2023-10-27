/* Copyright (c) 2023, HealMe. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.listener;

import com.chalwk.HealMe;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.chalwk.HealMe.getInstance;
import static com.chalwk.Misc.getString;
import static com.chalwk.Misc.send;

public class Commands implements CommandExecutor, Listener, TabCompleter {
    HealMe instance = getInstance();

    public static boolean hasPerm(Player player, String node) {
        if (player.hasPermission(node)) {
            return true;
        } else {
            send(player, getString("no-permission"));
            return false;
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {

        if (!(sender instanceof Player)) {
            sender.sendMessage("You must be a player to use this command!");
            return true;
        }

        String cmd = command.getName();
        Player player = (Player) sender;
        int gamemode = player.getGameMode().getValue();

        if (cmd.equalsIgnoreCase("heal") && hasPerm(player, "healme.health")) {
            if (gamemodeCheck(gamemode, player)) return true;
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
            if (player.getHealth() == maxHealth) {
                send(player, getString("health-already-full"));
                return true;
            }
            player.setHealth(maxHealth);
            send(player, getString("heal-message"));
            Effect(player);
        } else if (cmd.equalsIgnoreCase("feed") && hasPerm(player, "healme.hunger")) {
            if (gamemodeCheck(gamemode, player)) return true;
            if (player.getFoodLevel() == 20) {
                send(player, getString("hunger-already-full"));
                return true;
            }
            player.setFoodLevel(20);
            send(player, getString("feed-message"));
            Effect(player);
        } else if (cmd.equalsIgnoreCase("healme_reload") && hasPerm(player, "healme.reload")) {
            instance.reloadConfig();
            send(player, getString("config-reload"));
        }

        return true;
    }

    private boolean gamemodeCheck(int gamemode, Player player) {
        if (gamemode != 0) {
            send(player, getString("survival-mode-only"));
            return true;
        }
        return false;
    }

    private void Effect(Player player) {
        Location loc = player.getLocation();
        player.playSound(loc, Sound.ITEM_ARMOR_EQUIP_DIAMOND, 1.0F, 0.0F);
        loc.getWorld().playEffect(loc, Effect.MOBSPAWNER_FLAMES, 2004);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return HealMe.commands;
    }
}
