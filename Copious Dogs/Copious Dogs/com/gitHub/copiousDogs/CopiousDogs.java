package com.gitHub.copiousDogs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;

import com.gitHub.copiousDogs.blocks.BlockDogDish;
import com.gitHub.copiousDogs.items.DogBiscuit;
import com.gitHub.copiousDogs.mobs.Dog;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
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
	
	//
	//Blocks
	//
	public static Block dogDish;
	
	
	@Instance("CopiousDogs")
	public static CopiousDogs instance;
	
	@SidedProxy(clientSide = "com.gitHub.copiousDogs.client.ClientProxy", 
			serverSide = "com.gitHub.copiousDogs.CommonProxy")
	public static CommonProxy proxy;
	
	public static int dogDishRendererID;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) 
    {
    	//
    	//Loads the copiousdogs.cfg config file
    	//
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	
    	config.load();
    	
    	//
    	//reads the config file for item ids
    	//
    	Reference.DOG_BISCUIT_ID = config.getItem("Dog biscuit:", 17001).getInt();
    	
    	//reads the config file for block ids
    	Reference.DOG_DISH_ID = config.getBlock("Dog dish:", 1701).getInt();
    	
    	config.save();
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
    	dogBiscuit = new DogBiscuit(Reference.DOG_BISCUIT_ID);
    	LanguageRegistry.addName(dogBiscuit, "Dog Biscuit");
    	GameRegistry.addRecipe(new ItemStack(dogBiscuit), " m ", "mbm", " m ", 'm', Item.porkRaw, 'b', Item.bone);
    	//
    	//Golden Retriever
    	//
    	EntityRegistry.registerModEntity(Dog.class, "Golden Retriever", 1, this, 40, 1, true);
    	EntityRegistry.addSpawn(Dog.class, 10, 2, 6, EnumCreatureType.creature, BiomeGenBase.plains, 
    			BiomeGenBase.forest, BiomeGenBase.forestHills);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.GoldenRetriever.name", "Golden Retirever");
    	//
    	// Dog Dish
    	//
    	dogDish = new BlockDogDish(Reference.DOG_DISH_ID);
    	GameRegistry.registerBlock(dogDish, "dogDish");
    	GameRegistry.addRecipe(new ItemStack(dogDish), "III", "IBI", "III", 'I', Item.ingotIron, 'B', Item.bucketEmpty);
    	LanguageRegistry.addName(dogDish, "Dog Dish");
    	
    	proxy.registerRenderers();
    }
   
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
            // Stub Method
    }
}
