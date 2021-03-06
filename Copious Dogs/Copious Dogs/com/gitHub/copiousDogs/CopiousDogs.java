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
    	
    	//
    	//Golden Retriever
    	//
    	EntityRegistry.registerModEntity(GoldenRetriever.class, "Golden Retriever", Reference.MOB_GOLDEN_RETRIEVER_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Golden Retriever.name", "Golden Retriever");
    	EntityList.IDtoClassMapping.put(Reference.EGG_GOLDEN_RETRIEVER_ID, GoldenRetriever.class);
    	EntityList.entityEggs.put(Reference.EGG_GOLDEN_RETRIEVER_ID,
    			new EntityEggInfo(Reference.EGG_GOLDEN_RETRIEVER_ID, 0xbc8e5f , 0xddcdb6));
    	//
    	//Husky
    	//
    	EntityRegistry.registerModEntity(Husky.class, "Husky", Reference.MOB_HUSKY_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Husky.name", "Husky");
    	EntityList.IDtoClassMapping.put(Reference.EGG_HUSKY_ID, Husky.class);
    	EntityList.entityEggs.put(Reference.EGG_HUSKY_ID,
    			new EntityEggInfo(Reference.EGG_HUSKY_ID, 0x2b2e2d , 0x7e807d));
    	//
    	//Bernese Mountain
    	//
    	EntityRegistry.registerModEntity(BerneseMountain.class, "Bernese Mountain", Reference.MOB_BERNESE_MOUNTAIN_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Bernese Mountain.name", "Bernese Mountain");
    	EntityList.IDtoClassMapping.put(Reference.EGG_BERNESE_MOUNTAIN_ID, BerneseMountain.class);
    	EntityList.entityEggs.put(Reference.EGG_BERNESE_MOUNTAIN_ID,
    			new EntityEggInfo(Reference.EGG_BERNESE_MOUNTAIN_ID, 0x04070e , 0x723f21));
    	//
    	//Chihuahua
    	//
    	EntityRegistry.registerModEntity(Chihuahua.class, "Chihuahua", Reference.MOB_CHIHUAHUA_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Chihuahua.name", "Chihuahua");
    	EntityList.IDtoClassMapping.put(Reference.EGG_CHIHUAHUA_ID, Chihuahua.class);
    	EntityList.entityEggs.put(Reference.EGG_CHIHUAHUA_ID,
    			new EntityEggInfo(Reference.EGG_CHIHUAHUA_ID, 0xc7a087 , 0x9e7f6b));
    	//
    	//French Bulldog
    	//
    	EntityRegistry.registerModEntity(FrenchBullDog.class, "French Bulldog", Reference.MOB_FRENCH_BULLDOG_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.French Bulldog.name", "French Bulldog");
    	EntityList.IDtoClassMapping.put(Reference.EGG_FRENCH_BULLDOG_ID, FrenchBullDog.class);
    	EntityList.entityEggs.put(Reference.EGG_FRENCH_BULLDOG_ID,
    			new EntityEggInfo(Reference.EGG_FRENCH_BULLDOG_ID, 0x151618 , 0xbdbdb7));
    	//
    	//GermanShepherd
    	//
    	EntityRegistry.registerModEntity(GermanShepherd.class, "German Shepherd", Reference.MOB_GERMAN_SHEPHERD_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.German Shepherd.name", "German Shepherd");
    	EntityList.IDtoClassMapping.put(Reference.EGG_GERMAN_SHEPHERD_ID, GermanShepherd.class);
    	EntityList.entityEggs.put(Reference.EGG_GERMAN_SHEPHERD_ID,
    			new EntityEggInfo(Reference.EGG_GERMAN_SHEPHERD_ID, 0xad754f , 0x17141b));
    	//
    	//Dalmatian
    	//
    	EntityRegistry.registerModEntity(Dalmatian.class, "Dalmatian", Reference.MOB_DALMATIAN_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Dalmatian.name", "Dalmatian");
    	EntityList.IDtoClassMapping.put(Reference.EGG_DALMATIAN_ID, Dalmatian.class);
    	EntityList.entityEggs.put(Reference.EGG_DALMATIAN_ID,
    			new EntityEggInfo(Reference.EGG_DALMATIAN_ID, 0xFFFFFF , 0x000000));
    	
    	//Beagle
    	EntityRegistry.registerModEntity(Beagle.class, "Beagle", Reference.MOB_BEAGLE_ID, this, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Beagle.name", "Beagle");
    	EntityList.IDtoClassMapping.put(Reference.EGG_BEAGLE_ID, Beagle.class);
    	EntityList.entityEggs.put(Reference.EGG_BEAGLE_ID, 
    			new EntityEggInfo(Reference.EGG_BEAGLE_ID, 0xCE935F, 0x685043));
    	
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
    	
    	BiomeGenBase plains[] = BiomeDictionary.getBiomesForType(Type.PLAINS);
    	
    	for (int i = 0; i < plains.length; i++) {
    		
    		EntityRegistry.addSpawn(GermanShepherd.class, 15, 2, 6, EnumCreatureType.creature, plains[i]);
    		EntityRegistry.addSpawn(Chihuahua.class, 15, 2, 8, EnumCreatureType.creature, plains[i]);
        	EntityRegistry.addSpawn(FrenchBullDog.class, 15, 2, 8, EnumCreatureType.creature, plains[i]);
        	EntityRegistry.addSpawn(GoldenRetriever.class, 15, 2, 6, EnumCreatureType.creature, plains[i]);
    	}
    	
    	BiomeGenBase forests[] = BiomeDictionary.getBiomesForType(Type.FOREST);
    	
    	for (int i = 0; i < forests.length; i++) {
    		
        	EntityRegistry.addSpawn(Beagle.class, 15, 2, 6, EnumCreatureType.creature, forests[i]);
    		EntityRegistry.addSpawn(Dalmatian.class, 15, 2, 6, EnumCreatureType.creature, forests[i]);
    	}
    	
    	BiomeGenBase hills[] = BiomeDictionary.getBiomesForType(Type.HILLS);
    	
    	for (int i = 0; i < hills.length; i++) {
    		
        	EntityRegistry.addSpawn(BerneseMountain.class, 15, 2, 8, EnumCreatureType.creature, hills[i]);
    	}
    	
    	BiomeGenBase frozen[] = BiomeDictionary.getBiomesForType(Type.FROZEN);
    	
    	for (int i = 0; i < frozen.length; i++) {
    		
    		EntityRegistry.addSpawn(Husky.class, 15, 2, 8, EnumCreatureType.creature, frozen[i]);
    	}
    }
}
