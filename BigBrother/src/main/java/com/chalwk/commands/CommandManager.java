/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.commands;

import com.chalwk.commands.subcommands.Toggle;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import static com.chalwk.Misc.Log;
import static com.chalwk.Misc.send;

public class CommandManager implements CommandExecutor, TabCompleter {

    private final ArrayList<SubCommand> subcommands = new ArrayList<>();

    public CommandManager() {
        subcommands.add(new Toggle());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            Log("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        if (args.length > 0) {
            for (int i = 0; i < getSubCommands().size(); i++) {
                String subCommand = getSubCommands().get(i).getName();
                String permissionNode = getSubCommands().get(i).getPermission();
                if (args[0].equalsIgnoreCase(subCommand)) {
                    if (hasPermission(player, permissionNode)) {
                        getSubCommands().get(i).perform(player, args);
                    }
                    return true;
                }
            }
        }

        com.chalwk.Spy.CommandSpy.Spy(player, command, args);

        return true;
    }

    public ArrayList<SubCommand> getSubCommands() {
        return subcommands;
    }

    public boolean hasPermission(Player player, String permission) {
        if (player.hasPermission(permission)) {
            return true;
        } else {
            send(player, "&cYou do not have permission to use this command!");
            return false;
        }
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        ArrayList<String> tab = new ArrayList<>();
        if (args.length == 1) {
            for (SubCommand subCommand : getSubCommands()) {
                tab.add(subCommand.getName());
            }
            return tab;
        }
        return tab;
    }
}
