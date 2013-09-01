package com.gitHub.copiousDogs.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class BerneseMountain extends Dog
{
	public BerneseMountain(World world)
	{
		super(world, 0.5F, "bernese_mountain");
		this.setSize(0.4f, 1);
		this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
		return 2;
    }
}