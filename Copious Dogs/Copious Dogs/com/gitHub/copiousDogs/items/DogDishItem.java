package com.gitHub.copiousDogs.items;

import net.minecraft.item.ItemBlock;

public class DogDishItem extends ItemBlock
{
	private final static String[] subNames = 
	{
		"white", "orange",  "magenta", "lightBlue", "yellow", "lightGreen",
		"pink", "darkGrey", "lightGrey", "cyan", "purple", "blue", "brown",
		"green", "red", "black"
	};
	
	public DogDishItem(int id)
	{
		super(id);
		setHasSubtypes(true);
	}
	
	@Override
	public int getMetadata(int damageValue)
	{
		return damageValue;
	}
}