package com.gitHub.copiousDogs.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

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
	
	@Override
	public boolean onItemUse(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, World par3World, int par4, int par5,
			int par6, int par7, float par8, float par9, float par10) {
		
		par1ItemStack.stackSize--;
		
		return super.onItemUse(par1ItemStack, par2EntityPlayer, par3World, par4, par5,
				par6, par7, par8, par9, par10);
	}
}