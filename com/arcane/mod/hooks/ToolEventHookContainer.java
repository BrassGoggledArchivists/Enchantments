package com.arcane.mod.hooks;

import net.minecraft.block.Block;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;

import com.arcane.mod.ArcaneEnchantments;

public class ToolEventHookContainer 
{
	// Booleans, my boys
	boolean isVampire;
	boolean isFlameTouched;
	
	//Integers, ya idiot
	int vampireAmount;
	int flameTouchAmount;
	
	public EntityLiving living;
	
	@ForgeSubscribe
	public void onEntityAttack(LivingHurtEvent event)
	{
		
	}
	
	@ForgeSubscribe
	public void harvestCheck(PlayerEvent.HarvestCheck event)
	{
		EntityPlayer player = event.entityPlayer;
		ItemStack stack = player.inventory.getCurrentItem();
		flameTouchAmount = EnchantmentHelper.getEnchantmentLevel(ArcaneEnchantments.flameTouch.effectId, stack);
		
		if(flameTouchAmount > 0 && event.success)
		{
			int i = 0;
			int j = 0;
			int k = 0;
			int l = 0;
			this.dropBlocks(player.worldObj, i, j, k, l);
		}
	}
	
	@ForgeSubscribe
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		EntityLivingBase livingBase = event.entityLiving;
		livingBase = living;
	}
	
	public void dropBlocks(World world, int i, int j, int k, int l)
    {
        for (int i1 = -3; i1 < 3; i1++)
        {
            for (int j1 = 0; j1 < 2; j1++)
            {
                for (int k1 = -3; k1 < 3; k1++)
                {
                    int l1 = i + i1;
                    int i2 = j + j1;
                    int j2 = k + k1;
                    int k2 = world.getBlockId(l1, i2, j2);
                    int l2 = world.getBlockMetadata(l1, i2, j2);
                    if (k2 != l)
                    {
                        continue;
                    }
                    Block block = Block.blocksList[k2];
                    if (block != null && world.setBlockToAir(l1, i2, j2))
                    {
                        block.dropBlockAsItem(world, l1, i2, j2, l2, 0);
                    }
                }
            }
        }
    }
}