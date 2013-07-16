package com.gitHub.copiousDogs.blocks;

import net.minecraft.block.material.Material;

import com.gitHub.copiousDogs.CopiousDogs;

public class BlockDogDish extends BlockCopiousDogs {

	public BlockDogDish(int id) {
		
		super(id, Material.iron);
		setBlockBounds(.2F, 0F, .2F, .8F, .3F, .8F);
		setUnlocalizedName("dogDish");
	}
	
	@Override
	public int getRenderType() {
		
		//TODO return CopiousDogs.dogDishRendererID when rendering issue is fixed
		return 0;
	}
	
	@Override
	public boolean isOpaqueCube() {
		
		return false;
	}
	
	@Override
	public boolean renderAsNormalBlock() {
		
		//TODO return false when rendering issue is fixed
		return true;
	}
}
