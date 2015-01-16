package machir.emitters.tileentity;

import machir.emitters.util.ModConstants;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityBubbleFX;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityHeartFX;
import net.minecraft.client.particle.EntityLavaFX;
import net.minecraft.client.particle.EntityNoteFX;
import net.minecraft.client.particle.EntityReddustFX;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.particle.EntitySplashFX;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEmitter extends TileEntity {
	public static enum Particle {
		BUBBLE				(ModConstants.PREFIX + "particle.bubble"),
		FLAME				(ModConstants.PREFIX + "particle.flame"),
		HEART				(ModConstants.PREFIX + "particle.heart"),
		LAVA				(ModConstants.PREFIX + "particle.lava"),
		NOTE				(ModConstants.PREFIX + "particle.note"),
		SPLASH				(ModConstants.PREFIX + "particle.splash"),
		REDDUST				(ModConstants.PREFIX + "particle.reddust"),
		SMOKE				(ModConstants.PREFIX + "particle.smoke");
		
		// Keep track of the name and index of each particle type
		public String name;
		
		private Particle(String name) {
			this.name = name;
		}
	}
	private Particle particle = Particle.SMOKE;
	
	private float xOffset = 0f;
	private float yOffset = 0f;
	private float zOffset = 0f;
	
	private float xVel = 0f;
	private float yVel = 0f;
	private float zVel = 0f;
	
	private float scaleMultiplier = 1f;
	private ItemStack itemStack = null;
	
	@Override
    public void updateEntity() {
		// Prepare the proper particle settings
    	switch (ForgeDirection.getOrientation(this.blockMetadata)) {
    	case UP:
    	default:
    		this.xOffset = 0.5f;
    		this.yOffset = 0.3f;
    		this.zOffset = 0.5f;
    		this.yVel    = 0.1f;
    		this.particle = Particle.SMOKE;
    		break;
    	case DOWN:
    		this.xOffset = 0.5f;
    		this.yOffset = 0.8f;
    		this.zOffset = 0.5f;
    		this.yVel    = -0.1f;
    		this.particle = Particle.REDDUST;
    		break;
    	case NORTH:
    		this.xOffset = 0.5f;
    		this.yOffset = 0.5f;
    		this.zOffset = 0.7f;
    		this.zVel    = -0.1f;
    		this.particle = Particle.NOTE;
    		break;
    	case SOUTH:
    		this.xOffset = 0.5f;
    		this.yOffset = 0.5f;
    		this.zOffset = 0.3f;
    		this.zVel    = 0.1f;
    		this.particle = Particle.BUBBLE;
    		break;
    	case WEST:
    		this.xOffset = 0.7f;
    		this.yOffset = 0.5f;
    		this.zOffset = 0.5f;
    		this.xVel    = -0.1f;
    		this.particle = Particle.FLAME;
    		break;
    	case EAST:
    		this.xOffset = 0.3f;
    		this.yOffset = 0.5f;
    		this.zOffset = 0.5f;
    		this.xVel    = 0.1f;
    		this.particle = Particle.HEART;
    		break;
    	}
		
    	// Switch over the different particle types and create the correct one
		EntityFX emitterFX = null;
    	switch(this.particle) {
    	case BUBBLE:
    		emitterFX = new EntityBubbleFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset, this.xVel, this.yVel, this.zVel);
    		break;
    	case FLAME:
    		emitterFX = new EntityFlameFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset, this.xVel, this.yVel, this.zVel);
    		break;
    	case HEART:
    		emitterFX = new EntityHeartFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset, this.xVel, this.yVel, this.zVel, this.scaleMultiplier);
    		break;
    	case LAVA:
    		emitterFX = new EntityLavaFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset);
    		break;
    	case NOTE:
    		emitterFX = new EntityNoteFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset, this.xVel, this.yVel, this.zVel, this.scaleMultiplier);
    		break;
    	case SPLASH:
    		emitterFX = new EntitySplashFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset, this.xVel, this.yVel, this.zVel);
    		break;
    	case REDDUST:
    		emitterFX = new EntityReddustFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset, this.scaleMultiplier, this.xVel, this.yVel, this.zVel);
    		break;
    	case SMOKE:
    		emitterFX = new EntitySmokeFX(this.worldObj, this.xCoord + this.xOffset, this.yCoord + this.yOffset, this.zCoord + this.zOffset, this.xVel, this.yVel, this.zVel, this.scaleMultiplier);
    		break;
    	default:
			return;    	
    	}
    	
    	Minecraft.getMinecraft().effectRenderer.addEffect(emitterFX);
    }
}
