package com.gitHub.copiousDogs.client;

import com.gitHub.copiousDogs.CommonProxy;
import com.gitHub.copiousDogs.render.blocks.DogDishRenderer;

public class ClientProxy extends CommonProxy
{
	private DogDishRenderer dogDishRenderer = new DogDishRenderer();
	
	@Override
	public void registerRenderers()
	{
		//TODO uncomment when render issue is fixed
		//CopiousDogs.dogDishRendererID = RenderingRegistry.getNextAvailableRenderId();
		//RenderingRegistry.registerBlockHandler(CopiousDogs.dogDishRendererID, dogDishRenderer);
		//RenderingRegistry.registerEntityRenderingHandler(GoldenRetriever.class, new RenderGoldenRetriever(new ModelSheep1(), 1));
	}
}
