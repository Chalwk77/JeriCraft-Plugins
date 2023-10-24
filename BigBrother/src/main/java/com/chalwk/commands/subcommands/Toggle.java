/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.commands.subcommands;

import com.chalwk.commands.SubCommand;
import com.chalwk.data.PlayerDataManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import static com.chalwk.BigBrother.getPluginConfig;
import static com.chalwk.Misc.formatMSG;
import static com.chalwk.Misc.send;
import static com.chalwk.data.PlayerDataManager.getData;

public class Toggle extends SubCommand {

    private static final FileConfiguration config = getPluginConfig();

    @Nullable
    private static String getString(String s) {
        return config.getString(s);
    }

    @Override
    public String getName() {
        return "toggle";
    }

    @Override
    public String getDescription() {
        return "Toggle Big Brother on or off (for yourself or other players)";
    }

    @Override
    public String getSyntax() {
        return "/bigbrother [commands/signs/anvils/books] [opt player name]";
    }

    @Override
    public String getPermission() {
        return getPluginConfig().getString("primary-permission");
    }

    @Override
    public void perform(Player sender, String[] args) {

        boolean commands = getData(sender).commands;
        boolean signs = getData(sender).signs;
        boolean anvils = getData(sender).anvils;
        boolean books = getData(sender).books;

        String toggleMessage = getString("toggle-message");
        String toggleAllMessage = getString("toggle-all-message");

        if (args.length == 0) {
            boolean activationState = getData(sender).getActivationState();
            PlayerDataManager.getData(sender).setActivationState(!activationState);
            notify(sender, toggleMessage, activationState, "Big Brother", null);
        } else {

            String module = args[0].toLowerCase();
            switch (module) {
                case "commands":
                    if (perm(sender, getString("command-spy.toggle-permission"))) {
                        if (args.length == 1) {
                            PlayerDataManager.getData(sender).setCommands(!commands);
                            notify(sender, toggleMessage, commands, "Command Spy", null);
                            return;
                        } else if (perm(sender, getString("command-spy.toggle-others-permission"))) {
                            Player target = getPlayer(sender, args);
                            if (target == null) return;
                            PlayerDataManager.getData(target).setCommands(!commands);
                            notify(sender, toggleAllMessage, commands, "Command Spy", target.getName());
                        }
                    }
                    break;
                case "signs":
                    if (perm(sender, getString("sign-spy.toggle-permission"))) {
                        if (args.length == 1) {
                            PlayerDataManager.getData(sender).setSigns(!signs);
                            notify(sender, toggleMessage, signs, "Sign Spy", null);
                            return;
                        } else if (perm(sender, getString("sign-spy.toggle-others-permission"))) {
                            Player target = getPlayer(sender, args);
                            if (target == null) return;
                            PlayerDataManager.getData(target).setSigns(!signs);
                            notify(sender, toggleAllMessage, signs, "Sign Spy", target.getName());
                        }
                    }
                    break;
                case "anvils":
                    if (perm(sender, getString("anvil-spy.toggle-permission"))) {
                        if (args.length == 1) {
                            PlayerDataManager.getData(sender).setAnvils(!anvils);
                            notify(sender, toggleMessage, anvils, "Anvil Spy", null);
                            return;
                        } else if (perm(sender, getString("anvil-spy.toggle-others-permission"))) {
                            Player target = getPlayer(sender, args);
                            if (target == null) return;
                            PlayerDataManager.getData(target).setAnvils(!anvils);
                            notify(sender, toggleAllMessage, anvils, "Anvil Spy", target.getName());
                        }
                    }
                    break;
                case "books":
                    if (perm(sender, getString("book-spy.toggle-permission"))) {
                        if (args.length == 1) {
                            PlayerDataManager.getData(sender).setBooks(!books);
                            notify(sender, toggleMessage, books, "Book Spy", null);
                            return;
                        } else if (perm(sender, getString("book-spy.toggle-others-permission"))) {
                            Player target = getPlayer(sender, args);
                            if (target == null) return;
                            PlayerDataManager.getData(target).setBooks(!books);
                            notify(sender, toggleAllMessage, books, "Book Spy", target.getName());
                        }
                    }
                    break;
                default:
                    send(sender, formatMSG(getString("invalid-module")));
            }
        }
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
