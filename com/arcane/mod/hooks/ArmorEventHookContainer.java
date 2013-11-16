package com.arcane.mod.hooks;

import org.lwjgl.input.Keyboard;

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
	boolean isRegen = false;
	boolean isSprinter = false;
	boolean isSpeed = false;

	// Inegers, ya idiots
	int heavyFootedAmount;
	int jumpBoostAmount;
	int regenAmount;
	int sprinterAmount;
	int speedAmount;

	// Misc.
	int healingTimer;

	@ForgeSubscribe
	public void livingUpdateEvent(LivingEvent.LivingUpdateEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack stack_feet = player.inventory.armorItemInSlot(0);
			ItemStack stack_legs = player.inventory.armorItemInSlot(1);
			ItemStack stack_torso = player.inventory.armorItemInSlot(2);
			ItemStack stack_head = player.inventory.armorItemInSlot(3);
			ItemStack[] stack_total = player.inventory.armorInventory;

			this.jumpBoostAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.jumpBoost.effectId, stack_feet);

			if(jumpBoostAmount > 0)
			{
				isJumpBoost = true;
			}

			this.heavyFootedAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.jumpBoost.effectId, stack_feet);

			if(heavyFootedAmount > 0)
			{
				isHeavyFooted = true;
			}

			this.regenAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.armorRegen.effectId, stack_feet);

			if(regenAmount > 0)
			{
				isRegen = true;
			}

			this.sprinterAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.sprinter.effectId, stack_legs);

			if(sprinterAmount > 0)
			{
				isSprinter = true;
			}

			this.speedAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.speedBoost.effectId, stack_feet);

			if(speedAmount > 0)
			{
				isSpeed = true;
			}
		}
	}

	@ForgeSubscribe
	public void applyEffects(LivingEvent.LivingUpdateEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;

			if(isRegen = true)
			{
				int i = 20 * ((this.regenAmount - 1) * 3); 
				int j = 440 - i;
				int k = healingTimer % j;

				if(k == 0 && player.getHealth() < player.getMaxHealth())
				{
					player.heal(1);
				}
				if(healingTimer > 44000)
				{
					healingTimer = 0;
				}

				healingTimer++;
			}
			if(isSprinter = true && player.isSprinting() == true)
			{
				player.getFoodStats().addExhaustion(-0.2F * (float) sprinterAmount); // Halves the amount of exhaustion when sprinting
			}
			if(isSpeed = true)
			{
				if(!player.isSneaking() && player.onGround)
				{
					if(Keyboard.isKeyDown(87) || Keyboard.isKeyDown(65) || Keyboard.isKeyDown(83) || Keyboard.isKeyDown(68))
					{
						double amount;
						System.out.println("Woof");

						if(speedAmount == 1)
						{
							amount = 1.1;
						} else if(speedAmount == 2)
						{
							amount = 1.25;
						} else
						{
							amount = 1.45;
						}

						player.motionX *= amount;				
						player.motionZ *= amount;
					}
				}
			}
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