package com.arcane.mod.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.util.StatCollector;

public class EnchantmentSprinter extends Enchantment
{
	public EnchantmentSprinter(int id, int rarity)
	{
		super(id, rarity, EnumEnchantmentType.armor_legs);
		this.setName("sprinter");
	}

	public EnchantmentSprinter(int id, int rarity, EnumEnchantmentType type)
	{
		super(id, rarity, type);
	}

	@Override
	public String getTranslatedName(int i)
	{
		String enchantmentName = "Sprinter";
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
		return this.getMinEnchantability(i) + 20;
	}

	@Override
	public int getMaxLevel()
	{
		return 1;
	}
}