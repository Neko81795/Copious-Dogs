package com.gitHub.copiousDogs.mobs.models;

import com.gitHub.copiousDogs.mobs.Dog;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public abstract class DogModelBase extends ModelBase
{
	ModelRenderer WolfHead;
	ModelRenderer Body;
	ModelRenderer Mane;
	ModelRenderer Leg1;
	ModelRenderer Leg2;
	ModelRenderer Leg3;
	ModelRenderer Leg4;
	ModelRenderer Tail;
	ModelRenderer Ear1;
	ModelRenderer Ear2;
	ModelRenderer Nose;

	protected void setRotation(ModelRenderer model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setLivingAnimations(Dog entity, float walkTime,
			float isWalking, float random)
	{
		if (entity.isTailAnimated())
		{
			this.Tail.rotateAngleY = MathHelper.cos(entity.ticksExisted * 0.8F) * 0.6F;
		}
		else
		{
			this.Tail.rotateAngleY = 0;
		}
		
		this.Leg1.rotateAngleX = MathHelper.cos(walkTime * 0.6662F) * 1.4F * isWalking;
		this.Leg2.rotateAngleX = MathHelper.cos(walkTime * 0.6662F + (float) Math.PI) * 1.4F * isWalking;
		this.Leg3.rotateAngleX = MathHelper.cos(walkTime * 0.6662F + (float) Math.PI) * 1.4F * isWalking;
		this.Leg4.rotateAngleX = MathHelper.cos(walkTime * 0.6662F) * 1.4F * isWalking;
		
		super.setLivingAnimations(entity, walkTime, isWalking, random);
	}
	
	@Override
	public void setLivingAnimations(EntityLivingBase entity, float walkTime,
			float isWalking, float random)
	{
		setLivingAnimations((Dog)entity, walkTime, isWalking, random);
	}

	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity)
	{
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}