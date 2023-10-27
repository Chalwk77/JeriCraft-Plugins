/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.listener;

import com.chalwk.BigBrother;
import com.chalwk.data.PlayerDataManager;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static com.chalwk.BigBrother.getPluginConfig;
import static com.chalwk.Misc.*;
import static com.chalwk.data.PlayerDataManager.getData;

public class Commands {

    private static final BigBrother instance = BigBrother.getInstance();
    private static final String toggleMessage = getString("toggle-message");
    private static final String toggleAllMessage = getString("toggle-all-message");

    public static boolean commandHandler(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        } else {

            Player player = (Player) sender;
            boolean commands = getData(player).commands;
            boolean signs = getData(player).signs;
            boolean anvils = getData(player).anvils;
            boolean books = getData(player).books;
            boolean social = getData(player).social;

            if (args.length == 0) {
                boolean activationState = getData(player).getActivationState();
                PlayerDataManager.getData(player).setActivationState(!activationState);
                notify(player, toggleMessage, activationState, "Big Brother", null);
            } else {

                String module = args[0].toLowerCase();
                switch (module) {
                    case "reload":
                        if (hasPerm(player, (getString("reload-permission")))) {
                            instance.reloadConfig();
                            send(player, formatMSG(getString("config-reload")));
                        } else {
                            send(player, formatMSG(getString("no-permission")));
                        }
                        break;
                    case "commands":
                        if (perm(player, getString("command-spy.toggle-permission"))) {
                            if (args.length == 1) {
                                PlayerDataManager.getData(player).setCommands(!commands);
                                notify(player, toggleMessage, commands, "Command Spy", null);
                            } else if (perm(player, getString("command-spy.toggle-others-permission"))) {
                                Player target = getPlayer(player, args);
                                if (target == null) return true;
                                PlayerDataManager.getData(target).setCommands(!commands);
                                notify(player, toggleAllMessage, commands, "Command Spy", target.getName());
                            }
                        }
                        break;
                    case "signs":
                        if (perm(player, getString("sign-spy.toggle-permission"))) {
                            if (args.length == 1) {
                                PlayerDataManager.getData(player).setSigns(!signs);
                                notify(player, toggleMessage, signs, "Sign Spy", null);
                            } else if (perm(player, getString("sign-spy.toggle-others-permission"))) {
                                Player target = getPlayer(player, args);
                                if (target == null) return true;
                                PlayerDataManager.getData(target).setSigns(!signs);
                                notify(player, toggleAllMessage, signs, "Sign Spy", target.getName());
                            }
                        }
                        break;
                    case "anvils":
                        if (perm(player, getString("anvil-spy.toggle-permission"))) {
                            if (args.length == 1) {
                                PlayerDataManager.getData(player).setAnvils(!anvils);
                                notify(player, toggleMessage, anvils, "Anvil Spy", null);
                            } else if (perm(player, getString("anvil-spy.toggle-others-permission"))) {
                                Player target = getPlayer(player, args);
                                if (target == null) return true;
                                PlayerDataManager.getData(target).setAnvils(!anvils);
                                notify(player, toggleAllMessage, anvils, "Anvil Spy", target.getName());
                            }
                        }
                        break;
                    case "books":
                        if (perm(player, getString("book-spy.toggle-permission"))) {
                            if (args.length == 1) {
                                PlayerDataManager.getData(player).setBooks(!books);
                                notify(player, toggleMessage, books, "Book Spy", null);
                            } else if (perm(player, getString("book-spy.toggle-others-permission"))) {
                                Player target = getPlayer(player, args);
                                if (target == null) return true;
                                PlayerDataManager.getData(target).setBooks(!books);
                                notify(player, toggleAllMessage, books, "Book Spy", target.getName());
                            }
                        }
                        break;
                    case "social":
                        if (perm(player, getString("social-spy.toggle-permission"))) {
                            if (args.length == 1) {
                                PlayerDataManager.getData(player).setSocial(!social);
                                notify(player, toggleMessage, social, "Social Spy", null);
                            } else if (perm(player, getString("social-spy.toggle-others-permission"))) {
                                Player target = getPlayer(player, args);
                                if (target == null) return true;
                                PlayerDataManager.getData(target).setSocial(!social);
                                notify(player, toggleAllMessage, social, "Social Spy", target.getName());
                            }
                        }
                        break;
                    default:
                        send(player, formatMSG(getString("invalid-module")));
                }
            }
        }
        return true;
    }

    private static void notify(Player sender, String toggleMessage, boolean books, String label, String targetName) {
        targetName = targetName == null ? "" : targetName;
        String message = toggleMessage
                .replace("{state}", books ? "disabled" : "enabled")
                .replace("{module}", label)
                .replace("{player}", targetName);
        send(sender, formatMSG(message));
    }

    @Nullable
    private static Player getPlayer(Player sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[1]);
        if (target == null) {
            send(sender, formatMSG(getString("player-not-found")));
            return null;
        }
        return target;
    }

    private static boolean perm(Player player, String permission) {
        if (!(player.hasPermission(permission))) {
            send(player, formatMSG(getString("no-permission")));
            return false;
        }
        return true;
    }
}
