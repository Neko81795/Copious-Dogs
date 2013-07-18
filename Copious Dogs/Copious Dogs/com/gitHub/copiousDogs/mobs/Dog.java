package com.gitHub.copiousDogs.mobs;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

public class Dog extends EntityTameable
{
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
	
	public Dog(World world)
	{
		super(world);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		// TODO Auto-generated method stub
		return null;
	}
}