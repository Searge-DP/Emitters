package machir.emitters.tileentity;

import machir.emitters.Emitters;
import machir.emitters.util.ModConstants;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEmitter extends TileEntity {
	public static enum Button {
		INC_VEL,
		DEC_VEL,
		INC_SIZE,
		DEC_SIZE,
		INC_SPREAD,
		DEC_SPREAD,
		INC_FREQ,
		DEC_FREQ
	}
	
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
	
	public float velocity = 0f;
	public float size = 1f;
	public float spread = 1f;
	public int frequency = 20;
	
	
	@Override
    public void updateEntity() {
		if (!this.worldObj.isRemote || this.worldObj.getTotalWorldTime() % this.frequency != 0) {
			return;
		}
		this.particle = Particle.SMOKE;
		
		float xOffset = 0;
		float yOffset = 0;
		float zOffset = 0;
		
		float xVel = 0;
		float yVel = 0;
		float zVel = 0;
		// Prepare the proper particle settings
    	switch (ForgeDirection.getOrientation(this.blockMetadata)) {
    	case UP:
    	default:
    		xOffset = 0.5f;
    		yOffset = 0.3f;
    		zOffset = 0.5f;
    		yVel    = this.velocity;
    		break;
    	case DOWN:
    		xOffset = 0.5f;
    		yOffset = 0.8f;
    		zOffset = 0.5f;
    		yVel    = -this.velocity;
    		break;
    	case NORTH:
    		xOffset = 0.5f;
    		yOffset = 0.5f;
    		zOffset = 0.7f;
    		zVel    = -this.velocity;
    		break;
    	case SOUTH:
    		xOffset = 0.5f;
    		yOffset = 0.5f;
    		zOffset = 0.3f;
    		zVel    = this.velocity;
    		break;
    	case WEST:
    		xOffset = 0.7f;
    		yOffset = 0.5f;
    		zOffset = 0.5f;
    		xVel    = -this.velocity;
    		break;
    	case EAST:
    		xOffset = 0.3f;
    		yOffset = 0.5f;
    		zOffset = 0.5f;
    		xVel    = this.velocity;
    		break;
    	}
    	
    	Emitters.proxy.spawnParticle(this, xOffset, yOffset, zOffset, xVel, yVel, zVel);
    }
	
	public void handleButtonClick(String buttonName) {
		switch (Button.valueOf(buttonName)) {
		case INC_VEL:
			this.velocity += 0.1f;
			break;
		case DEC_VEL:
			this.velocity -= 0.1f;
			break;
		case INC_SIZE:
			this.size += 0.1f;
			break;
		case DEC_SIZE:
			this.size -= 0.1f;
			break;
		case INC_SPREAD:
			this.spread += 0.1f;
			break;
		case DEC_SPREAD:
			this.spread -= 0.1f;
			break;
		case INC_FREQ:
			this.frequency += 1;
			break;
		case DEC_FREQ:
			this.frequency -= 1;
			break;
		}
	}
	
	/**
	 * @return the particle
	 */
	public Particle getParticle() {
		return this.particle;
	}
	
	/**
	 * Sets the particle to spawn
	 * 
	 * @param particle the new particle
	 */
	public void setParticle(Particle particle) {
		this.particle = particle;
	}
}
