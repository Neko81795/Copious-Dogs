package com.gitHub.copiousDogs.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class DogBiscuit extends Item
{
	public DogBiscuit(int id)
	{
		super(id);
		setUnlocalizedName("DogBiscuit");
		setCreativeTab(CreativeTabs.tabMisc);
		setMaxStackSize(64);
	}
}