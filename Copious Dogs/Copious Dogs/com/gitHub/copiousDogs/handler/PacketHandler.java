package com.gitHub.copiousDogs.handler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gitHub.copiousDogs.blocks.tileentity.TileEntityDogDish;
import com.gitHub.copiousDogs.lib.Reference;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.IPacketHandler;
import cpw.mods.fml.common.network.Player;
import cpw.mods.fml.relauncher.Side;

public class PacketHandler implements IPacketHandler {
	
	@Override
	public void onPacketData(INetworkManager manager,
			Packet250CustomPayload packet, Player player) {
		
		if (packet.channel.equals(Reference.CHANNEL_NAME)) {
			
			identifyPacket(packet, player);
		}
	}
	
	/**
	 * 
	 * Identifies the given packet to know how to handle it.
	 * 
	 * @param packet
	 */
	private void identifyPacket(Packet250CustomPayload packet, Player player) {
		
		DataInputStream stream = new DataInputStream(new ByteArrayInputStream(packet.data));
		
		try {
			
			int id = stream.readInt();
			
			switch(id) {
			
			case 0: handleTileEntityPacket(packet, player, stream);
			default: return;
			}
			
			
		} catch (IOException e) {
			
			System.err.println("Invalid packet recieved.");
		}	
	}
	
	private void handleTileEntityPacket(Packet250CustomPayload packet, Player player, DataInputStream stream) {
		
		int x = 0;
		int y = 0;
		int z = 0;
		
		int food = 0;
		try {
			
			x = stream.readInt();
			y = stream.readInt();
			z = stream.readInt();
			
			food = stream.readInt();
			
		} catch (IOException e) {

			
			e.printStackTrace();
		}
		
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			
			World world = ((EntityPlayer)player).worldObj;
			
			if (world.blockExists(x, y, z)) {
				
				TileEntity te = world.getBlockTileEntity(x, y, z);
				
				if (te != null && te instanceof TileEntityDogDish) {
					
					TileEntityDogDish dish = (TileEntityDogDish) te;
					
					dish.setFoodLevel(food);
					dish.onInventoryChanged();
					world.markBlockForRenderUpdate(x, y, z);
				}
			}
		}
		else if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER) {
			
			World world = ((EntityPlayerMP) player).worldObj;
			
			if (world.blockExists(x, y, z)) {
				
				TileEntity te = world.getBlockTileEntity(x, y, z);
				
				if (te != null && te instanceof TileEntityDogDish) {
					
					TileEntityDogDish dish = (TileEntityDogDish) te;
					
					dish.setFoodLevel(food);
					dish.onInventoryChanged();
				}
			}
		}
	}
}
