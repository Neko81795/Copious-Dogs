package com.gitHub.copiousDogs.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.blocks.tileentity.TileEntityDogDish;

public class BlockDogDish extends BlockContainer { 
	
	public BlockDogDish(int id) {

		super(id, Material.leaves);
		setBlockBounds(.20F, 0F, .20F, .8F, .25F, .8F);
		setCreativeTab(CopiousDogs.tabCopiousDogs);
		setUnlocalizedName("dogDish");
		setLightOpacity(1);
		
	}
	
	@Override
	public boolean renderAsNormalBlock() {

		return false;
	}
	
	@Override
	public int getRenderType() {

		return -1;
	}
	
	public static String getTextureFromMetadata(int par0) {
		
		return "dogDish_" + ItemDye.field_94595_b[getDyeFromBlock(par0)];
	}
	
	@Override
	public boolean isOpaqueCube() {
		
		return false;
	}
	
	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		
		return blockID;
	}
	
	public int damageDropped(int metadata)
    {
        return metadata;
    }
	
	@Override
	public int quantityDropped(int meta, int fortune, Random random) {
		
		return 1;
	}
	
	public static int getBlockFromDye(int par0)
    {
        return 15 - par0;
    }
	
	public static int getDyeFromBlock(int par0)
    {
        return 15 - par0;
    }
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void getSubBlocks(int par1, CreativeTabs par2CreativeTabs, List par3List)
    {
        for (int j = 0; j < 16; ++j)
        {
            par3List.add(new ItemStack(par1, 1, j));
        }
    }
	
	@Override
	public boolean onBlockActivated(World par1World, int par2, int par3,
			int par4, EntityPlayer entity, int par6, float par7,
			float par8, float par9) {
		
		TileEntityDogDish tileEntity = (TileEntityDogDish)par1World.getBlockTileEntity(par2, par3, par4);
		
		ItemStack stack = entity.getCurrentEquippedItem();
		
		if (stack != null) {
		
			boolean foodAdded = tileEntity.addFood(stack);
			
			if (foodAdded && !entity.capabilities.isCreativeMode) {
			
				stack.stackSize -= 1;
			}
		
			if (stack.itemID == Item.dyePowder.itemID) {
			
				par1World.setBlock(par2, par3, par4, blockID, getBlockFromDye(stack.getItemDamage()), 2);
				par1World.setBlockTileEntity(par2, par3, par4, tileEntity);
				
				if(!entity.capabilities.isCreativeMode)
					stack.stackSize -= 1;
			}
		}
		
		return super.onBlockActivated(par1World, par2, par3, par4, entity,
				par6, par7, par8, par9);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world) {
		
		return new TileEntityDogDish();
	}
}