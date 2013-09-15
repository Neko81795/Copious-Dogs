package com.gitHub.copiousDogs.client.render.tileentity;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;

import com.gitHub.copiousDogs.blocks.tileentity.TileEntityDogDish;
import com.gitHub.copiousDogs.client.model.block.ModelDogDish;

public class TileEntityDogDishRenderer extends TileEntitySpecialRenderer {

	private ModelDogDish model = new ModelDogDish();
	
	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1,
			double d2, float f) {

		if (tileentity instanceof TileEntityDogDish) {
			
			model.render((TileEntityDogDish) tileentity, (float)d0, (float)d1, (float)d2);
		}
	}
}
