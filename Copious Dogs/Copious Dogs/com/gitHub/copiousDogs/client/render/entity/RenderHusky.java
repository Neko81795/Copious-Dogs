package com.gitHub.copiousDogs.client.render.entity;

import com.gitHub.copiousDogs.entity.Husky;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderHusky extends RenderDog
{
	public RenderHusky(ModelBase par1ModelBase, ModelBase par2ModelBase, float shadowSize)
	{
		super(par1ModelBase, par2ModelBase, shadowSize, "huskytexture");
	}

	public void renderTutorial(Husky entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
	
	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((Husky)par1EntityLiving, par2, par4, par6, par8, par9);
    }

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((Husky)par1Entity, par2, par4, par6, par8, par9);
    }
}
