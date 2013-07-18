package com.gitHub.copiousDogs.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.world.World;

public class GoldenRetriever extends Dog
{
	private static final float moveSpeed = 0.5f;
	
	public GoldenRetriever(World world)
	{
		super(world);
		this.setSize(0.4f, 1);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWander(this, moveSpeed));
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
		return 2;
    }
}