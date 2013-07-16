package com.gitHub.copiousDogs.client;

import net.minecraft.client.model.ModelSheep1;
import net.minecraft.client.model.ModelWolf;

import com.gitHub.copiousDogs.CommonProxy;
import com.gitHub.copiousDogs.mobs.GoldenRetriever;
import com.gitHub.copiousDogs.mobs.renderers.RenderGoldenRetriever;

import cpw.mods.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy
{
	@Override
	public void registerRenderers()
	{
		//RenderingRegistry.registerEntityRenderingHandler(GoldenRetriever.class, new RenderGoldenRetriever(new ModelSheep1(), 1));
	}
}