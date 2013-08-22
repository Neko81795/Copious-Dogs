package com.gitHub.copiousDogs.handler;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.network.INetworkManager;
import net.minecraft.network.packet.Packet250CustomPayload;

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
			
			case 0: handleEntityItemSpawnPacket(packet, player, stream);
			default: return;
			}
			
			
		} catch (IOException e) {
			
			System.err.println("Invalid packet recieved.");
		}	
	}
	
	private void handleEntityItemSpawnPacket(Packet250CustomPayload packet, Player player, DataInputStream stream) {
		
		Side side = FMLCommonHandler.instance().getEffectiveSide();
		
		if (side == Side.SERVER) {
			
			EntityPlayerMP mpPlayer = (EntityPlayerMP) player;
			
			try {
				
				float x = stream.readFloat();
				float y = stream.readFloat();
				float z = stream.readFloat();
				float motionX = stream.readFloat();
				float motionY = stream.readFloat();
				float motionZ = stream.readFloat();
				int itemID = stream.readInt();
				int amount = stream.readInt();
				int damage = stream.readInt();
				
				EntityItem item = new EntityItem(mpPlayer.worldObj, x, y, z, new ItemStack(itemID, amount, damage));
				item.motionX = motionX;
				item.motionY = motionY;
				item.motionZ = motionZ;
				
				mpPlayer.worldObj.spawnEntityInWorld(item);
				
			} catch (IOException e) {
				
				e.printStackTrace();
			}
		}
	}
}
