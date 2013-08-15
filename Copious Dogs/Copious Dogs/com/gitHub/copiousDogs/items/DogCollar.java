package com.gitHub.copiousDogs.items;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.mobs.Dog;

public class DogCollar extends ItemColored {
	
	public DogCollar(int id) {
		
		super(id);
		setUnlocalizedName("dogCollar");
		setMaxStackSize(1);
	}
	
	@Override
	public boolean func_111207_a(ItemStack par1ItemStack,
			EntityPlayer par2EntityPlayer, EntityLivingBase par3EntityLivingBase) {
		
		if (par3EntityLivingBase instanceof Dog && !par2EntityPlayer.worldObj.isRemote) {
			
			Dog dog = (Dog) par3EntityLivingBase;
			
			if (dog.isTamed() && dog.getOwnerName().equalsIgnoreCase(par2EntityPlayer.getEntityName())) {
								
				par2EntityPlayer.swingItem();
				
				byte color = dog.getCollarColor();
				
				if (!dog.hasCollar()) {
					dog.setHasCollar(true);
				}
				dog.setCollarColor((byte) DogCollar.getDyeFromItem(par1ItemStack.getItemDamage()));
				
				if (!par2EntityPlayer.capabilities.isCreativeMode) {
					
					par1ItemStack.stackSize--;
					
					if (color > -1) {

						Random rand = dog.getRNG();
							
						ItemStack collar = new ItemStack(CopiousDogs.dogCollar.itemID, 1, DogCollar.getItemFromDye(color));
						EntityItem item = dog.entityDropItem(collar, 1F);
						item.motionY += rand.nextFloat() * .5F;
						item.motionX += (rand.nextFloat() - rand.nextFloat()) * .1F;
						item.motionZ += (rand.nextFloat() - rand.nextFloat()) * .1F;
						
						par2EntityPlayer.worldObj.spawnEntityInWorld(item);
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
}
