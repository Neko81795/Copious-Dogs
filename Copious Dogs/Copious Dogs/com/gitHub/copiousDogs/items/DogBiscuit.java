package com.gitHub.copiousDogs.items;

import net.minecraft.item.Item;

import com.gitHub.copiousDogs.CopiousDogs;

public class DogBiscuit extends Item
{
	public DogBiscuit(int id)
	{
		super(id);
		setUnlocalizedName("DogBiscuit");
		setCreativeTab(CopiousDogs.tabCopiousDogs);
		setMaxStackSize(64);
	}
}