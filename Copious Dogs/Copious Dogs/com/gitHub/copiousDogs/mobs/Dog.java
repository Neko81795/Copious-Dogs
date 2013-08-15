package com.gitHub.copiousDogs.mobs;

import java.lang.reflect.Constructor;
import java.util.Random;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.gitHub.copiousDogs.CopiousDogs;
import com.gitHub.copiousDogs.items.DogCollar;
import com.gitHub.copiousDogs.mobs.ai.EntityAIBegBiscuit;
import com.gitHub.copiousDogs.mobs.ai.EntityAIEatDogDish;
import com.gitHub.copiousDogs.mobs.ai.EntityAIFollowOwnerLeashed;
import com.gitHub.copiousDogs.mobs.ai.EntityAILieDown;
import com.gitHub.copiousDogs.mobs.ai.EntityAIMateNearTorch;

public class Dog extends EntityTameable
{
	
	protected static float moveSpeed;
	
	/**
	 * A value describing how close this dog is to getting tamed.
	 * 10 is tamed.
	 */
	
	public boolean hasCollar() {
		
		return (this.dataWatcher.getWatchableObjectByte(18) & 4) != 0;
	}
	
	public boolean isTailAnimated() {
		
		return (this.dataWatcher.getWatchableObjectByte(18) & 1) != 0;
	}
	
	public boolean isBegging() {
		
		return (this.dataWatcher.getWatchableObjectByte(18) & 2) != 0;
	}
	
	public boolean isLeashed() {
		
		return (this.dataWatcher.getWatchableObjectByte(18) & 8) != 0;
	}
	
	public boolean isEating() {
		
		return (this.dataWatcher.getWatchableObjectByte(18) & 16) != 0;
	}
	
	public boolean shouldRoamNearDogDish() {
		
		return (this.dataWatcher.getWatchableObjectByte(18) & 32) != 0;
	}
	
	public void setHasCollar(boolean par0) {
		
		if (this.hasCollar() != par0) { 
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 4:-4)));
		}
	}
	
	public void setTailAnimated(boolean par0) {
		
		if (this.isTailAnimated() != par0) {
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 1:-1)));
		}
	}
	
	public void setBegging(boolean par0) {
		
		if (this.isBegging() != par0) {
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 2:-2)));
		}
	}
	
	public void setLeashed(boolean par0) {
		
		if (this.isLeashed() != par0) {
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 8:-8)));
		}
	}
	
	public void setEating(boolean par0) {
		
		if (this.isEating() != par0) {
			this.dataWatcher.updateObject(18, (byte) (this.dataWatcher.getWatchableObjectByte(18) + (par0 ? 16: -16)));
		}
	}
	
	@Override
	public void onDeath(DamageSource par1DamageSource) {
		
		if (hasCollar() && !this.worldObj.isRemote) {
			
			Random rand = this.getRNG();
				
			ItemStack collar = new ItemStack(CopiousDogs.dogCollar.itemID, 1, DogCollar.getItemFromDye(getCollarColor()));
			EntityItem item = this.entityDropItem(collar, 1F);
			item.motionY += rand.nextFloat() * .5F;
			item.motionX += (rand.nextFloat() - rand.nextFloat()) * .1F;
			item.motionZ += (rand.nextFloat() - rand.nextFloat()) * .1F;
			
			this.worldObj.spawnEntityInWorld(item);
		}
	}
	
	public float getMaxHealth() {
		
		return 15;
	}
	
	public byte getCollarColor() {
		
		return this.dataWatcher.getWatchableObjectByte(21);
	}
	
	public void setCollarColor(byte par0) {
		
		this.dataWatcher.updateObject(21, par0);
	}
	
	@Override
	protected int getDropItemId()
	{
		return -1;
	}
	
	@Override
	protected String getLivingSound()
    {
        return "mob.wolf.bark";
    }

	@Override
    protected String getHurtSound()
    {
        return "mob.wolf.hurt";
    }

	@Override
    protected String getDeathSound()
    {
        return "mob.wolf.death";
    }

	@Override
    protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.worldObj.playSoundAtEntity(this, "mob.wolf.step", 0.15F, 1.0F);
    }

	@Override
	protected boolean isAIEnabled()
    {
        return true;
    }
	
	@Override
	protected void updateAITick() {
		
		this.dataWatcher.updateObject(19, new Float(this.func_110143_aJ()));
		
		
	}
	
	@Override
	protected void entityInit() {
		
		super.entityInit();
		this.dataWatcher.addObject(18, (byte)0);
		this.dataWatcher.addObject(19, new Float(this.func_110143_aJ()));
		this.dataWatcher.addObject(20, (byte)0);
		this.dataWatcher.addObject(21, (byte)-1);
	}
	
	public Dog(World world, float moveSpeed) {
		
		super(world);
		this.moveSpeed = moveSpeed;
		this.getNavigator().setAvoidsWater(true);
		this.tasks.addTask(0, new EntityAISwimming(this));
		
		//TODO make this work
		//this.tasks.addTask(1, new EntityAILieDown(this));
		//
		
		this.tasks.addTask(2, new EntityAIMateNearTorch(this, 0.4F, 10F));
		this.tasks.addTask(3, new EntityAILeapAtTarget(this, 0.4F));
		this.tasks.addTask(4, new EntityAIAttackOnCollide(this, .75F, true));
		this.tasks.addTask(5, new EntityAIEatDogDish(this, 20F));
		this.tasks.addTask(6, new EntityAIFollowOwnerLeashed(this, moveSpeed, 5.0F, 2.0F));
		this.tasks.addTask(7, new EntityAIWander(this, moveSpeed));
		this.tasks.addTask(8, new EntityAIBegBiscuit(this, 2F));
    	this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 0F));
    	this.tasks.addTask(9, new EntityAILookIdle(this));
    	
    	this.setEntityHealth(getMaxHealth());
	}
	
	@Override
	public boolean getAlwaysRenderNameTag() {
		
		return isTamed() && hasCustomNameTag();
	}

	@Override
	public void setCustomNameTag(String par1Str) {
		
		if (isTamed()) {
			super.setCustomNameTag(par1Str);
		}
	}
	
	public void setTamed(boolean par1)
	{
		super.setTamed(par1);

		if (par1)
		{
			this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(20.0D);
    	}
		else
		{	
			this.func_110148_a(SharedMonsterAttributes.field_111267_a).func_111128_a(8.0D);
		}
		
		setTailAnimated(true);
	}
	
	public void tryToTame(EntityPlayer player) {
		
		if (getTameValue() >= 10) {
			
			setTamed(true);
			setOwner(player.getEntityName());
			playTameEffect(true);
		
		}
		else {
					
			playTameEffect(false);
		}
	}
	
	public void setTameValue(byte par0) {
		
		this.dataWatcher.updateObject(20, par0);
	}
	
	public byte getTameValue() {
		
		return this.dataWatcher.getWatchableObjectByte(20);
	}

	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) 
	{
		
		if (this.worldObj.isRemote) {
			
			ItemStack stack = par1EntityPlayer.getCurrentEquippedItem();
			
			if (stack != null && this.isBreedingItem(stack) && this.getGrowingAge() == 0 && this.inLove <= 0 && this.isTamed()
					&& this.getOwnerName().equalsIgnoreCase(par1EntityPlayer.getEntityName()))
	        {
	            if (!par1EntityPlayer.capabilities.isCreativeMode)
	            {
	                --stack.stackSize;

	                if (stack.stackSize <= 0)
	                {
	                    par1EntityPlayer.inventory.setInventorySlotContents(par1EntityPlayer.inventory.currentItem, (ItemStack)null);
	                }
	            }

	            this.func_110196_bT();
	            return true;
	        }
			
			if (stack == null) {
				if (isSitting()) {
					this.setSitting(false);
					return true;
				}
				else {
			
					setSitting(true);
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack par1ItemStack) {

		return par1ItemStack.itemID == Item.cookie.itemID;
	}

	@Override
	public EntityAgeable createChild(EntityAgeable entity) 
	{
		try
		{
			Class<?> newClass = Class.forName(entity.getClass().getName());
			Constructor<?> construct = newClass.getConstructor(World.class);
			return (EntityAgeable) construct.newInstance(entity.worldObj);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound par1nbtTagCompound) {
		
		super.writeEntityToNBT(par1nbtTagCompound);
		
		par1nbtTagCompound.setBoolean("TailAnimated", isTailAnimated());
		par1nbtTagCompound.setBoolean("Begging", isBegging());
		par1nbtTagCompound.setBoolean("Leashed", isLeashed());
		
		NBTTagCompound collar = new NBTTagCompound();
		
		collar.setBoolean("HasCollar", hasCollar());
		collar.setByte("CollarColor", getCollarColor());
		
		par1nbtTagCompound.setCompoundTag("Collar", collar);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound par1nbtTagCompound) {
		
		super.readEntityFromNBT(par1nbtTagCompound);
		
		setTailAnimated(par1nbtTagCompound.getBoolean("TailAnimated"));
		setBegging(par1nbtTagCompound.getBoolean("Begging"));
		setLeashed(par1nbtTagCompound.getBoolean("Leashed"));
		
		NBTTagCompound collar = par1nbtTagCompound.getCompoundTag("Collar");
		
		setHasCollar(collar.getBoolean("HasCollar"));
		setCollarColor(collar.getByte("CollarColor"));
	}
}
