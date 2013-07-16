package com.gitHub.copiousDogs.render.blocks;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.blocks.models.ModelDogDish;

import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;

public class DogDishRenderer implements ISimpleBlockRenderingHandler {

	private ModelDogDish model;
	
	public DogDishRenderer() {
		
		model = new ModelDogDish();
	}

	@Override
	public void renderInventoryBlock(Block block, int metadata, int modelID,
			RenderBlocks renderer) {
	}

	@Override
	public boolean renderWorldBlock(IBlockAccess world, int x, int y, int z,
			Block block, int modelId, RenderBlocks renderer) {
		
		System.out.println("Rendering");
		
        GL11.glPushMatrix();
        GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
        
        //Commented out texture loading, doesn't work for some reason
        //ResourceLocation location = new ResourceLocation("/copiousdogs/textures/models", "model_dog_dish.png");
        //Minecraft.getMinecraft().renderEngine.func_110577_a(location);   
        
        GL11.glPushMatrix();
        GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

        this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
        GL11.glPopMatrix();
		
		return false;
	}

	@Override
	public boolean shouldRender3DInInventory() {
		
		return false;
	}

	@Override
	public int getRenderId() {
		
		return CopiousDogs.dogDishRendererID;
	}

}
