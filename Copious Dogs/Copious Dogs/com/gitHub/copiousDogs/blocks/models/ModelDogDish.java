package com.gitHub.copiousDogs.blocks.models;

import net.minecraft.client.model.ModelBase;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

import com.gitHub.copiousDogs.blocks.BlockDogDish;
import com.gitHub.copiousDogs.blocks.tileentities.TileEntityDogDish;

import cpw.mods.fml.client.FMLClientHandler;

public class ModelDogDish extends ModelBase {

	private IModelCustom model;
	
	public ModelDogDish() {
		
		model = AdvancedModelLoader.loadModel("/assets/copiousdogs/models/blocks/dog_dish_model.obj");
	}
	
	public void render() {
		
		model.renderAll();
	}
	
	public void render(TileEntityDogDish tileEntity, float par2, float par3, float par4) {

		GL11.glPushMatrix();

	    GL11.glTranslatef((float)par2 + .5f, (float)par3, (float)par4 + .5f);

	    GL11.glScalef(0.75f, 0.75f, 0.75f);
	    
	    FMLClientHandler.instance().getClient().renderEngine.func_110577_a(
	    		new ResourceLocation("copiousdogs:textures/blocks/" + 
	    				BlockDogDish.getTextureFromMetadata(tileEntity.blockMetadata) + ".png"));

	    render();

	    GL11.glPopMatrix();
	}
}
