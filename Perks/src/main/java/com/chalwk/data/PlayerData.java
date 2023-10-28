/* Copyright (c) 2023, Perks. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.data;

import com.chalwk.Misc;
import com.chalwk.gui.CustomGUI;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.Map;

public class PlayerData {

    private final Player player;
    private ItemStack head;
    private int moneySpent;
    private CustomGUI openGUI;

    public PlayerData(Player player) {
        this.moneySpent = 0;
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public int getMoneySpent() {
        return moneySpent;
    }

    public void setMoneySpent(int i) {
        this.moneySpent = i;
    }

    public CustomGUI getOpenGUI() {
        return openGUI;
    }

    public void setOpenGUI(CustomGUI openGUI) {
        this.openGUI = openGUI;
    }

    public void setHead(List<String> lore, String displayName) {
        ItemStack item = new ItemStack(Material.valueOf("PLAYER_HEAD"));
        SkullMeta meta = (SkullMeta) item.getItemMeta();
        meta.setOwner(this.player.getName());
        meta.setDisplayName(Misc.formatMSG(displayName));
        lore.replaceAll(Misc::formatMSG);
        meta.setLore(lore);
        item.setItemMeta(meta);
        this.head = item;
    }

    public ItemStack getHead() {
        return head;
    }

    public void updateMoneySpent(Player sender, Map<?, ?> data) {
        Map<?, ?> perks = (Map<?, ?>) data.get("perks");
        for (Map.Entry<?, ?> entry : perks.entrySet()) {
            String node = (String) entry.getKey();
            if (sender.hasPermission(node)) {
                Map<?, ?> perkData = (Map<?, ?>) entry.getValue();
                int price = (int) perkData.get("price");
                this.moneySpent = this.moneySpent + price;
            }
        }
    }
}
