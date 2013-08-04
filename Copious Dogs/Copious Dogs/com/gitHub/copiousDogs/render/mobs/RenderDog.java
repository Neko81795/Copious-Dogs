package com.gitHub.copiousDogs.render.mobs;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.item.ItemDye;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import com.gitHub.copiousDogs.Reference;
import com.gitHub.copiousDogs.items.DogCollar;
import com.gitHub.copiousDogs.mobs.Dog;

public class RenderDog extends RenderLiving {

	String texture;
	
	public RenderDog(ModelBase par1ModelBase, float par2, String texture) {
		super(par1ModelBase, par2);
		this.texture = texture;
	}
	
	@Override
	protected void passSpecialRender(EntityLivingBase par1EntityLivingBase,
			double par2, double par4, double par6) {
		
		if (MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Specials.Pre(par1EntityLivingBase, this))) return;
        if (this.func_110813_b(par1EntityLivingBase))
        {
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            double d3 = par1EntityLivingBase.getDistanceSqToEntity(this.renderManager.livingPlayer);
            float f2 = par1EntityLivingBase.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;

            if (d3 < (double)(f2 * f2))
            {
                String s = par1EntityLivingBase.getTranslatedEntityName();

                if (par1EntityLivingBase.isSneaking())
                {
                    FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)par2 + 0.0F, (float)par4 + par1EntityLivingBase.height + 0.5F, (float)par6);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(-f1, -f1, f1);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glTranslatef(0.0F, 0.25F / f1, 0.0F);
                    GL11.glDepthMask(false);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Tessellator tessellator = Tessellator.instance;
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    tessellator.startDrawingQuads();
                    int i = fontrenderer.getStringWidth(s) / 2;
                    tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                    tessellator.addVertex((double)(-i - 1), -1.0D, 0.0D);
                    tessellator.addVertex((double)(-i - 1), 8.0D, 0.0D);
                    tessellator.addVertex((double)(i + 1), 8.0D, 0.0D);
                    tessellator.addVertex((double)(i + 1), -1.0D, 0.0D);
                    tessellator.draw();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glDepthMask(true);
                    fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, 0, 553648127);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glPopMatrix();
                }
                else
                {
                    this.func_96449_a(par1EntityLivingBase, par2, par4, par6, s, f1, d3);
                }
            }
        }
        
        if (par1EntityLivingBase instanceof Dog) {
        	
        	Dog dog = (Dog) par1EntityLivingBase;
        	
        	if (dog.isTamed()) renderTag(dog.func_110143_aJ() + "/" + dog.getMaxHealth(), par1EntityLivingBase, par2, par4, par6, -.25F);
        	
        	if (Reference.DEBUG) {
        		
        		renderTag("Is eating:" + dog.isEating(), par1EntityLivingBase, par2, par4, par6, .25F);
        		renderTag("Is leashed:" + dog.isLeashed(), par1EntityLivingBase, par2, par4, par6, .5F);
        		renderTag("Is tamed:" + dog.isTamed(), par1EntityLivingBase, par2, par4, par6, .75F);
        		if (dog.isTamed())renderTag("Owner:" + dog.getOwnerName(), par1EntityLivingBase, par2, par4, par6, 1F);
        	}
        }
	}
	
	protected void renderTag(String par0String, EntityLivingBase par1EntityLivingBase,
			double par2, double par4, double par6, float yOffset) {
        	
      	Dog dog = (Dog) par1EntityLivingBase;
            
       	String s = par0String;
        	
      	double d3 = par1EntityLivingBase.getDistanceSqToEntity(this.renderManager.livingPlayer);
       	float f2 = par1EntityLivingBase.isSneaking() ? NAME_TAG_RANGE_SNEAK : NAME_TAG_RANGE;    
            
       	if (d3 <= (double)(f2 * f2))
       	{
       		FontRenderer fontrenderer = this.getFontRendererFromRenderManager();
            float f = 1.6F;
            float f1 = 0.016666668F * f;
            GL11.glPushMatrix();
            GL11.glTranslatef((float)par2 + 0.0F, (float)par4 + par1EntityLivingBase.height + 0.5F + yOffset, (float)par6);
            GL11.glNormal3f(0.0F, 1.0F, 0.0F);
            GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
            GL11.glScalef(-f1, -f1, f1);
            GL11.glDisable(GL11.GL_LIGHTING);
            GL11.glDepthMask(false);
            GL11.glDisable(GL11.GL_DEPTH_TEST);
            GL11.glEnable(GL11.GL_BLEND);
            GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
            Tessellator tessellator = Tessellator.instance;
            byte b0 = 0;

            if (s.equals("deadmau5"))
            {
            	b0 = -10;
            }

            GL11.glDisable(GL11.GL_TEXTURE_2D);
            tessellator.startDrawingQuads();
            int j = fontrenderer.getStringWidth(s) / 2;
            tessellator.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
            tessellator.addVertex((double)(-j - 1), (double)(-1 + b0), 0.0D);
           	tessellator.addVertex((double)(-j - 1), (double)(8 + b0), 0.0D);
          	tessellator.addVertex((double)(j + 1), (double)(8 + b0), 0.0D);
          	tessellator.addVertex((double)(j + 1), (double)(-1 + b0), 0.0D);
           	tessellator.draw();
           	GL11.glEnable(GL11.GL_TEXTURE_2D);
          	fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, b0, 553648127);
         	GL11.glEnable(GL11.GL_DEPTH_TEST);
          	GL11.glDepthMask(true);
           	fontrenderer.drawString(s, -fontrenderer.getStringWidth(s) / 2, b0, -1);
           	GL11.glEnable(GL11.GL_LIGHTING);
          	GL11.glDisable(GL11.GL_BLEND);
          	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
           	GL11.glPopMatrix();
        }
        MinecraftForge.EVENT_BUS.post(new RenderLivingEvent.Specials.Post(par1EntityLivingBase, this));
	}
	@Override
	protected ResourceLocation func_110775_a(Entity entity) {
		
		Dog dog = (Dog) entity;
		
		if (dog.hasCollar()) {
			
			return new ResourceLocation("copiousDogs:textures/mobs/" + texture + "_collar_" + ItemDye.field_94595_b[dog.getCollarColor()] + ".png");
		}
		
		return new ResourceLocation("copiousDogs:textures/mobs/" + texture + ".png");
	}

}
