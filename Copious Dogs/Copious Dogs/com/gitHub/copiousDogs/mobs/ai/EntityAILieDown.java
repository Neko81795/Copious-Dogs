package com.gitHub.copiousDogs.mobs.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

import com.gitHub.copiousDogs.mobs.Dog;

public class EntityAILieDown extends EntityAIBase {

	private Dog dog;
	
	public EntityAILieDown(Dog dog) {
		
		this.dog = dog;
	}
	
	@Override
	public boolean shouldExecute() {

		if (!this.dog.isTamed())
        {
            return false;
        }
        else if (this.dog.isInWater())
        {
            return false;
        }
        else if (!this.dog.onGround)
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.dog.func_130012_q();
            return entitylivingbase == null ? true : (this.dog.getDistanceSqToEntity(entitylivingbase) < 144.0D && entitylivingbase.getAITarget() != null ? false : dog.isSitting());
        }
    }
	
	public void startExecuting()
    {
        this.dog.getNavigator().clearPathEntity();
        this.dog.setSitting(true);
    }
	
	@Override
	public boolean continueExecuting() {
		
		if (!this.dog.isTamed())
        {
            return false;
        }
        else if (this.dog.isInWater())
        {
            return false;
        }
        else if (!this.dog.onGround)
        {
            return false;
        }
        else
        {
            EntityLivingBase entitylivingbase = this.dog.func_130012_q();
            return entitylivingbase == null ? true : (this.dog.getDistanceSqToEntity(entitylivingbase) < 144.0D && entitylivingbase.getAITarget() != null ? false : dog.isSitting());
        }
	}
	
	@Override
	public void updateTask() {
		
		this.dog.getNavigator().clearPathEntity();
	}
}
