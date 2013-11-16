package com.arcane.mod.hooks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import com.arcane.mod.ArcaneEnchantments;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ToolEventHookContainer 
{
	// Booleans, my boys
	boolean isVampire;
	boolean isFlameTouched;

	//Integers, ya idiot
	int vampireAmount;
	int flameTouchAmount;

	EntityLiving living;

	@ForgeSubscribe
	public void onEntityAttack(LivingHurtEvent event)
	{

	}

	@ForgeSubscribe
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase livingBase = event.entityLiving;
		livingBase = living;
	}
}