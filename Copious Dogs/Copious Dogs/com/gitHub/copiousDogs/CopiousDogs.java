package com.gitHub.copiousDogs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.Configuration;

import com.gitHub.copiousDogs.items.DogBiscuit;
import com.gitHub.copiousDogs.mobs.Dog;
import com.gitHub.copiousDogs.mobs.GoldenRetriever;
import com.gitHub.copiousDogs.items.DogDish;

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
	public static Item dogDish;
	
	@Instance("CopiousDogs")
	public static CopiousDogs instance;
	
	@SidedProxy(clientSide = "com.gitHub.copiousDogs.client.ClientProxy", 
			serverSide = "com.gitHub.copiousDogs.CommonProxy")
	public static CommonProxy proxy;
	

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
    	Reference.DOG_DISH_ID = config.getItem("Dog dish:", 17002).getInt();
    	Reference.EGG_GOLDEN_RETRIEVER_ID = config.getItem("Egg Golden Retriever:", 17003).getInt();
    	
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
    	GameRegistry.addRecipe(new ItemStack(dogBiscuit), " m ", "mbm", " m ", 'm', Item.porkRaw, 'b', Item.bone);
    	//
    	//Golden Retriever
    	//
    	EntityRegistry.registerModEntity(GoldenRetriever.class, "Golden Retriever", Reference.MOB_GOLDEN_RETRIEVER_ID, this, 40, 1, true);
    	EntityRegistry.addSpawn(GoldenRetriever.class, 10, 2, 6, EnumCreatureType.creature, BiomeGenBase.plains);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Golden Retriever.name", "Golden Retriever");
    	EntityList.IDtoClassMapping.put(Reference.EGG_GOLDEN_RETRIEVER_ID, GoldenRetriever.class);
    	EntityList.entityEggs.put(Reference.EGG_GOLDEN_RETRIEVER_ID,
    			new EntityEggInfo(Reference.EGG_GOLDEN_RETRIEVER_ID, 0xFFFFFF , 0x000000));
    	//
    	// Dog Dish
    	//
    	dogDish = new DogDish(Reference.DOG_DISH_ID);
    	LanguageRegistry.addName(dogDish, "Dog Dish");
    	GameRegistry.addRecipe(new ItemStack(dogDish), "III", "IBI", "III", 'I', Item.ingotIron, 'B', Item.bucketEmpty);
    }
   
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) 
    {
            // Stub Method
    }
}
