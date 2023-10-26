/* Copyright (c) 2023, NightVision. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.data;

import org.bukkit.entity.Player;


public class PlayerData {

    private final Player player;
    public boolean activationState;

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
}
