package com.gitHub.copiousDogs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class Beagle extends Dog {

	public Beagle(World world) {
		
		super(world, .4F, "beagle");
		this.setSize(0.4f, 1);
		this.targetTasks.addTask(0, new EntityAIOwnerHurtByTarget(this));
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
