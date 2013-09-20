package com.gitHub.copiousDogs.client.render.items;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;

import org.lwjgl.opengl.GL11;

import com.gitHub.copiousDogs.blocks.BlockDogDish;
import com.gitHub.copiousDogs.client.model.block.ModelDogDish;

import cpw.mods.fml.client.FMLClientHandler;

public class ItemDogDishRenderer implements IItemRenderer {

	private ModelDogDish model;

	public ItemDogDishRenderer() {
		
		model = new ModelDogDish();
	}
	
	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {

		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,
			ItemRendererHelper helper) {

		return true;
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		
		switch(type)
	    {
	        case ENTITY:{
	            renderDogDish(item.getItemDamage(),0f, 0f, 0f, .75f);
	            return;
	        }
	         
	        case EQUIPPED:{
	            renderDogDish(item.getItemDamage(), 0f, 1f, 1f, .75f);
	            return;
	        }
	             
	        case INVENTORY:{
	            renderDogDish(item.getItemDamage(), 0f, 0f, 0f, .75f);
	            return;
	        }
	         
	        default:return;
	    }
	}
	
	private void renderDogDish(int meta, float x, float y, float z, float scale)
	{
	    GL11.glPushMatrix();
	 
	    // Disable Lighting Calculations
	    GL11.glDisable(GL11.GL_LIGHTING);
	     
	    GL11.glTranslatef(x,  y,  z);
	    GL11.glScalef(scale, scale, scale);
	    GL11.glRotatef(180f, 0f, 1f, 0f);
	     
	    FMLClientHandler.instance().getClient().renderEngine.bindTexture(new ResourceLocation("copiousdogs:textures/blocks/" + BlockDogDish.getTextureFromMetadata(meta) + ".png"));
	     
	    model.render();
	     
	    // Re-enable Lighting Calculations
	    GL11.glEnable(GL11.GL_LIGHTING);
	    GL11.glPopMatrix();
	}
}
