package com.gitHub.copiousDogs.render.mobs;

import com.gitHub.copiousDogs.mobs.GermanShepherd;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderGermanShepherd extends RenderDog
{
	public RenderGermanShepherd(ModelBase par1ModelBase, ModelBase par2ModelBase, float shadowSize)
	{
		super(par1ModelBase, par2ModelBase, shadowSize, "germanshepherdtexture");
	}

	public void renderTutorial(GermanShepherd entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
	
	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((GermanShepherd)par1EntityLiving, par2, par4, par6, par8, par9);
    }

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((GermanShepherd)par1Entity, par2, par4, par6, par8, par9);
    }
}
