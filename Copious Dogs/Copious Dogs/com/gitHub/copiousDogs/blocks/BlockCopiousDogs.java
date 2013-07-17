package com.gitHub.copiousDogs.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;

import com.gitHub.copiousDogs.CopiousDogs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * Same as ItemCopiousDogs but for blocks
 * 
 * @author Dramentiaras
 *
 */
public class BlockCopiousDogs extends Block {

	public BlockCopiousDogs(int id, Material material) {
		
		super(id, material);
		setCreativeTab(CopiousDogs.tabCopiousDogs);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		
		blockIcon = par1IconRegister.registerIcon("copiousdogs:" + getUnlocalizedName().substring(5));
	}
}
