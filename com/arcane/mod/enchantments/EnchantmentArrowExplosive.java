package com.arcane.mod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentArrowKnockback;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentArrowExplosive extends Enchantment 
{
	public EnchantmentArrowExplosive(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.bow);
		this.setName("arrowSeeking");
	}

	public EnchantmentArrowExplosive(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Explosive";
		return enchantmentName + " " + StatCollector.translateToLocal("enchantment.level." + i);
	}

	@Override
	public int getMinEnchantability(int i)
	{
		return 5 + (12 + (i - 1));
	}

	@Override
	public int getMaxEnchantability(int i)
	{
		return getMinEnchantability(i) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 3;
	}
	
	@Override
	public boolean canApplyTogether(Enchantment enchantment)
    {
        /*if(enchantment instanceof EnchantmentArrowDamage)
        {
            return false;
        }
        if(enchantment instanceof EnchantmentArrowFire)
        {
            return false;
        }
        if(enchantment instanceof EnchantmentArrowHoly)
        {
            return false;
        }*/
        if(enchantment instanceof EnchantmentArrowKnockback)
        {
            return false;
        }
        /*if(enchantment instanceof EnchantmentArrowLightning)
        {
            return false;
        }
        if(enchantment instanceof EnchantmentArrowCrippleing)
        {
            return false;
        }*/ else
        {
            return super.canApplyTogether(enchantment);
        }
    }
}