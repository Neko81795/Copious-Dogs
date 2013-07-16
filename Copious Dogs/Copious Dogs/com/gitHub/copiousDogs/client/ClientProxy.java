package com.gitHub.copiousDogs.client;

import com.gitHub.copiousDogs.CommonProxy;
import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.render.blocks.DogDishRenderer;

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
	}
}