package com.arcane.mod.hooks;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;

import com.arcane.mod.ArcaneEnchantments;

public class ArmorEventHookContainer 
{
	// Booleans, my boys
	boolean isJumpBoost = false;
	boolean isHeavyFooted = false;

	// Inegers, ya idiots
	int heavyFootedAmount;
	int jumpBoostAmount;

	@ForgeSubscribe
	public void livingUpdateEvent(LivingEvent.LivingUpdateEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack stack = player.inventory.armorItemInSlot(0);

			int jumpBoostAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.jumpBoost.effectId, stack);

			if(jumpBoostAmount > 0)
			{
				isJumpBoost = true;
			}

			int heavyFootedAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.jumpBoost.effectId, stack);

			if(heavyFootedAmount > 0)
			{
				isHeavyFooted = true;
			}
		}
	}
	
	@ForgeSubscribe
	public void applyEffects(LivingEvent.LivingUpdateEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			
		}
	}

	@ForgeSubscribe
	public void playerJumping(LivingJumpEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if(isJumpBoost = true)
			{
				if(jumpBoostAmount == 1)
				{
					player.motionY = 0.4655786; // Instead of adding to the player's jump height, it has to receive a new value
				} else if(jumpBoostAmount == 2)
				{
					player.motionY = 0.5725786;
				}
			} else
			{
				return;
			}
		}
	}
}