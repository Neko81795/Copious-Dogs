package com.gitHub.copiousDogs.client;

import net.minecraftforge.client.MinecraftForgeClient;

import com.gitHub.copiousDogs.CommonProxy;
import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.blocks.tileentity.TileEntityDogDish;
import com.gitHub.copiousDogs.client.model.entity.ModelBeagle;
import com.gitHub.copiousDogs.client.model.entity.ModelChihuahua;
import com.gitHub.copiousDogs.client.model.entity.ModelDalmatian;
import com.gitHub.copiousDogs.client.model.entity.ModelFrenchBulldog;
import com.gitHub.copiousDogs.client.model.entity.ModelGermanShepherd;
import com.gitHub.copiousDogs.client.model.entity.ModelGoldenRetriever;
import com.gitHub.copiousDogs.client.model.entity.ModelHusky;
import com.gitHub.copiousDogs.client.render.entity.RenderBeagle;
import com.gitHub.copiousDogs.client.render.entity.RenderChihuahua;
import com.gitHub.copiousDogs.client.render.entity.RenderDalmatian;
import com.gitHub.copiousDogs.client.render.entity.RenderFrenchBulldog;
import com.gitHub.copiousDogs.client.render.entity.RenderGermanShepherd;
import com.gitHub.copiousDogs.client.render.entity.RenderGoldenRetriever;
import com.gitHub.copiousDogs.client.render.entity.RenderHusky;
import com.gitHub.copiousDogs.client.render.items.ItemDogDishRenderer;
import com.gitHub.copiousDogs.client.render.tileentity.TileEntityDogDishRenderer;
import com.gitHub.copiousDogs.entity.Beagle;
import com.gitHub.copiousDogs.entity.Chihuahua;
import com.gitHub.copiousDogs.entity.Dalmatian;
import com.gitHub.copiousDogs.entity.FrenchBullDog;
import com.gitHub.copiousDogs.entity.GermanShepherd;
import com.gitHub.copiousDogs.entity.GoldenRetriever;
import com.gitHub.copiousDogs.entity.Husky;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityDogDish.class, new TileEntityDogDishRenderer());
		MinecraftForgeClient.registerItemRenderer(CopiousDogs.dogDish.blockID, new ItemDogDishRenderer());
		RenderingRegistry.registerEntityRenderingHandler(GoldenRetriever.class,
				new RenderGoldenRetriever(new ModelGoldenRetriever(), new ModelGoldenRetriever(), 0.6f));
		RenderingRegistry.registerEntityRenderingHandler(Husky.class,
				new RenderHusky(new ModelHusky(), new ModelHusky(), 0.6f));
		RenderingRegistry.registerEntityRenderingHandler(Chihuahua.class,
				new RenderChihuahua(new ModelChihuahua(), new ModelChihuahua(), 0.2f));
		RenderingRegistry.registerEntityRenderingHandler(FrenchBullDog.class,
				new RenderFrenchBulldog(new ModelFrenchBulldog(), new ModelFrenchBulldog(), 0.4f));
		RenderingRegistry.registerEntityRenderingHandler(GermanShepherd.class,
				new RenderGermanShepherd(new ModelGermanShepherd(), new ModelGermanShepherd(), 0.6f));
		RenderingRegistry.registerEntityRenderingHandler(Dalmatian.class,
				new RenderDalmatian(new ModelDalmatian(), new ModelDalmatian(), 0.6f));
		RenderingRegistry.registerEntityRenderingHandler(Beagle.class, 
				new RenderBeagle(new ModelBeagle(), new ModelBeagle(), 0.6F));
	}
}