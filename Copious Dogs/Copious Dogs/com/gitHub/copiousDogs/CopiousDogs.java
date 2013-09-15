package com.gitHub.copiousDogs;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.Configuration;

import com.gitHub.copiousDogs.blocks.BlockDogDish;
import com.gitHub.copiousDogs.blocks.tileentity.TileEntityDogDish;
import com.gitHub.copiousDogs.configuration.CopiousDogsEntity;
import com.gitHub.copiousDogs.entity.Beagle;
import com.gitHub.copiousDogs.entity.BerneseMountain;
import com.gitHub.copiousDogs.entity.Chihuahua;
import com.gitHub.copiousDogs.entity.Dalmatian;
import com.gitHub.copiousDogs.entity.FrenchBullDog;
import com.gitHub.copiousDogs.entity.GermanShepherd;
import com.gitHub.copiousDogs.entity.GoldenRetriever;
import com.gitHub.copiousDogs.entity.Husky;
import com.gitHub.copiousDogs.handler.PacketHandler;
import com.gitHub.copiousDogs.items.DogBiscuit;
import com.gitHub.copiousDogs.items.DogCollar;
import com.gitHub.copiousDogs.items.DogDishItem;
import com.gitHub.copiousDogs.items.DogLeash;
import com.gitHub.copiousDogs.lib.Reference;

import cpw.mods.fml.common.Loader;
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

@Mod(modid = "CopiousDogs", name = "Copious Dogs", version = Reference.VERSION)
@NetworkMod(clientSideRequired = true, serverSideRequired = false, versionBounds = "[" + Reference.VERSION + "]",
		channels = {Reference.CHANNEL_NAME}, packetHandler = PacketHandler.class)
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
	public static Item dogCollar;
	public static Item dogLeash;
	
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
    	
    	//Loads the copiousdogs.cfg config file
    	Configuration config = new Configuration(event.getSuggestedConfigurationFile());
    	
    	config.load();
    	
    	//reads the config file for item ids
    	Reference.DOG_BISCUIT_ID = config.getItem("Dog biscuit:", 17001).getInt();
    	Reference.EGG_GOLDEN_RETRIEVER_ID = config.getItem("Egg Golden Retriever:", 17002).getInt();
    	Reference.DOG_COLLAR_ID = config.getItem("Dog collar:", 17003).getInt();
    	Reference.EGG_HUSKY_ID = config.getItem("Egg Husky:", 17004).getInt();
    	Reference.DOG_LEASH_ID = config.getItem("Dog leash:", 17005).getInt();
    	Reference.EGG_BERNESE_MOUNTAIN_ID = config.getItem("Egg Bernese Mountain:", 17006).getInt();
    	Reference.EGG_CHIHUAHUA_ID = config.getItem("Egg Chihuahua:", 17007).getInt();
    	Reference.EGG_FRENCH_BULLDOG_ID = config.getItem("Egg French Bulldog:", 17008).getInt();
    	Reference.EGG_GERMAN_SHEPHERD_ID = config.getItem("Egg GermanShepherd", 17009).getInt();
    	Reference.EGG_DALMATIAN_ID = config.getItem("Egg Dalmatian:", 17010).getInt();
    	Reference.EGG_BEAGLE_ID = config.getItem("Egg Beagle:", 17011).getInt();
    	
    	//reads the config file for block ids
    	Reference.DOG_DISH_ID = config.getBlock("Dog dish:", 1701).getInt();
    	
    	config.save();
    }
   
    @SuppressWarnings("unchecked")
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
    	//
    	//Dog Collar
    	//
    	dogCollar = new DogCollar(Reference.DOG_COLLAR_ID);
    	LanguageRegistry.addName(dogCollar, "Dog Collar");
    	//
    	//Dog leash
    	//
    	dogLeash = new DogLeash(Reference.DOG_LEASH_ID);
    	LanguageRegistry.addName(dogLeash, "Dog Leash");
    	
    	CopiousDogsEntity.init();
    	
    	//
    	// Dog Dish
    	//
    	dogDish = new BlockDogDish(Reference.DOG_DISH_ID);
    	GameRegistry.registerBlock(dogDish, DogDishItem.class, "dogDish");
    	GameRegistry.registerTileEntity(TileEntityDogDish.class, "dog_dish_entity");
    	LanguageRegistry.addName(dogDish, "Dog Dish");

    	proxy.registerRenderers();
    	proxy.registerRecipes();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
    	
    	BiomeDictionary.registerAllBiomes();
    	
    	CopiousDogsEntity.postInit();
    }
}
