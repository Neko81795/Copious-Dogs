package com.gitHub.copiousDogs.mobs.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.mobs.Dog;

public class EntityAIBegBiscuit extends EntityAIBase {

	private Dog dog;
	private EntityPlayer player;
	private World worldObject;
	private float minPlayerDistance;
	private int ticksLeft;
	
	public EntityAIBegBiscuit(Dog dog, float minDist) {
		
		this.dog = dog;
		this.worldObject = dog.worldObj;
		this.minPlayerDistance = minDist;
	}
	
	@Override
	public boolean shouldExecute() {
		
		this.player = worldObject.getClosestPlayerToEntity(dog, (double)minPlayerDistance);
		return player == null ? false: isPlayerHoldingBiscuit(player) && !dog.isTamed();
	}

	@Override
	public boolean continueExecuting() {
		
		return !player.isEntityAlive() ? false : (dog.getDistanceSqToEntity(player)) > (double)(Math.pow(minPlayerDistance, 2)) ? false: ticksLeft > 0 && isPlayerHoldingBiscuit(player) && !dog.isTamed();
	}
	
	@Override
	public void startExecuting() {
		
		dog.setBegging(true);
		ticksLeft = 40 + dog.getRNG().nextInt(40);
	}
	
	@Override
	public void resetTask() {
		
		dog.setBegging(false);
		this.player = null;
	}
	
	@Override
	public void updateTask() {
		
		this.dog.getLookHelper().setLookPosition(this.player.posX, this.player.posY + (double)this.player.getEyeHeight(), this.player.posZ, 10.0F, (float)this.dog.getVerticalFaceSpeed());
        --this.ticksLeft;
	}
	
	private boolean isPlayerHoldingBiscuit(EntityPlayer player) {
		
		ItemStack stack = player.getCurrentEquippedItem();
		return stack == null ? false:stack.itemID == CopiousDogs.dogBiscuit.itemID;
	}
}
