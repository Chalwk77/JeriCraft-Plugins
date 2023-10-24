/* Copyright (c) 2023, BigBrother. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.data;

import org.bukkit.entity.Player;


public class PlayerData {

    private final Player player;
    public boolean activationState;
    public boolean commands;
    public boolean signs;
    public boolean anvils;
    public boolean books;
    public boolean social;

    /***
     *  PlayerData constructor.
     * @param player The player to store data for.
     *               Stores big-brother activation state (parent).
     *               Stores command-spy activation state (child).
     *               Stores sign-spy activation state (child).
     *               Stores anvil-spy activation state (child).
     *               Stores book-spy activation state (child).
     *
     */
    public PlayerData(Player player) {
        this.player = player;
        this.activationState = false;
        this.commands = true;
        this.anvils = true;
        this.books = true;
        this.signs = true;
        this.social = true;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean getActivationState() {
        return activationState;
    }

    public void setActivationState(boolean activationState) {
        this.activationState = activationState;
    }

    public void setCommands(boolean commands) {
        this.commands = commands;
    }

    public void setAnvils(boolean anvils) {
        this.anvils = anvils;
    }

    public void setBooks(boolean books) {
        this.books = books;
    }

    public void setSigns(boolean signs) {
        this.signs = signs;
    }

    public void setSocial(boolean social) {
        this.social = social;
    }
}
