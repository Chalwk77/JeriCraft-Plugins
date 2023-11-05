/* Copyright (c) 2023, Perks. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.commands.subcommands;

import com.chalwk.commands.SubCommand;
import org.bukkit.entity.Player;

import static com.chalwk.Misc.send;
import static com.chalwk.Perks.getInstance;

public class Reload extends SubCommand {

    @Override
    public String getName() {
        return "reload";
    }

    @Override
    public String getSyntax() {
        return "&cInvalid Syntax. Usage: &b/perks reload";
    }

    @Override
    public String getPermission() {
        return "perks.reload";
    }

    @Override
    public void perform(Player sender, String[] args) {
        if (args.length == 1) {
            getInstance().reloadConfig();
            send(sender, "&8=============================");
            send(sender, "&3Perks reloaded!");
            send(sender, "&8=============================");
        } else {
            send(sender, "&8=============================");
            send(sender, getSyntax());
            send(sender, "&8=============================");
        }
    }
}
