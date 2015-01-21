package machir.emitters.client;

import machir.emitters.CommonProxy;
import machir.emitters.tileentity.TileEmitter;
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

public class ClientProxy extends CommonProxy {
	@Override
	public void spawnParticle(TileEmitter emitter, float xOffset, float yOffset, float zOffset, float xVel, float yVel, float zVel) {
    	// Switch over the different particle types and create the correct one
		EntityFX emitterFX = null;
    	switch(emitter.getParticle()) {
    	case BUBBLE:
    		emitterFX = new EntityBubbleFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset, xVel, yVel, zVel);
    		break;
    	case FLAME:
    		emitterFX = new EntityFlameFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset, xVel, yVel, zVel);
    		break;
    	case HEART:
    		emitterFX = new EntityHeartFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset, xVel, yVel, zVel, emitter.size);
    		break;
    	case LAVA:
    		emitterFX = new EntityLavaFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset);
    		break;
    	case NOTE:
    		emitterFX = new EntityNoteFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset, xVel, yVel, zVel, emitter.size);
    		break;
    	case SPLASH:
    		emitterFX = new EntitySplashFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset, xVel, yVel, zVel);
    		break;
    	case REDDUST:
    		emitterFX = new EntityReddustFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset, emitter.size, xVel, yVel, zVel);
    		break;
    	case SMOKE:
    		emitterFX = new EntitySmokeFX(emitter.getWorldObj(), emitter.xCoord + xOffset, emitter.yCoord + yOffset, emitter.zCoord + zOffset, xVel, yVel, zVel, emitter.size);
    		break;
    	default:
			return;    	
    	}
    	
    	Minecraft.getMinecraft().effectRenderer.addEffect(emitterFX);
	}
}
