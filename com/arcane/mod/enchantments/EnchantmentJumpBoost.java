package com.arcane.mod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentJumpBoost extends Enchantment
{
	public EnchantmentJumpBoost(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.armor_feet);
		this.setName("jumpBoost");
	}

	public EnchantmentJumpBoost(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Jump Boost";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 20;
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return i + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 2;
	}

	@Override 
	public boolean canApplyTogether(Enchantment enchantment)
	{
		//(if(enchantment instanceof EnchantmentLeadFooted)
		//{
		//    return false;
		//} else
		//{
		return super.canApplyTogether(enchantment);
		//}
	}
}