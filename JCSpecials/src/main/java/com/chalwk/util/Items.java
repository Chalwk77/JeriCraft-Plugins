/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static com.chalwk.JCSpecials.cfg;

public class Items {

    /* RECIPES */
    public static final ItemStack[] blazeGunRecipe = {
            null, null, SlimefunItems.FIRE_RUNE,
            null, new ItemStack(Material.STICK), null,
            SlimefunItems.REINFORCED_ALLOY_INGOT, null, null
    };
    public static final ItemStack[] blazeGunAmmoRecipe = {
            null, new ItemStack(Material.BLAZE_POWDER), null,
            new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.MAGMA_CREAM), new ItemStack(Material.BLAZE_POWDER),
            null, new ItemStack(Material.BLAZE_POWDER), null
    };
    public static final ItemStack[] zapperGunRecipe = {
            null, null, SlimefunItems.LIGHTNING_RUNE,
            null, new ItemStack(Material.LIGHTNING_ROD), null,
            SlimefunItems.REDSTONE_ALLOY, null, null
    };
    public static final ItemStack[] zapperGunAmmoRecipe = {
            new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR),
            new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR),
            new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR)
    };
    public static final ItemStack[] smeltersShovelRecipe = {
            null, SlimefunItems.LAVA_CRYSTAL, null,
            null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
            null, SlimefunItems.REINFORCED_ALLOY_INGOT, null
    };
    public static final ItemStack[] spongeRecipe = {
            new ItemStack(SlimefunItems.CLOTH), new ItemStack(SlimefunItems.CLOTH), new ItemStack(SlimefunItems.CLOTH),
            new ItemStack(SlimefunItems.CLOTH), new ItemStack(SlimefunItems.CLOTH), new ItemStack(SlimefunItems.CLOTH),
            new ItemStack(SlimefunItems.CLOTH), new ItemStack(SlimefunItems.CLOTH), new ItemStack(SlimefunItems.CLOTH)
    };
    public static final ItemStack[] witherGunRecipe = {
            null, null, SlimefunItems.BLISTERING_INGOT_3,
            null, new ItemStack(Material.STICK), null,
            SlimefunItems.REINFORCED_ALLOY_INGOT, null, null
    };
    public static final ItemStack[] witherGunAmmoRecipe = {
            null, new ItemStack(SlimefunItems.MAGIC_LUMP_3), null,
            new ItemStack(SlimefunItems.MAGIC_LUMP_3), new ItemStack(SlimefunItems.NECROTIC_SKULL), new ItemStack(SlimefunItems.MAGIC_LUMP_3),
            null, new ItemStack(SlimefunItems.MAGIC_LUMP_3), null
    };

    /* WEAPONS */
    public static SlimefunItemStack BLAZE_GUN = new SlimefunItemStack(
            "BLAZE_GUN",
            Material.BLAZE_ROD,
            cfg.getString("messages.items.blaze-gun.name"),
            cfg.getString("messages.items.blaze-gun.description")
    );
    public static SlimefunItemStack BLAZE_GUN_AMMO = new SlimefunItemStack(
            "BLAZE_GUN_AMMO",
            Material.MAGMA_CREAM,
            cfg.getString("messages.items.blaze-gun-ammo.name"),
            cfg.getString("messages.items.blaze-gun-ammo.description")
    );
    public static SlimefunItemStack ZAPPER_GUN = new SlimefunItemStack(
            "ZAPPER_GUN",
            Material.LIGHTNING_ROD,
            cfg.getString("messages.items.zapper-gun.name"),
            cfg.getString("messages.items.zapper-gun.description")
    );
    public static SlimefunItemStack ZAPPER_GUN_AMMO = new SlimefunItemStack(
            "ZAPPER_GUN_AMMO",
            Material.NETHER_STAR,
            cfg.getString("messages.items.zapper-gun-ammo.name"),
            cfg.getString("messages.items.zapper-gun-ammo.description")
    );
    public static SlimefunItemStack WITHER_GUN = new SlimefunItemStack(
            "WITHER_GUN",
            Material.BLAZE_ROD,
            cfg.getString("messages.items.wither-gun.name"),
            cfg.getString("messages.items.wither-gun.description")
    );
    public static SlimefunItemStack WITHER_GUN_AMMO = new SlimefunItemStack(
            "WITHER_GUN_AMMO",
            Material.WITHER_SKELETON_SKULL,
            cfg.getString("messages.items.wither-gun-ammo.name"),
            cfg.getString("messages.items.wither-gun-ammo.description")
    );

    /* TOOLS */
    public static SlimefunItemStack SMELTERS_SHOVEL = new SlimefunItemStack(
            "SMELTERS_SHOVEL",
            Material.NETHERITE_SHOVEL,
            cfg.getString("messages.items.smelters-shovel.name"),
            cfg.getString("messages.items.smelters-shovel.description")
    );

    /* MISC */
    public static SlimefunItemStack SPONGE = new SlimefunItemStack(
            "SPONGE",
            Material.SPONGE,
            cfg.getString("messages.items.sponge.name"),
            cfg.getString("messages.items.sponge.description")
    );
}
