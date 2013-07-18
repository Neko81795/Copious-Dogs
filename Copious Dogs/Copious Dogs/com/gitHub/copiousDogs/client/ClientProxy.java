package com.gitHub.copiousDogs.client;

import com.gitHub.copiousDogs.CommonProxy;
import com.gitHub.copiousDogs.mobs.GoldenRetriever;
import com.gitHub.copiousDogs.mobs.Husky;
import com.gitHub.copiousDogs.mobs.models.ModelHusky;
import com.gitHub.copiousDogs.mobs.models.ModelGoldenRetriever;
import com.gitHub.copiousDogs.render.blocks.DogDishRenderer;
import com.gitHub.copiousDogs.render.mobs.RenderGoldenRetriever;
import com.gitHub.copiousDogs.render.mobs.RenderHusky;

import cpw.mods.fml.client.registry.RenderingRegistry;
public class ClientProxy extends CommonProxy
{
	private DogDishRenderer dogDishRenderer = new DogDishRenderer();
	
	@Override
	public void registerRenderers()
	{
		//TODO uncomment when render issue is fixed
		//CopiousDogs.dogDishRendererID = RenderingRegistry.getNextAvailableRenderId();
		//RenderingRegistry.registerBlockHandler(CopiousDogs.dogDishRendererID, dogDishRenderer);
		RenderingRegistry.registerEntityRenderingHandler(GoldenRetriever.class, new RenderGoldenRetriever(new ModelGoldenRetriever(), 1));
		RenderingRegistry.registerEntityRenderingHandler(Husky.class, new RenderHusky(new ModelHusky(), 1));
	}
}
