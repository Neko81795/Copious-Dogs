package com.gitHub.copiousDogs.entity.ai;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.world.World;

import com.gitHub.copiousDogs.entity.Dog;

public class EntityAIMateNearTorch extends EntityAIBase {
	
	private Dog dog;
    private World theWorld;
    private Dog dog1;
    private float radius;

    /**
     * Delay preventing a baby from spawning immediately when two mate-able animals find each other.
     */
    int spawnBabyDelay;

    /** The speed the creature moves at during mating behavior. */
    double moveSpeed;

    public EntityAIMateNearTorch(Dog par1EntityAnimal, double par2, float par3)
    {
        this.dog = par1EntityAnimal;
        this.theWorld = par1EntityAnimal.worldObj;
        this.moveSpeed = par2;
        this.setMutexBits(3);
        radius = par3;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	
        if (!this.dog.isInLove())
        {
            return false;
        }
        else
        {
        	
            this.dog1 = this.getNearbyMate();
            System.out.println(dog1);
            return this.dog1 != null && isNearHeat();
        }
    }

    private boolean isNearHeat() {

    	for (int x = 0; x < radius * 2; x++) {
			for (int y = 0; y < radius * 2; y++) {
				for (int z = 0; z < radius * 2; z++) {
					if (theWorld.getBlockId((int)(dog.posX + x - radius), (int)(dog.posY + y - radius), (int)(dog.posZ + z - radius)) == Block.fire.blockID ||
							theWorld.getBlockId((int)(dog.posX + x - radius), (int)(dog.posY + y - radius), (int)(dog.posZ + z - radius)) == Block.torchWood.blockID) {
						
						return true;
					}
				}
			}
    	}
    	
		return false;
	}

	/**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return this.dog1.isEntityAlive() && this.dog1.isInLove() && this.spawnBabyDelay < 60;
    }

    /**
     * Resets the task
     */
    public void resetTask()
    {
        this.dog1 = null;
        this.spawnBabyDelay = 0;
    }

    /**
     * Updates the task
     */
    public void updateTask()
    {
    	
        this.dog.getLookHelper().setLookPositionWithEntity(this.dog1, 10.0F, (float)this.dog.getVerticalFaceSpeed());
        this.dog.getNavigator().tryMoveToEntityLiving(this.dog1, this.moveSpeed);
        ++this.spawnBabyDelay;

        if (this.spawnBabyDelay >= 60 && this.dog.getDistanceSqToEntity(this.dog1) < 9.0D)
        {
            this.spawnBaby();
        }
    }

    /**
     * Loops through nearby animals and finds another animal of the same type that can be mated with. Returns the first
     * valid mate found.
     */
    private Dog getNearbyMate()
    {
        float f = 15F;
        List list = this.theWorld.getEntitiesWithinAABB(this.dog.getClass(), this.dog.boundingBox.expand((double)f, (double)f, (double)f));
        double d0 = Double.MAX_VALUE;
        Dog entityanimal = null;
        Iterator iterator = list.iterator();

        while (iterator.hasNext())
        {
            Dog entityanimal1 = (Dog)iterator.next();

            if (this.dog.canMateWith(entityanimal1) && this.dog.getDistanceSqToEntity(entityanimal1) < d0)
            {
                entityanimal = entityanimal1;
                d0 = this.dog.getDistanceSqToEntity(entityanimal1);
            }
        }

        return entityanimal;
    }

    /**
     * Spawns a baby animal of the same type.
     */
    private void spawnBaby()
    {
        EntityAgeable entityageable = this.dog.createChild(this.dog1);

        if (entityageable != null)
        {
            this.dog.setGrowingAge(6000);
            this.dog1.setGrowingAge(6000);
            this.dog.resetInLove();
            this.dog1.resetInLove();
            entityageable.setGrowingAge(-24000);
            entityageable.setLocationAndAngles(this.dog.posX, this.dog.posY, this.dog.posZ, 0.0F, 0.0F);
            this.theWorld.spawnEntityInWorld(entityageable);
            Random random = this.dog.getRNG();

            for (int i = 0; i < 7; ++i)
            {
                double d0 = random.nextGaussian() * 0.02D;
                double d1 = random.nextGaussian() * 0.02D;
                double d2 = random.nextGaussian() * 0.02D;
                this.theWorld.spawnParticle("heart", this.dog.posX + (double)(random.nextFloat() * this.dog.width * 2.0F) - (double)this.dog.width, this.dog.posY + 0.5D + (double)(random.nextFloat() * this.dog.height), this.dog.posZ + (double)(random.nextFloat() * this.dog.width * 2.0F) - (double)this.dog.width, d0, d1, d2);
            }

            this.theWorld.spawnEntityInWorld(new EntityXPOrb(this.theWorld, this.dog.posX, this.dog.posY, this.dog.posZ, random.nextInt(7) + 1));
        }
    }
}

