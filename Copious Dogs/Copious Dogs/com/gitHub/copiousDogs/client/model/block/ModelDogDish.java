package com.gitHub.copiousDogs.client.model.block;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.gitHub.copiousDogs.blocks.BlockDogDish;
import com.gitHub.copiousDogs.blocks.tileentity.TileEntityDogDish;

import cpw.mods.fml.client.FMLClientHandler;

public class ModelDogDish extends ModelBase {

	private IModelCustom model;
	
	public ModelDogDish() {
		
		model = AdvancedModelLoader.loadModel("/assets/copiousdogs/models/blocks/dog_dish_model.obj");
	}
	
	public void render() {
		
		model.renderPart("Dish");
	}
	
	public void render(TileEntityDogDish tileEntity, float par2, float par3, float par4) {

		GL11.glPushMatrix();

	    GL11.glTranslatef((float)par2 + .5f, (float)par3, (float)par4 + .5f);

	    GL11.glScalef(0.75f, 0.75f, 0.75f);
	    
	    FMLClientHandler.instance().getClient().renderEngine.bindTexture(
	    		new ResourceLocation("copiousdogs:textures/blocks/" + 
	    				BlockDogDish.getTextureFromMetadata(tileEntity.blockMetadata) + ".png"));

	    render();

	    if (tileEntity.getFoodLevel() > 0) {
	    	
	    	FMLClientHandler.instance().getClient().renderEngine.bindTexture(
	    			new ResourceLocation("copiousdogs:textures/blocks/food.png"));
	    	
	    	float var0 = 0.2f * (((float) tileEntity.getFoodLevel() - 1) / (float) tileEntity.getMaxFoodLevel());
	    	
	    	GL11.glTranslatef(0, var0, 0);
	    	
	    	model.renderPart("Food");
	    }
	    
	    GL11.glPopMatrix();
	}
}
