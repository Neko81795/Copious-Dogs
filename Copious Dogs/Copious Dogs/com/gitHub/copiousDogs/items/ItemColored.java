package com.gitHub.copiousDogs.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemColored extends ItemCopiousDogs {

	private Icon[] itemIcons;
	
	public ItemColored(int id) {
		
		super(id);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister par1IconRegister) {
		
		itemIcons = new Icon[16];
		
		for (int i = 0; i < 16; i++) {
			
			itemIcons[i] = par1IconRegister.registerIcon("copiousdogs:" + getUnlocalizedName().substring(5) + "_" + ItemDye.dyeItemNames[getItemFromDye(i)]);
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List) {
		
		for (int i = 0; i < 16; i++) {
			
			par3List.add(new ItemStack(itemID, 1, i));
		}
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public Icon getIconFromDamage(int par1) {
		
		return itemIcons[par1];
	}
	
	public static int getItemFromDye(int par0)
    {
        return 15 - par0;
    }
	
	public static int getDyeFromItem(int par0)
    {
        return 15 - par0;
    }
}
