package com.gitHub.copiousDogs.items;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

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
		
		if (par3EntityLivingBase instanceof Dog) {
			
			Dog dog = (Dog) par3EntityLivingBase;
			
			System.out.println(dog.isTamed() + "   " + dog.getOwnerName() + "   " + par2EntityPlayer.getEntityName());
			
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
						
						ByteArrayOutputStream bos = new ByteArrayOutputStream(36);
						DataOutputStream out = new DataOutputStream(bos);
						
						try {
							
							Random rand = dog.getRNG();
							
							ItemStack collar = new ItemStack(CopiousDogs.dogCollar.itemID, 1, DogCollar.getItemFromDye(color));
							EntityItem item = dog.entityDropItem(collar, 1F);
							item.motionY += rand.nextFloat() * .5F;
							item.motionX += (rand.nextFloat() - rand.nextFloat()) * .1F;
							item.motionZ += (rand.nextFloat() - rand.nextFloat()) * .1F;
							
							out.writeInt(0);
							out.writeFloat((float) item.posX);
							out.writeFloat((float) item.posY);
							out.writeFloat((float) item.posZ);
							out.writeFloat((float) item.motionX);
							out.writeFloat((float) item.motionY);
							out.writeFloat((float) item.motionZ);
							out.writeInt(collar.itemID);
							out.writeInt(1);
							out.writeInt(collar.getItemDamage());
							
						}catch(IOException e) {
							
							System.out.println("Unable to send packet.");
						}
					}
				}
				
				return true;
			}
		}
		
		return false;
	}
}
