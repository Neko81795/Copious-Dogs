package com.gitHub.copiousDogs.client;

import com.gitHub.copiousDogs.CommonProxy;
import com.gitHub.copiousDogs.mobs.Beagle;
import com.gitHub.copiousDogs.mobs.BerneseMountain;
import com.gitHub.copiousDogs.mobs.Chihuahua;
import com.gitHub.copiousDogs.mobs.Dalmatian;
import com.gitHub.copiousDogs.mobs.FrenchBullDog;
import com.gitHub.copiousDogs.mobs.GermanShepherd;
import com.gitHub.copiousDogs.mobs.GoldenRetriever;
import com.gitHub.copiousDogs.mobs.Husky;
import com.gitHub.copiousDogs.mobs.models.ModelBeagle;
import com.gitHub.copiousDogs.mobs.models.ModelBerneseMountainDog;
import com.gitHub.copiousDogs.mobs.models.ModelChihuahua;
import com.gitHub.copiousDogs.mobs.models.ModelDalmatian;
import com.gitHub.copiousDogs.mobs.models.ModelFrenchBulldog;
import com.gitHub.copiousDogs.mobs.models.ModelGermanShepherd;
import com.gitHub.copiousDogs.mobs.models.ModelGoldenRetriever;
import com.gitHub.copiousDogs.mobs.models.ModelHusky;
import com.gitHub.copiousDogs.render.blocks.DogDishRenderer;
import com.gitHub.copiousDogs.render.mobs.RenderBeagle;
import com.gitHub.copiousDogs.render.mobs.RenderBerneseMountain;
import com.gitHub.copiousDogs.render.mobs.RenderChihuahua;
import com.gitHub.copiousDogs.render.mobs.RenderDalmatian;
import com.gitHub.copiousDogs.render.mobs.RenderFrenchBulldog;
import com.gitHub.copiousDogs.render.mobs.RenderGermanShepherd;
import com.gitHub.copiousDogs.render.mobs.RenderGoldenRetriever;
import com.gitHub.copiousDogs.render.mobs.RenderHusky;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
	@SuppressWarnings("unused")
	private DogDishRenderer dogDishRenderer = new DogDishRenderer();

	@Override
	public void registerRenderers() {
		//TODO uncomment when render issue is fixed
		/*CopiousDogs.dogDishRendererID =
		RenderingRegistry.getNextAvailableRenderId();
		RenderingRegistry.registerBlockHandler(CopiousDogs.dogDishRendererID,
		dogDishRenderer);*/
		RenderingRegistry.registerEntityRenderingHandler(GoldenRetriever.class,
				new RenderGoldenRetriever(new ModelGoldenRetriever(), new ModelGoldenRetriever(), 0.6f));
		RenderingRegistry.registerEntityRenderingHandler(Husky.class,
				new RenderHusky(new ModelHusky(), new ModelHusky(), 0.6f));
		RenderingRegistry.registerEntityRenderingHandler(BerneseMountain.class,
				new RenderBerneseMountain(new ModelBerneseMountainDog(), new ModelBerneseMountainDog(), 0.8f));
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