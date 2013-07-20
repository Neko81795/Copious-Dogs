package com.gitHub.copiousDogs.blocks;

import java.util.List;
import java.util.Random;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Icon;
import net.minecraft.world.World;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.blocks.tileentities.TileEntityDogDish;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockDogDish extends BlockContainer {
	
	private Icon[] blockIcons;
	
	public BlockDogDish(int id) {

		super(id, Material.leaves);
		setBlockBounds(.25F, 0F, .25F, .75F, .25F, .75F);
		setCreativeTab(CopiousDogs.tabCopiousDogs);
		setUnlocalizedName("dogDish");
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		
		blockIcons = new Icon[16];
		
		for (int i = 0; i < 16; i++) {
			
			blockIcons[i] = par1IconRegister.registerIcon("copiousdogs:" + getUnlocalizedName().substring(5) + "_" + ItemDye.field_94595_b[getDyeFromBlock(i)]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIcon(int par1, int par2) {

		return blockIcons[par2];
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
	public int onBlockPlaced(World par1World, int par2, int par3, int par4,
			int par5, float par6, float par7, float par8, int par9) {
		
		par1World.setBlock(par2, par3, par4, blockID, par9, 2);
		
		return super.onBlockPlaced(par1World, par2, par3, par4, par5, par6, par7, par8,
				par9);
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