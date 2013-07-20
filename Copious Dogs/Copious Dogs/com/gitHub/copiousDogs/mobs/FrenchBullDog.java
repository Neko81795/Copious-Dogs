package com.gitHub.copiousDogs.mobs;

import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class FrenchBullDog extends Dog
{
	public FrenchBullDog(World world)
	{
		super(world, 0.5F);
		this.setSize(0.4f, 1);
	}
	
	public int getAttackStrength(Entity par1Entity)
    {
		return 2;
    }
}