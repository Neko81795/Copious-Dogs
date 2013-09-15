package com.gitHub.copiousDogs.blocks.tileentity;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;

public class TileEntityDogDish extends TileEntity {

	/**
	 * The current amount of food in this dog dish,
	 * beef and porkchop adding 10 and
	 * chicken adding 5. This allows the dogs
	 * to eat the exact amount of food they need until they're
	 * full.
	 */
	private float foodLevel = 0;
	/**
	 * The maximum amount of food this dog dish can store.
	 */
	private float maxFoodLevel = 30;
	
	public TileEntityDogDish() {
		
		foodLevel = 0;
	}
	
	public float getFoodLevel() {
		
		return foodLevel;
	}
	
	public void setFoodLevel(float par0) {
		
		foodLevel = par0;
	}
	
	public float getMaxFoodLevel() {
		
		return maxFoodLevel;
	}
	
	/**
	 * Used to get the food modification
	 * value of the item id passed in
	 * as a parameter.
	 * 
	 * @param id
	 * @returns the value of how much this item affects foodLevel
	 */
	private int getFoodModValue(int id) {
		
		if (id == Item.beefRaw.itemID || id == Item.porkRaw.itemID) return 10;
		else if (id == Item.chickenRaw.itemID) return 5;
		else return 0;
	}
	
	public void sendChangeToServer() {
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		
		try {
			
			outputStream.writeInt(1);
			
			outputStream.writeInt(xCoord);
			outputStream.writeInt(yCoord);
			outputStream.writeInt(zCoord);
			
			outputStream.writeFloat(foodLevel);
		}catch(Exception ex) {
			
			ex.printStackTrace();
		}
		
		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "CopiousDogs#1";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		PacketDispatcher.sendPacketToAllPlayers(packet);
	}
	
	/**
	 * Tries to add the given item
	 * to the dog dish. Return true
	 * if the item got added.
	 * 
	 * @param stack
	 * @returns True if suceeded, false otherwise.
	 */
	public boolean addFood(ItemStack stack) {
		
		System.out.println(foodLevel + "    " + FMLCommonHandler.instance().getEffectiveSide());
		
		if (stack == null) return false;
		
		int foodAmount = getFoodModValue(stack.itemID);
		
		if (foodAmount != 0) {
			
			if (foodLevel >= maxFoodLevel) {
				
				return false;
			}
			else if (foodLevel + foodAmount > maxFoodLevel) {
				
				foodLevel = maxFoodLevel;
				sendChangeToServer();
				return true;
			}
			else {
				
				foodLevel += foodAmount;
				sendChangeToServer();
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * 
	 * Tries to eat (subtract) the given amount
	 * of food from the bowl. Returns the
	 * amount of food removed from the dog dish. -1
	 * if none.
	 * 
	 * @param amount
	 * @returns The amount of food removed
	 */
	public float eat(float amount) {
		
		if (foodLevel != 0) {
			
			if (foodLevel - amount < 0) {
				
				foodLevel = 0;
				System.out.println(foodLevel);
				sendChangeToServer();
				return foodLevel;
			}
			else {
				
				foodLevel -= amount;
				System.out.println(foodLevel);
				sendChangeToServer();
				return amount;
			}
		}
		
		return -1;
	}
	
	public boolean canEat(float amount) {
		
		return foodLevel - amount >= 0;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		
		super.writeToNBT(par1nbtTagCompound);
		
		par1nbtTagCompound.setFloat("FoodLevel", foodLevel);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		
		super.readFromNBT(par1nbtTagCompound);
		
		foodLevel = par1nbtTagCompound.getFloat("FoodLevel");
	}
}
