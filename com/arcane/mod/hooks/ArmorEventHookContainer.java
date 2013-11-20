package com.arcane.mod.hooks;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;

import org.lwjgl.input.Keyboard;

import com.arcane.mod.ArcaneEnchantments;

public class ArmorEventHookContainer 
{
	// Booleans, my boys
	boolean isJumpBoost = false;
	boolean isHeavyFooted = false;
	boolean isRegen = false;
	boolean isSprinter = false;
	boolean isSpeed = false;
	boolean isBound = false;

	// Inegers, ya idiots
	int heavyFootedAmount;
	int jumpBoostAmount;
	int regenAmount;
	int sprinterAmount;
	int speedAmount;
	int boundAmount;

	// Misc.
	int healingTimer;
	boolean respawned;
	ItemStack inventory[];

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

			jumpBoostAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.jumpBoost.effectId, stack_feet);

			if(jumpBoostAmount > 0)
			{
				isJumpBoost = true;
			}

			heavyFootedAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.jumpBoost.effectId, stack_feet);

			if(heavyFootedAmount > 0)
			{
				isHeavyFooted = true;
			}

			regenAmount = EnchantmentHelper.getMaxEnchantmentLevel(ArcaneEnchantments.armorRegen.effectId, stack_total);

			if(regenAmount > 0)
			{
				isRegen = true;
			}

			sprinterAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.sprinter.effectId, stack_legs);

			if(sprinterAmount > 0)
			{
				isSprinter = true;
			}

			speedAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.speedBoost.effectId, stack_feet);

			if(speedAmount > 0)
			{
				isSpeed = true;
			}

			boundAmount = EnchantmentHelper.getMaxEnchantmentLevel(ArcaneEnchantments.allBound.effectId, stack_total);

			if(boundAmount > 0 && !(player.worldObj.getWorldInfo().isHardcoreModeEnabled()))
			{
				isBound = true;
			}
		}
	}

	@ForgeSubscribe
	public void onLivingDeath(LivingDeathEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			inventory = new ItemStack[player.inventory.getSizeInventory()];;

			for(int i = 0; i < player.inventory.getSizeInventory(); i++)
			{
				int k = boundAmount;
				
				if(k == 4)
				{
					inventory[i] = player.inventory.getStackInSlot(i);
					continue;
				}
				if(k > 0 && player.worldObj.rand.nextInt(k + 1) > 0)
				{
					inventory[i] = player.inventory.getStackInSlot(i);
				}
			}
			for(int j = 0; j < inventory.length; j++)
			{
				if(inventory[j] != null)
				{
					player.inventory.setInventorySlotContents(j, null);
				}
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

			inventory = new ItemStack[player.inventory.getSizeInventory()];

			if(respawned && inventory != null && player.getHealth() > 0)
			{
				respawned = false;

				for(int k = 0; k < inventory.length; k++)
				{
					if(inventory[k] != null)
					{
						player.inventory.setInventorySlotContents(k, inventory[k]);
					}
				}
			}		
		}
	}
	
	@ForgeSubscribe
	public void afterDeathUpdate(LivingSpawnEvent event)
    {
        if(event.entityLiving instanceof EntityPlayer)
        {
        	EntityPlayer player = (EntityPlayer) event.entityLiving;
        	inventory = new ItemStack[player.inventory.getSizeInventory()];
        	
            respawned = true;
            
            if(inventory != null)
            {
                for(int i = 0; i < inventory.length; i++)
                {
                    player.inventory.setInventorySlotContents(i, inventory[i]);
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