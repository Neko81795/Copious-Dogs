package com.gitHub.copiousDogs;

import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;

import com.gitHub.copiousDogs.blocks.BlockDogDish;
import com.gitHub.copiousDogs.blocks.tileentities.TileEntityDogDish;
import com.gitHub.copiousDogs.items.DogBiscuit;
import com.gitHub.copiousDogs.items.DogCollar;
import com.gitHub.copiousDogs.mobs.GoldenRetriever;
import com.gitHub.copiousDogs.mobs.Husky;

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
	public static Item dogCollar;
	
	//
	//Materials
	//
	public static final Material plate = (new Material(MapColor.ironColor));
	
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
    	Reference.DOG_COLLAR_ID = config.getItem("Dog collar", 17003).getInt();
    	Reference.EGG_HUSKY_ID = config.getItem("Egg Husky:", 17004).getInt();
    	
    	//reads the config file for block ids
    	Reference.DOG_DISH_ID = config.getBlock("Dog dish:", 1701).getInt();
    	
    	config.save();
    }
   
    @SuppressWarnings("unchecked")
	@EventHandler
    public void load(FMLInitializationEvent event) 
    {
    	proxy.registerRenderers();
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
    	//Golden Retriever
    	//
    	EntityRegistry.registerModEntity(GoldenRetriever.class, "Golden Retriever", Reference.MOB_GOLDEN_RETRIEVER_ID, this, 40, 1, true);
    	EntityRegistry.addSpawn(GoldenRetriever.class, 2, 2, 6, EnumCreatureType.creature, BiomeGenBase.plains);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Golden Retriever.name", "Golden Retriever");
    	EntityList.IDtoClassMapping.put(Reference.EGG_GOLDEN_RETRIEVER_ID, GoldenRetriever.class);
    	EntityList.entityEggs.put(Reference.EGG_GOLDEN_RETRIEVER_ID,
    			new EntityEggInfo(Reference.EGG_GOLDEN_RETRIEVER_ID, 0xbc8e5f , 0xddcdb6));
    	//
    	//Husky
    	//
    	EntityRegistry.registerModEntity(Husky.class, "Husky", Reference.MOB_HUSKY_ID, this, 40, 1, true);
    	EntityRegistry.addSpawn(Husky.class, 1, 2, 8, EnumCreatureType.creature, BiomeGenBase.icePlains, BiomeGenBase.taiga,
    			BiomeGenBase.taigaHills, BiomeGenBase.iceMountains);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Husky.name", "Husky");
    	EntityList.IDtoClassMapping.put(Reference.EGG_HUSKY_ID, Husky.class);
    	EntityList.entityEggs.put(Reference.EGG_HUSKY_ID,
    			new EntityEggInfo(Reference.EGG_HUSKY_ID, 0x2b2e2d , 0x7e807d));
    	//
    	// Dog Dish
    	//
    	dogDish = new BlockDogDish(Reference.DOG_DISH_ID);
    	GameRegistry.registerBlock(dogDish, "dogDish");
    	GameRegistry.registerTileEntity(TileEntityDogDish.class, "dog_dish_entity");
    	LanguageRegistry.addName(dogDish, "Dog Dish");
    	
    	registerRecipes();
    	proxy.registerRenderers();
    }
   
    public void registerRecipes() {
    	
    	GameRegistry.addRecipe(new ItemStack(dogBiscuit), " m ", "mbm", " m ", 'm', Item.porkRaw, 'b', Item.bone);
    	GameRegistry.addRecipe(new ItemStack(dogDish), "III", "IBI", "III", 'I', Item.ingotIron, 'B', Item.bucketEmpty);
    	GameRegistry.addRecipe(new ItemStack(dogCollar), "SSS", "S S", "SSI", 'S', Item.silk, 'I', Item.ingotIron);
    	
    	for (int i = 0; i < 16; i++) {
    		
    		GameRegistry.addShapelessRecipe(new ItemStack(dogDish, 1, i), new ItemStack(Item.dyePowder, 1, i), dogDish);
    	}
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
            // Stub Method
    }
}
