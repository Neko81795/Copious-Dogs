package com.gitHub.copiousDogs.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Husky extends Dog
{

	public Husky(World world)
	{
		super(world, .5F, "husky");
		this.setSize(0.4f, 1);
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
		this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
		return 2;
    }
	
	public boolean attackEntityAsMob(Entity par1Entity)
    {
        return par1Entity.attackEntityFrom(DamageSource.causeMobDamage(this), getAttackStrength(par1Entity));
    }
}