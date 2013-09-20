package com.gitHub.copiousDogs.client.render.entity;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;

import org.lwjgl.opengl.GL11;

import com.gitHub.copiousDogs.entity.Dog;
import com.gitHub.copiousDogs.items.DogCollar;
import com.gitHub.copiousDogs.lib.Reference;

public class RenderDog extends RenderLiving {

	ResourceLocation texture;
	ResourceLocation collarTexture;
	
	public RenderDog(ModelBase par1ModelBase, ModelBase par2ModelBase, float par2, String texture) {
		super(par1ModelBase, par2);
		this.setRenderPassModel(par2ModelBase);
		this.texture = new ResourceLocation("copiousDogs:textures/mobs/" + texture + ".png");
		this.collarTexture = new ResourceLocation("copiousDogs:textures/mobs/" + texture + "_collar.png");
	}
	
	protected int func_82447_a(Dog par1Dog, int par2, float par3)
    {
        float f1;
        if (par2 == 1 && par1Dog.isTamed() && par1Dog.hasCollar())
        {
            this.bindTexture(collarTexture);
            f1 = 1.0F;
            int j = DogCollar.getItemFromDye(par1Dog.getCollarColor());      
            GL11.glColor3f(f1 * EntitySheep.fleeceColorTable[j][0], f1 * EntitySheep.fleeceColorTable[j][1], f1 * EntitySheep.fleeceColorTable[j][2]);
            return 1;
        }
        else
        {
            return -1;
        }
    }
	
	@Override
	public void doRenderLiving(EntityLiving par1EntityLiving, double par2,
			double par4, double par6, float par8, float par9) {
		
		super.doRenderLiving(par1EntityLiving, par2, par4, par6, par8, par9);
		
		Dog dog = (Dog) par1EntityLiving;
		
		if (dog.isLeashed()) {
			
			float f9 = ((EntityLivingBase) dog.getOwner()).getSwingProgress(par9);
	        float f10 = MathHelper.sin(MathHelper.sqrt_float(f9) * (float)Math.PI);
			
	        Vec3 vec3 = dog.worldObj.getWorldVec3Pool().getVecFromPool(-.03D, 0D, -.03D);
	        vec3.rotateAroundX(-(dog.getOwner().prevRotationPitch + (dog.getOwner().rotationPitch - dog.getOwner().prevRotationPitch) * par9) * (float)Math.PI / 180.0F);
	        vec3.rotateAroundY(-(dog.getOwner().prevRotationYaw + (dog.getOwner().rotationYaw - dog.getOwner().prevRotationYaw) * par9) * (float)Math.PI / 180.0F);
	        vec3.rotateAroundY(f10 * 0.5F);
	        vec3.rotateAroundX(-f10 * 0.7F);
			
			double d3 = dog.getOwner().prevPosX + (dog.getOwner().posX - dog.getOwner().prevPosX) * (double)par9 + vec3.xCoord;
	        double d4 = dog.getOwner().prevPosY + (dog.getOwner().posY - dog.getOwner().prevPosY) * (double)par9 + vec3.yCoord;
	        double d5 = dog.getOwner().prevPosZ + (dog.getOwner().posZ - dog.getOwner().prevPosZ) * (double)par9 + vec3.zCoord;
			
			double d9 = par1EntityLiving.prevPosX + (par1EntityLiving.posX - par1EntityLiving.prevPosX) * (double)par9;
	        double d10 = par1EntityLiving.prevPosY + (par1EntityLiving.posY - par1EntityLiving.prevPosY) * (double)par9 + 0.5D;
	        double d11 = par1EntityLiving.prevPosZ + (par1EntityLiving.posZ - par1EntityLiving.prevPosZ) * (double)par9;
	        double d12 = (double)((float)(d3 - d9));
	        double d13 = (double)((float)(d4 - d10));
	        double d14 = (double)((float)(d5 - d11));
	        GL11.glDisable(GL11.GL_TEXTURE_2D);
	        GL11.glDisable(GL11.GL_LIGHTING);
	        
	        Tessellator tessellator = Tessellator.instance;
	 
	        tessellator.startDrawing(3);
	        tessellator.setColorOpaque_I(0);
	        byte b2 = 16;
	
	        for (int i = 0; i <= b2; ++i)
	        {
	            float f12 = (float)i / (float)b2;
	            tessellator.addVertex(par2 + d12 * (double)f12, par4 + d13 * (double)(f12 * f12 + f12) * 0.5D + 0.25D, par6 + d14 * (double)f12);
	        }
	        
	        tessellator.draw();
	        
	        GL11.glEnable(GL11.GL_TEXTURE_2D);
	        GL11.glEnable(GL11.GL_LIGHTING);
		}
	}
	
	@Override
	public void doRender(Entity par1Entity, double par2, double par4,
			double par6, float par8, float par9) {

		doRenderLiving((EntityLiving)par1Entity, par2, par4, par6, par8, par9);
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
        	
        	if (Reference.DEBUG) {
        		
        		renderTag("Is eating:" + dog.isEating(), par1EntityLivingBase, par2, par4, par6, .25F);
        		renderTag("Is leashed:" + dog.isLeashed(), par1EntityLivingBase, par2, par4, par6, .5F);
        		renderTag("Is tamed:" + dog.isTamed(), par1EntityLivingBase, par2, par4, par6, .75F);
        		if (dog.isTamed())renderTag("Owner:" + dog.getOwnerName(), par1EntityLivingBase, par2, par4, par6, 1F);
        		renderTag("Is in love:" + dog.isInLove(), par1EntityLivingBase, par2, par4, par6, 1.25F);
        		renderTag("In love:" + dog.inLove, par1EntityLivingBase, par2, par4, par6, 1.5F);
        		renderTag(dog.getHealth() + "/" + dog.getMaxHealth(), par1EntityLivingBase, par2, par4, par6, -.25F);
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
	protected int shouldRenderPass(EntityLivingBase par1EntityLivingBase, int par2, float par3)
    {
        return this.func_82447_a((Dog)par1EntityLivingBase, par2, par3);
    }

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		
		return texture;
	}

}
