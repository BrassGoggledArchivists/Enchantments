package com.arcane.mod.common;

import com.arcane.mod.ArcaneEnchantments;

import cpw.mods.fml.common.registry.TickRegistry;
import cpw.mods.fml.relauncher.Side;

public class CommonProxy 
{
	public void load() 
	{
		TickRegistry.registerTickHandler(new UpdateHandler(ArcaneEnchantments.getModName(), ArcaneEnchantments.getVersion()), Side.SERVER);
	}
}