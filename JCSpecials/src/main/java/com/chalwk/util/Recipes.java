/* Copyright (c) 2023, JCSpecials. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import com.chalwk.Items.*;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class Recipes {

    private static final ItemStack[] blazeGunRecipe = {
            null, null, SlimefunItems.FIRE_RUNE,
            null, new ItemStack(Material.STICK), null,
            SlimefunItems.REINFORCED_ALLOY_INGOT, null, null
    };
    private static final ItemStack[] blazeGunAmmoRecipe = {
            null, new ItemStack(Material.BLAZE_POWDER), null,
            new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.MAGMA_CREAM), new ItemStack(Material.BLAZE_POWDER),
            null, new ItemStack(Material.BLAZE_POWDER), null
    };
    private static final ItemStack[] zapperGunRecipe = {
            null, null, SlimefunItems.LIGHTNING_RUNE,
            null, new ItemStack(Material.LIGHTNING_ROD), null,
            SlimefunItems.REDSTONE_ALLOY, null, null
    };
    private static final ItemStack[] zapperGunAmmoRecipe = {
            new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR),
            new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR),
            new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR), new ItemStack(Material.NETHER_STAR)
    };

    private static final ItemStack[] smeltersShovelRecipe = {
            null, SlimefunItems.LAVA_CRYSTAL, null,
            null, SlimefunItems.REINFORCED_ALLOY_INGOT, null,
            null, SlimefunItems.REINFORCED_ALLOY_INGOT, null
    };

    public static ItemStack BLAZE_GUN_AMMO;
    public static ItemStack ZAPPER_GUN_AMMO;

    public static void getBlazeGun(ItemGroup itemGroup) {
        SlimefunItemStack blazeGun = new SlimefunItemStack("BLAZE_GUN", Material.BLAZE_ROD, "&6Blaze Gun", "&bShoots burning magma cream");
        new BlazeGun(itemGroup, blazeGun, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunRecipe);
    }

    public static void getBlazeGunAmmo(ItemGroup itemGroup) {
        SlimefunItemStack blazeGunAmmo = new SlimefunItemStack("BLAZE_GUN_AMMO", Material.MAGMA_CREAM, "&6Blaze Gun Ammo", "&bUsed with Blaze Gun");
        BLAZE_GUN_AMMO = blazeGunAmmo;
        new BlazeGunAmmo(itemGroup, blazeGunAmmo, RecipeType.ENHANCED_CRAFTING_TABLE, blazeGunAmmoRecipe);
    }

    public static void getZapperGun(ItemGroup itemGroup) {
        SlimefunItemStack zapperGun = new SlimefunItemStack("ZAPPER_GUN", Material.LIGHTNING_ROD, "&6Zapper Gun", "&bStrikes lightning on your enemies");
        new ZapperGun(itemGroup, zapperGun, RecipeType.ENHANCED_CRAFTING_TABLE, zapperGunRecipe);
    }

    public static void getZapperGunAmmo(ItemGroup itemGroup) {
        SlimefunItemStack zapperGunAmmo = new SlimefunItemStack("ZAPPER_GUN_AMMO", Material.NETHER_STAR, "&6Zapper Gun Ammo", "&bUsed with Zapper Gun");
        ZAPPER_GUN_AMMO = zapperGunAmmo;
        new ZapperGunAmmo(itemGroup, zapperGunAmmo, RecipeType.ENHANCED_CRAFTING_TABLE, zapperGunAmmoRecipe);
    }

    public static void getSmeltersShovel(ItemGroup itemGroup) {
        SlimefunItemStack smeltersShovel = new SlimefunItemStack("SMELTERS_SHOVEL", Material.NETHERITE_SHOVEL, "&6Smelters Shovel", "&bSmelts sand/red sand into glass");
        new SmeltersShovel(itemGroup, smeltersShovel, RecipeType.ENHANCED_CRAFTING_TABLE, smeltersShovelRecipe);
    }
}