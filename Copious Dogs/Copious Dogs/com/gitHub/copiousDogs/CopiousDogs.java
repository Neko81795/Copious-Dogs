package com.gitHub.copiousDogs;

import net.minecraft.item.Item;

import com.gitHub.copiousDogs.items.DogBiscuit;

import cpw.mods.fml.common.*;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

@Mod(modid = "CopiousDogs", name = "Copious Dogs", version = "0.0.0.0")
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = "[0.0.0.0]")
public class CopiousDogs
{
	//
	//Items
	//
	private static Item dogBiscuit = new DogBiscuit(500);
	
	//
	//Mobs
	//
	
	
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
    	// Dog Biscuit
    	//
    	LanguageRegistry.addName(dogBiscuit, "Dog Biscuit");
    }
   
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
            // Stub Method
    }
}