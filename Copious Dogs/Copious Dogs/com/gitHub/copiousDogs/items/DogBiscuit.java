package com.gitHub.copiousDogs.items;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

import com.gitHub.copiousDogs.mobs.Dog;

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
	
	@Override
	public boolean func_111207_a(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase) {
		
		if (par3EntityLivingBase instanceof Dog && !par2EntityPlayer.worldObj.isRemote) {
			
			Dog dog = (Dog) par3EntityLivingBase;
				
			if (!dog.isTamed()) {
				
				par2EntityPlayer.swingItem();
				
				if (!par2EntityPlayer.capabilities.isCreativeMode) 
				{
					dog.setTameValue((byte)(dog.getTameValue() + + 3 + dog.getRNG().nextInt(8)));
						
					dog.tryToTame(par2EntityPlayer);
					par1ItemStack.stackSize--;
				}
				else
					dog.setTameValue((byte)11);
					dog.tryToTame(par2EntityPlayer);
						
				return true;
			}
		}
		
		return false;
	}
}
