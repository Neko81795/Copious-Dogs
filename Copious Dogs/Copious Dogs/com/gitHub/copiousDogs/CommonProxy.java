package com.gitHub.copiousDogs;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import com.gitHub.copiousDogs.blocks.BlockDogDish;
import com.gitHub.copiousDogs.items.DogCollar;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{
	//Client stuffs
	public void registerRenderers()
	{ 
		
	}
	
	public void registerRecipes() {
    	
    	GameRegistry.addRecipe(new ItemStack(CopiousDogs.dogBiscuit), " m ", "mbm", " m ", 'm', Item.porkRaw, 'b', Item.bone);
    	GameRegistry.addRecipe(new ItemStack(CopiousDogs.dogDish), "III", "IBI", "III", 'I', Item.ingotIron, 'B', Item.bucketEmpty);
    	GameRegistry.addRecipe(new ItemStack(CopiousDogs.dogCollar), "SSS", "S S", "SSI", 'S', Item.silk, 'I', Item.ingotIron);
    	GameRegistry.addRecipe(new ItemStack(CopiousDogs.dogLeash), "SS", "SS", "SS", 'S', Item.silk);
    
    	for (int i = 0; i < 16; i++) {
    		
    		GameRegistry.addShapelessRecipe(new ItemStack(CopiousDogs.dogDish, 1, i), new ItemStack(Item.dyePowder, 1, BlockDogDish.getDyeFromBlock(i)), CopiousDogs.dogDish);
    		GameRegistry.addShapelessRecipe(new ItemStack(CopiousDogs.dogCollar, 1, i), new ItemStack(Item.dyePowder, 1, DogCollar.getDyeFromItem(i)), CopiousDogs.dogCollar);
    	}
    }
}