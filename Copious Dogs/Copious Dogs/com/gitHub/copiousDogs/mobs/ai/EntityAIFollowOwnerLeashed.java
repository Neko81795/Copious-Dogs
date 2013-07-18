package com.gitHub.copiousDogs.mobs.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIFollowOwner;

import com.gitHub.copiousDogs.mobs.Dog;

public class EntityAIFollowOwnerLeashed extends EntityAIFollowOwner {

	private Dog dog;
	
	public EntityAIFollowOwnerLeashed(Dog dog,
			double par2, float par4, float par5) {
		
		super(dog, par2, par4, par5);
		this.dog = dog;
	}
	
	@Override
	public boolean shouldExecute() {
		
		return super.shouldExecute() && dog.isLeashed();
	}
	
	@Override
	public boolean continueExecuting() {
		
		return super.continueExecuting() && dog.isLeashed();
	}
}
