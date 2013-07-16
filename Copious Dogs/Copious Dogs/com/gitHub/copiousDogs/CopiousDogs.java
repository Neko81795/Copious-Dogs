package com.gitHub.copiousDogs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeCache;
import net.minecraft.world.biome.BiomeGenBase;

import com.gitHub.copiousDogs.items.DogBiscuit;
import com.gitHub.copiousDogs.mobs.Dog;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "CopiousDogs", name = "Copious Dogs", version = "0.0.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = "[0.0.0.0]")
public class CopiousDogs
{
	//
	//Creative Tabs
	//
	public static CreativeTabs tabCopiousDogs = new CreativeTabs("tabCopiousDogs")
	{
        public ItemStack getIconItemStack()
        {
                return new ItemStack(dogBiscuit, 1, 0);
        }
	};
	//
	//Items
	//
	public static Item dogBiscuit;
	public static Item spawnGoldenRetriever;
	
	
	@Instance("CopiousDogs")
	public static CopiousDogs instance;
	
	@SidedProxy(clientSide = "com.gitHub.copiousDogs.client.ClientProxy", 
			serverSide = "com.gitHub.copiousDogs.CommonProxy")
	public static CommonProxy proxy;
	

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	// Stub Method
    }
   
    @EventHandler
    public void load(FMLInitializationEvent event) 
    {
    	//
    	//tab Copious dogs
    	//
    	LanguageRegistry.instance().addStringLocalization("itemGroup.tabCopiousDogs", "en_US", "Copious Dogs");
    	//
    	//Dog Biscuit
    	//
    	dogBiscuit = new DogBiscuit(500);
    	LanguageRegistry.addName(dogBiscuit, "Dog Biscuit");
    	GameRegistry.addRecipe(new ItemStack(dogBiscuit), " m ", "mbm", " m ", 'm', Item.porkRaw, 'b', Item.bone);
    	
    	
    	//
    	//Golden Retriever
    	//
    	EntityRegistry.registerModEntity(Dog.class, "Golden Retriever", 1, this, 40, 1, true);
    	EntityRegistry.addSpawn(Dog.class, 10, 2, 6, EnumCreatureType.creature, BiomeGenBase.plains, 
    			BiomeGenBase.forest, BiomeGenBase.forestHills);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.GoldenRetriever.name", "Golden Retirever");
    }
   
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
            // Stub Method
    }
}