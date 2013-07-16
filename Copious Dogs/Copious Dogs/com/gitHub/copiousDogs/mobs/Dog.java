package com.gitHub.copiousDogs.mobs;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.world.World;

public class Dog extends EntityTameable
{	
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