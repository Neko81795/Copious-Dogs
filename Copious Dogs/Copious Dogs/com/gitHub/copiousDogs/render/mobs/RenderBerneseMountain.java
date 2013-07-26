package com.gitHub.copiousDogs.render.mobs;

import com.gitHub.copiousDogs.mobs.BerneseMountain;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBerneseMountain extends RenderDog
{
	public RenderBerneseMountain(ModelBase par1ModelBase, float shadowSize)
	{
		super(par1ModelBase, shadowSize, "bernesemountaindogtexture");
	}

	public void renderTutorial(BerneseMountain entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
	
	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((BerneseMountain)par1EntityLiving, par2, par4, par6, par8, par9);
    }

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((BerneseMountain)par1Entity, par2, par4, par6, par8, par9);
    }
}