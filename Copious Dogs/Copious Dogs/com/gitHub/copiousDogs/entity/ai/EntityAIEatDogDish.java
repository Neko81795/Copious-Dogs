package com.gitHub.copiousDogs.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.tileentity.TileEntity;

import com.gitHub.copiousDogs.blocks.tileentity.TileEntityDogDish;
import com.gitHub.copiousDogs.entity.Dog;

public class EntityAIEatDogDish extends EntityAIBase {

	private Dog dog;
	private float radius;
	private TileEntityDogDish dogDish;
	private int eatingTimeLeft;
	
	public EntityAIEatDogDish(Dog dog, float radius) {
		
		this.dog = dog;
		this.radius = radius;
	}
	
	@Override
	public boolean shouldExecute() {
		
		if (dog.getHealth() < dog.getMaxHealth() && !dog.isSitting() && !dog.isEating()) {
			
			for (int x = 0; x < radius * 2; x++) {
				for (int y = 0; y < radius * 2; y++) {
					for (int z = 0; z < radius * 2; z++) {
						
						TileEntity entity = dog.worldObj.getBlockTileEntity((int) (dog.posX + x - radius), (int) (dog.posY + y - radius), (int) (dog.posZ + z - radius));
						
						if (entity instanceof TileEntityDogDish) {
							
							this.dogDish = (TileEntityDogDish) entity;
							
							if (dogDish.canEat(1)) {
								if (dog.getNavigator().tryMoveToXYZ(dogDish.xCoord, dogDish.yCoord, dogDish.zCoord, .5F)) {
								
									dog.getNavigator().setPath(dog.getNavigator().getPathToXYZ(dogDish.xCoord, dogDish.yCoord, dogDish.zCoord), .5);
									eatingTimeLeft = 30;
								
									System.out.println("Found dog dish at: " + dogDish.xCoord + " " + dogDish.yCoord + " " + dogDish.zCoord);
								
									return true;
								}
							}
						}
					}
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean continueExecuting() {
		
		
		return dog.getHealth() < dog.getMaxHealth() && !dog.isDead && eatingTimeLeft > 0 &&
				dog.worldObj.getBlockTileEntity(dogDish.xCoord, dogDish.yCoord, dogDish.zCoord) instanceof TileEntityDogDish;
	}
	
	@Override
	public void updateTask() {
		
		if (dog.getDistanceSq(dogDish.xCoord, dogDish.yCoord, dogDish.zCoord) < 3F) {
			
			dog.getNavigator().clearPathEntity();
			dog.setEating(true);
			dog.getLookHelper().setLookPosition(dogDish.xCoord, dogDish.yCoord - .8F, dogDish.zCoord, 10F, dog.getVerticalFaceSpeed());
			eatingTimeLeft--;
			
			if (eatingTimeLeft == 0) {
				
				dog.heal(dogDish.eat(dog.getMaxHealth() - dog.getHealth()));
				dog.setEating(false);
			}
		}
	}
	
	@Override
	public void resetTask() {
		
		dogDish = null;	
	}
}
