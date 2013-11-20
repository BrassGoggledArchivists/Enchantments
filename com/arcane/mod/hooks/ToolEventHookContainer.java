package com.arcane.mod.hooks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.event.world.BlockEvent;

import com.arcane.mod.ArcaneEnchantments;

public class ToolEventHookContainer 
{
	// Booleans, my boys
	boolean isVampire = false;
	boolean isFlameTouched = false;

	// Integers, ya idiot
	int vampireAmount;
	int flameTouchAmount;
	
	// Misc. variables
	int i;
	Random random = new Random();

	@ForgeSubscribe
	public void onEntityAttack(AttackEntityEvent event)
	{
		if(event.entityLiving instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer) event.entityLiving;
			ItemStack stack = player.inventory.getCurrentItem();
			this.vampireAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.vampirism.effectId, stack);

			if(vampireAmount > 0)
			{
				isVampire = true;
				
				if(random.nextInt(3) == 0)
				{
					if(((EntityPlayer) event.entityLiving).getFoodStats().getFoodLevel() <= 19)
					{
						((EntityPlayer) event.entityLiving).getFoodStats().setFoodLevel(((EntityPlayer) event.entityLiving).getFoodStats().getFoodLevel() + 1);
					} else
					{
						((EntityPlayer) event.entityLiving).heal(1);
					}
					
					i++;
				}
			}
		}
	}
	
	@ForgeSubscribe
	public void onHarvestBlocks(BlockEvent.HarvestDropsEvent event)
	{
		EntityPlayer player = event.harvester;
		ItemStack heldItem = player.inventory.getCurrentItem();
		Block block = event.block;
		
		flameTouchAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.flameTouch.effectId, heldItem);
		
		if(heldItem == null)
		{
			return;
		} else if(flameTouchAmount > 0)
		{
			isFlameTouched = true;
		}
			
		if(isFlameTouched = true)
		{
			if(player.worldObj.rand.nextInt(2) == 0)
			{
				// So I was going to use FurnaceRecipes, but then I decided against it because this way gives me more flexibility
				if(block == Block.oreIron)
				{
					event.drops.add(new ItemStack(Item.ingotIron, 1));
					event.drops.remove(15);
				}
				if(block == Block.oreGold)
				{
					event.drops.add(new ItemStack(Item.ingotGold, 1));
					event.drops.remove(Block.oreGold);
				}
			}
		}
	}
}