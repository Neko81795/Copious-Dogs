package com.gitHub.copiousDogs.render.mobs;

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

import com.gitHub.copiousDogs.mobs.FrenchBullDog;

public class RenderFrenchBulldog extends RenderDog
{
	public RenderFrenchBulldog(ModelBase par1ModelBase, float par2)
	{
		super(par1ModelBase, par2, "frenchbulldogtexture");
		// TODO Auto-generated constructor stub
	}

	public void renderTutorial(FrenchBullDog entity, double par2, double par4, double par6, float par8, float par9)
    {
        super.doRenderLiving(entity, par2, par4, par6, par8, par9);
    }
	
	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((FrenchBullDog)par1EntityLiving, par2, par4, par6, par8, par9);
    }

	public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        renderTutorial((FrenchBullDog)par1Entity, par2, par4, par6, par8, par9);
    }
}