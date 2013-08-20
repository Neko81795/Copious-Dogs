package com.gitHub.copiousDogs.render.mobs;

import com.gitHub.copiousDogs.mobs.Dalmatian;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderDalmatian extends RenderDog
{
	public RenderDalmatian(ModelBase par1ModelBase, ModelBase par2ModelBase, float shadowSize)
	{
		super(par1ModelBase, par2ModelBase, shadowSize, "dalmatiantexture");
	}

	public void renderTutorial(Dalmatian entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
	
	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((Dalmatian)par1EntityLiving, par2, par4, par6, par8, par9);
    }

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((Dalmatian)par1Entity, par2, par4, par6, par8, par9);
    }
}
