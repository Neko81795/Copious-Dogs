package com.gitHub.copiousDogs.configuration;

import net.minecraft.entity.EntityEggInfo;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.entity.Beagle;
import com.gitHub.copiousDogs.entity.BerneseMountain;
import com.gitHub.copiousDogs.entity.Chihuahua;
import com.gitHub.copiousDogs.entity.Dalmatian;
import com.gitHub.copiousDogs.entity.FrenchBullDog;
import com.gitHub.copiousDogs.entity.GermanShepherd;
import com.gitHub.copiousDogs.entity.GoldenRetriever;
import com.gitHub.copiousDogs.entity.Husky;
import com.gitHub.copiousDogs.lib.Reference;

import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class CopiousDogsEntity {

	public static void init() {
		
    	//
    	//Golden Retriever
    	//
    	EntityRegistry.registerModEntity(GoldenRetriever.class, "Golden Retriever", Reference.MOB_GOLDEN_RETRIEVER_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Golden Retriever.name", "Golden Retriever");
    	EntityList.IDtoClassMapping.put(Reference.EGG_GOLDEN_RETRIEVER_ID, GoldenRetriever.class);
    	EntityList.entityEggs.put(Reference.EGG_GOLDEN_RETRIEVER_ID,
    			new EntityEggInfo(Reference.EGG_GOLDEN_RETRIEVER_ID, 0xbc8e5f , 0xddcdb6));
    	//
    	//Husky
    	//
    	EntityRegistry.registerModEntity(Husky.class, "Husky", Reference.MOB_HUSKY_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Husky.name", "Husky");
    	EntityList.IDtoClassMapping.put(Reference.EGG_HUSKY_ID, Husky.class);
    	EntityList.entityEggs.put(Reference.EGG_HUSKY_ID,
    			new EntityEggInfo(Reference.EGG_HUSKY_ID, 0x2b2e2d , 0x7e807d));
    	//
    	//Bernese Mountain
    	//
    	EntityRegistry.registerModEntity(BerneseMountain.class, "Bernese Mountain", Reference.MOB_BERNESE_MOUNTAIN_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Bernese Mountain.name", "Bernese Mountain");
    	EntityList.IDtoClassMapping.put(Reference.EGG_BERNESE_MOUNTAIN_ID, BerneseMountain.class);
    	EntityList.entityEggs.put(Reference.EGG_BERNESE_MOUNTAIN_ID,
    			new EntityEggInfo(Reference.EGG_BERNESE_MOUNTAIN_ID, 0x04070e , 0x723f21));
    	//
    	//Chihuahua
    	//
    	EntityRegistry.registerModEntity(Chihuahua.class, "Chihuahua", Reference.MOB_CHIHUAHUA_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Chihuahua.name", "Chihuahua");
    	EntityList.IDtoClassMapping.put(Reference.EGG_CHIHUAHUA_ID, Chihuahua.class);
    	EntityList.entityEggs.put(Reference.EGG_CHIHUAHUA_ID,
    			new EntityEggInfo(Reference.EGG_CHIHUAHUA_ID, 0xc7a087 , 0x9e7f6b));
    	//
    	//French Bulldog
    	//
    	EntityRegistry.registerModEntity(FrenchBullDog.class, "French Bulldog", Reference.MOB_FRENCH_BULLDOG_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.French Bulldog.name", "French Bulldog");
    	EntityList.IDtoClassMapping.put(Reference.EGG_FRENCH_BULLDOG_ID, FrenchBullDog.class);
    	EntityList.entityEggs.put(Reference.EGG_FRENCH_BULLDOG_ID,
    			new EntityEggInfo(Reference.EGG_FRENCH_BULLDOG_ID, 0x151618 , 0xbdbdb7));
    	//
    	//GermanShepherd
    	//
    	EntityRegistry.registerModEntity(GermanShepherd.class, "German Shepherd", Reference.MOB_GERMAN_SHEPHERD_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.German Shepherd.name", "German Shepherd");
    	EntityList.IDtoClassMapping.put(Reference.EGG_GERMAN_SHEPHERD_ID, GermanShepherd.class);
    	EntityList.entityEggs.put(Reference.EGG_GERMAN_SHEPHERD_ID,
    			new EntityEggInfo(Reference.EGG_GERMAN_SHEPHERD_ID, 0xad754f , 0x17141b));
    	//
    	//Dalmatian
    	//
    	EntityRegistry.registerModEntity(Dalmatian.class, "Dalmatian", Reference.MOB_DALMATIAN_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Dalmatian.name", "Dalmatian");
    	EntityList.IDtoClassMapping.put(Reference.EGG_DALMATIAN_ID, Dalmatian.class);
    	EntityList.entityEggs.put(Reference.EGG_DALMATIAN_ID,
    			new EntityEggInfo(Reference.EGG_DALMATIAN_ID, 0xFFFFFF , 0x000000));
    	
    	//Beagle
    	EntityRegistry.registerModEntity(Beagle.class, "Beagle", Reference.MOB_BEAGLE_ID, CopiousDogs.class, 40, 1, true);
    	LanguageRegistry.instance().addStringLocalization("entity.CopiousDogs.Beagle.name", "Beagle");
    	EntityList.IDtoClassMapping.put(Reference.EGG_BEAGLE_ID, Beagle.class);
    	EntityList.entityEggs.put(Reference.EGG_BEAGLE_ID, 
    			new EntityEggInfo(Reference.EGG_BEAGLE_ID, 0xCE935F, 0x685043));
	}
	
	public static void postInit() {
		
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
