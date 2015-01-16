package machir.emitters.block;

import machir.emitters.tileentity.TileEmitter;
import machir.emitters.util.BlockConstants;
import machir.emitters.util.ModConstants;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {
	// Below is a list of all blocks available
	public static Block emitter;
	
	/**
	 * Initializes all blocks on startup.
	 */
	public static void init() {
		emitter = new BlockEmitter();
	}
	
	/**
	 * Initializes all tile entities on startup.
	 */
	public static void initTileEntities() {
		registerTile(TileEmitter.class, BlockConstants.EMITTER);
	}
	
	/**
	 * Shortcut to register a tile entity.
	 * 
	 * @param clazz The tile entity class to register
	 * @param key The id to register the tile entity under
	 */
	private static void registerTile(Class<? extends TileEntity> clazz, String key) {
		GameRegistry.registerTileEntityWithAlternatives(clazz, ModConstants.PREFIX + key, key);
	}
}
