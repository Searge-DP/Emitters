package machir.emitters.tileentity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import machir.emitters.Emitters;
import machir.emitters.util.ModConstants;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
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
	
	public double velocity = 0d;
	public double size = 1d;
	public double spread = 1d;
	public int frequency = 20;
	
	
	@Override
    public void updateEntity() {
		if (!this.worldObj.isRemote || this.worldObj.getTotalWorldTime() % this.frequency != 0) {
			return;
		}
		
		double xOffset = 0;
		double yOffset = 0;
		double zOffset = 0;
		
		double xVel = 0;
		double yVel = 0;
		double zVel = 0;
		// Prepare the proper particle settings
    	switch (ForgeDirection.getOrientation(this.blockMetadata)) {
    	case UP:
    	default:
    		xOffset = 0.5d;
    		yOffset = 0.3d;
    		zOffset = 0.5d;
    		yVel    = this.velocity;
    		break;
    	case DOWN:
    		xOffset = 0.5d;
    		yOffset = 0.8d;
    		zOffset = 0.5d;
    		yVel    = -this.velocity;
    		break;
    	case NORTH:
    		xOffset = 0.5d;
    		yOffset = 0.5d;
    		zOffset = 0.7d;
    		zVel    = -this.velocity;
    		break;
    	case SOUTH:
    		xOffset = 0.5d;
    		yOffset = 0.5d;
    		zOffset = 0.3d;
    		zVel    = this.velocity;
    		break;
    	case WEST:
    		xOffset = 0.7d;
    		yOffset = 0.5d;
    		zOffset = 0.5d;
    		xVel    = -this.velocity;
    		break;
    	case EAST:
    		xOffset = 0.3d;
    		yOffset = 0.5d;
    		zOffset = 0.5d;
    		xVel    = this.velocity;
    		break;
    	}
    	
    	Emitters.proxy.spawnParticle(this, xOffset, yOffset, zOffset, xVel, yVel, zVel);
    }
	
	public void handleButtonClick(String buttonName) {
		switch (Button.valueOf(buttonName)) {
		case INC_VEL:
			this.velocity += 0.1d;
			this.velocity = this.round(this.velocity);
			break;
		case DEC_VEL:
			this.velocity -= 0.1d;
			this.velocity = this.round(this.velocity);
			break;
		case INC_SIZE:
			this.size += 0.1d;
			this.size = this.round(this.size);
			break;
		case DEC_SIZE:
			this.size -= 0.1d;
			this.size = this.round(this.size);
			break;
		case INC_SPREAD:
			this.spread += 0.1d;
			this.spread = this.round(this.spread);
			break;
		case DEC_SPREAD:
			this.spread -= 0.1d;
			this.spread = this.round(this.spread);
			break;
		case INC_FREQ:
			this.frequency += 1;
			break;
		case DEC_FREQ:
			this.frequency -= 1;
			break;
		}
	}
	
	public double round(double value) {
		value *= 10;
		value = Math.round(value);
		value /= 10;
		return value;
	}
	
	/**
	 * Used to process description packets
	 */
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    	this.readFromNBT(pkt.func_148857_g());
    }
    
    public void readFromNBT(NBTTagCompound nbttagcompound) {
        super.readFromNBT(nbttagcompound);
        this.particle = Particle.valueOf(nbttagcompound.getString("particle"));
        this.velocity = nbttagcompound.getDouble("velocity");
        this.size = nbttagcompound.getDouble("size");
        this.spread = nbttagcompound.getDouble("spread");
        this.frequency = nbttagcompound.getInteger("frequency");
    }

    public void writeToNBT(NBTTagCompound nbttagcompound) {
        super.writeToNBT(nbttagcompound);
        nbttagcompound.setString("particle", this.particle.toString());
        nbttagcompound.setDouble("velocity", this.velocity);
        nbttagcompound.setDouble("size", this.size);
        nbttagcompound.setDouble("spread", this.spread);
        nbttagcompound.setInteger("frequency", this.frequency);
    }
	
    /**
     * Provides extra tile entity data through the update packet
     */
    public Packet getDescriptionPacket() {
        NBTTagCompound nbttagcompound = new NBTTagCompound();
        this.writeToNBT(nbttagcompound);
        return new S35PacketUpdateTileEntity(this.xCoord, this.yCoord, this.zCoord, 0, nbttagcompound);
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
