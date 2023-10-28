/* Copyright (c) 2023, JeriCraft-Plugins. Jericho Crosby <jericho.crosby227@gmail.com> */
package com.chalwk.util;

import com.chalwk.Items.BlazeGun;
import com.chalwk.Items.BlazeGunAmmo;
import com.chalwk.Items.ZapperGun;
import com.chalwk.Items.ZapperGunAmmo;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class Recipes {

    public static ItemStack BLAZE_GUN_AMMO;
    public static ItemStack ZAPPER_GUN_AMMO;

    @NotNull
    public static BlazeGun getBlazeGun(ItemGroup itemGroup) {
        SlimefunItemStack blazeGun = new SlimefunItemStack("BLAZE_GUN", Material.BLAZE_ROD, "&6Blaze Gun", "&bShoots burning magma cream");
        return new BlazeGun(itemGroup, blazeGun, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.BLAZE_POWDER), null, new ItemStack(Material.BLAZE_POWDER),
                        null, new ItemStack(Material.STICK), null,
                        new ItemStack(Material.BLAZE_POWDER), null, new ItemStack(Material.BLAZE_POWDER)}
        );
    }

    @NotNull
    public static BlazeGunAmmo getBlazeGunAmmo(ItemGroup itemGroup) {
        SlimefunItemStack blazeGunAmmo = new SlimefunItemStack("BLAZE_GUN_AMMO", Material.MAGMA_CREAM, "&6Blaze Gun Ammo", "&bUsed in with Blaze Gun");
        BLAZE_GUN_AMMO = blazeGunAmmo;
        return new BlazeGunAmmo(
                itemGroup, blazeGunAmmo, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, new ItemStack(Material.BLAZE_POWDER), null,
                        new ItemStack(Material.BLAZE_POWDER), new ItemStack(Material.MAGMA_CREAM), new ItemStack(Material.BLAZE_POWDER),
                        null, new ItemStack(Material.BLAZE_POWDER), null}
        );
    }

    @NotNull
    public static ZapperGun getZapperGun(ItemGroup itemGroup) {
        SlimefunItemStack zapperGun = new SlimefunItemStack("ZAPPER_GUN", Material.REDSTONE_TORCH, "&6Zapper Gun", "&bStrikes lightning on your enemies");
        return new ZapperGun(itemGroup, zapperGun, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        new ItemStack(Material.REDSTONE), null, new ItemStack(Material.REDSTONE),
                        null, new ItemStack(Material.STICK), null,
                        new ItemStack(Material.REDSTONE), null, new ItemStack(Material.REDSTONE)}
        );
    }

    @NotNull
    public static ZapperGunAmmo getZapperGunAmmo(ItemGroup itemGroup) {
        SlimefunItemStack zapperGunAmmo = new SlimefunItemStack("ZAPPER_GUN_AMMO", Material.REDSTONE, "&6Zapper Gun Ammo", "&bUsed with Zapper Gun");
        ZAPPER_GUN_AMMO = zapperGunAmmo;
        return new ZapperGunAmmo(itemGroup, zapperGunAmmo, RecipeType.ENHANCED_CRAFTING_TABLE,
                new ItemStack[]{
                        null, new ItemStack(Material.REDSTONE), null,
                        new ItemStack(Material.REDSTONE), new ItemStack(Material.REDSTONE_TORCH), new ItemStack(Material.REDSTONE),
                        null, new ItemStack(Material.REDSTONE), null}
        );
    }
}
