/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.listener;

import com.chalwk.BigBrother;
import com.chalwk.data.PlayerDataManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static com.chalwk.BigBrother.getPluginConfig;
import static com.chalwk.Misc.*;
import static com.chalwk.Spy.CommandSpy.commandSpy;
import static com.chalwk.Spy.SocialSpy.socialSpy;
import static com.chalwk.data.PlayerDataManager.getData;

public class Commands implements CommandExecutor, Listener {

    private static final FileConfiguration config = getPluginConfig();

    private static final BigBrother instance = BigBrother.getInstance();


    @Nullable
    private static String getString(String s) {
        return config.getString(s);
    }

    private static final List<String> hiddenCommands = config.getStringList("command-spy.hidden");

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            return true;
        } else if (!command.getName().equalsIgnoreCase("bigbrother")) {

            Player player = (Player) sender;
            if (args[0].equalsIgnoreCase("reload")) {
                if (hasPerm(player, (config.getString("reload-permission")))) {
                    config.options().copyDefaults(true);
                    instance.saveConfig();
                    instance.reloadConfig();
                    send(player, formatMSG(config.getString("config-reload")));
                } else {
                    send(player, formatMSG(config.getString("no-permission")));
                }
                return true;
            } else if (hiddenCommands.contains(command.getName())) {
                return true;
            }
            boolean social = socialSpy(sender, command.getName(), args);
            if (!social)
                commandSpy(sender, command.getName(), args);
            return true;
        }

        Player player = (Player) sender;

        boolean commands = getData(player).commands;
        boolean signs = getData(player).signs;
        boolean anvils = getData(player).anvils;
        boolean books = getData(player).books;
        boolean social = getData(player).social;

        String toggleMessage = getString("toggle-message");
        assert toggleMessage != null;

        String toggleAllMessage = getString("toggle-all-message");
        assert toggleAllMessage != null;

        if (args.length == 0) {
            boolean activationState = getData(player).getActivationState();
            PlayerDataManager.getData(player).setActivationState(!activationState);
            notify(player, toggleMessage, activationState, "Big Brother", null);
        } else {

            String module = args[0].toLowerCase();

            switch (module) {
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
        return true;
    }

    private void notify(Player sender, String toggleMessage, boolean books, String label, String targetName) {
        targetName = targetName == null ? "" : targetName;
        String message = toggleMessage
                .replace("{state}", books ? "disabled" : "enabled")
                .replace("{module}", label)
                .replace("{player}", targetName);
        send(sender, formatMSG(message));
    }

    @Nullable
    private Player getPlayer(Player sender, String[] args) {
        Player target = sender.getServer().getPlayer(args[1]);
        if (target == null) {
            send(sender, formatMSG(getString("player-not-found")));
            return null;
        }
        return target;
    }

    private boolean perm(Player player, String permission) {
        if (!player.hasPermission(getString(permission))) {
            send(player, formatMSG(getString("no-permission")));
            return false;
        }
        return true;
    }
}
