package com.gitHub.copiousDogs.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.world.World;

public class GoldenRetriever extends Dog
{
	private static final float moveSpeed = 0.25f;
	
	@Override
	protected int getDropItemId()
	{
		return -1;
	}
	
	@Override
	protected String getLivingSound()
    {
        return "mob.wolf.bark";
    }

	@Override
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

	@Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

	@Override
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.wolf.step", 0.15F, 1.0F);
    }

	@Override
	protected boolean isAIEnabled()
    {
        return true;
    }
	
	public GoldenRetriever(World world)
	{
		super(world);
		this.setSize(1, 1);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWander(this, moveSpeed));
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
		return 2;
    }
	
	public String getTexture()
	{
		return "/CopiousDogs/GoldenRetriever.png";
	}
}