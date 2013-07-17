package com.gitHub.copiousDogs.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;

import com.gitHub.copiousDogs.CopiousDogs;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * A base item class for items within the mod. Sets up icon loading using the
 * items unlocalized name for future use.
 * 
 * @author Dramentiaras
 * 
 */
public class ItemCopiousDogs extends Item 
{

	public ItemCopiousDogs(int id) 
	{

		super(id);
		setCreativeTab(CopiousDogs.tabCopiousDogs);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) 
	{

		itemIcon = par1IconRegister.registerIcon("copiousdogs:"
				+ getUnlocalizedName().substring(5));
		
	}
}
