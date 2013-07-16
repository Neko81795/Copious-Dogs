package com.gitHub.copiousDogs.mobs;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.items.DogBiscuit;

import net.minecraft.block.BlockColored;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.world.World;

public class Dog extends EntityTameable
{	
	//this is a copy of net.minecraft.entity.passive.EntityWolf with modifications (and comments)
	private boolean collar;
	private boolean angry;
	private int collarColor;
	
	public Dog(World world)
	{
		super(world);
	}
	
	@Override
	public boolean interact(EntityPlayer player)
	{
		//get what the player is holding
		ItemStack itemstack = player.inventory.getCurrentItem();

        if (this.isTamed())
        {
        	//is the player holding anything?
            if (itemstack != null)
            {
            	//is the player holding a DogBiscuit?
                if (Item.itemsList[itemstack.itemID] instanceof DogBiscuit)
                {
                	DogBiscuit itemfood = (DogBiscuit)Item.itemsList[itemstack.itemID];

                    if (this.dataWatcher.func_111145_d(18) < 20.0F)//I have no idea what this does
                    {
                        if (!player.capabilities.isCreativeMode)//is the player in creative mode?
                        {
                            --itemstack.stackSize;
                        }
                        //heal the dog
                        this.heal(itemfood.getHealAmount());

                        if (itemstack.stackSize <= 0)//if the stack is empty, remove it from the inventory
                        {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                        }

                        return true;//did something! :D
                    }
                }
                else if (itemstack.itemID == Item.dyePowder.itemID)//is it a die?
                {
                	//get the die color
                    int i = BlockColored.getBlockFromDye(itemstack.getItemDamage());

                    if (i != this.getCollarColor())//does the die equal the current color?
                    {
                    	if(!player.capabilities.isCreativeMode)//is the player in creative mode?
                    	{
                    		--itemstack.stackSize;
                    	}
                        this.setCollarColor(i);

                        if (itemstack.stackSize <= 0)//if the stack is empty, remove it from the inventory
                        {
                            player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
                        }
                        
                        return true;// did something!
                    }
                }
            }

            if (player.getCommandSenderName().equalsIgnoreCase(this.getOwnerName()) && !this.worldObj.isRemote /*id this server side*/ && !this.isBreedingItem(itemstack))
            {
            	//sit boy sit
                this.aiSit.setSitting(!this.isSitting());
                this.isJumping = false;
                this.setPathToEntity((PathEntity)null);
                this.setTarget((Entity)null);
                this.setAttackTarget((EntityLivingBase)null);
            }
        }
        else if (itemstack != null && itemstack.itemID == CopiousDogs.dogBiscuit.itemID && !this.isAngry())
        {
            if (!player.capabilities.isCreativeMode)
            {
                --itemstack.stackSize;
            }

            if (itemstack.stackSize <= 0)
            {
                player.inventory.setInventorySlotContents(player.inventory.currentItem, (ItemStack)null);
            }

            if (!this.worldObj.isRemote)//is this server side?
            {
                if (this.rand.nextInt(3) == 0)
                {
                    this.setTamed(true);
                    this.setPathToEntity((PathEntity)null);
                    this.setAttackTarget((EntityLivingBase)null);
                    this.aiSit.setSitting(true);
                    this.setEntityHealth(20.0F);
                    this.setOwner(player.getCommandSenderName());
                    this.playTameEffect(true);
                    this.worldObj.setEntityState(this, (byte)7);
                }
                else
                {
                    this.playTameEffect(false);
                    this.worldObj.setEntityState(this, (byte)6);
                }
            }

            return true;
        }

        return super.interact(player);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entityageable)
	{
		return null;
	}

	public boolean isAngry()
	{
		return angry;
	}
	
	public int getCollarColor()
	{
		return collarColor;
	}
	
	public void setCollarColor(int color)
	{
		//0 <= collarColor <= 15 always
		collarColor = color < 0 ? 0 : (color > 15 ? 15 : color);
	}

	public boolean hasCollar()
	{
		return collar;
	}
}