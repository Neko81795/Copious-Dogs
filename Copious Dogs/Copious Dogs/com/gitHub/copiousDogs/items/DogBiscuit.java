package com.gitHub.copiousDogs.items;

public class DogBiscuit extends ItemCopiousDogs
{
	public DogBiscuit(int id)
	{
		super(id);
		setUnlocalizedName("DogBiscuit");
		setMaxStackSize(64);
	}
	
	public float getHealAmount()
	{
		return 3;
	}
}
